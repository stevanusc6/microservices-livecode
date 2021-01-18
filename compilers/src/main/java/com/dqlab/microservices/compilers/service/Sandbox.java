package com.dqlab.microservices.compilers.service;

import com.dqlab.microservices.compilers.command.Isolate;
import com.dqlab.microservices.compilers.entity.Submission;
import com.dqlab.microservices.compilers.helper.FileHelper;
import com.zaxxer.nuprocess.NuAbstractProcessHandler;
import com.zaxxer.nuprocess.NuProcess;
import com.zaxxer.nuprocess.NuProcessBuilder;
import com.zaxxer.nuprocess.NuProcessHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

@Service
public class Sandbox {
    private static final Logger logger = LoggerFactory.getLogger(Sandbox.class);
    private final FileHelper fileHelper;
    private Submission submission;

    @Autowired
    public Sandbox(FileHelper fileHelper) {
        this.fileHelper = fileHelper;
    }

    @KafkaListener(topics = "${KAFKA_TOPIC:submission}", groupId = "SubmissionConsumer")
    public void execute(Submission submission) {
        this.submission = submission;

        initSandboxDirectory();

        boolean isEnableCgroups = !submission.isEnablePerProcessAndThreadTimeLimit() || !submission.isEnablePerProcessAndThreadMemoryLimit();

        /* TODO: setting environment */
        List<String> env = Arrays.asList("LANG", "LANGUAGE", "LC_ALL");

        Isolate isolate = new Isolate
                .IsolateBuilder()
                .withCg(isEnableCgroups)
                .withSilent()
                .withBoxId(submission.getId())
                .withMeta(FileHelper.METADATA_FILENAME)
                .withStderrToStdout(submission.isRedirectStderrToStdout())
                .withTime(submission.getCpuTimeLimit())
                .withExtraTime(submission.getCpuExtraTime())
                .withWallTimeLimit(submission.getWallTimeLimit())
                .withStack(submission.getStackLimit())
                .withProcesses(submission.getMaxProcessesAndOrThreads())
                .withCgTiming(submission.isEnablePerProcessAndThreadTimeLimit(), isEnableCgroups)
                .setMemoryLimit(submission.getMemoryLimit(), submission.isEnablePerProcessAndThreadMemoryLimit())
                .withFileSize(submission.getMaxFileSize())
                .withDir("/etc:noexec")
                .setEnv(env)
                .execute("run")
                .build();

        NuProcessBuilder pb = new NuProcessBuilder(isolate.getCommandList());
        NuProcessHandler processHandler = new ProcessHandler();
        pb.setProcessListener(processHandler);
        pb.setCwd(Path.of(fileHelper.getWorkingDir()));
        pb.start();
    }

    public void initSandboxDirectory() {
        Isolate isolate = new Isolate
                .IsolateBuilder()
                .withCg(!submission.isEnablePerProcessAndThreadTimeLimit() || !submission.isEnablePerProcessAndThreadMemoryLimit())
                .withBoxId(submission.getId())
                .executeInit()
                .build();

        try {
            Process proc = Runtime.getRuntime().exec(String.join(" ", isolate.getCommandList()));

            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                fileHelper.setWorkingDir(line);
            }

            fileHelper.saveSourceCode(submission.getLanguage().getSourceFile(), submission.getSourceCode());
            fileHelper.saveRunScript(submission.getLanguage().getRunCmd());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cleanUpSandbox() {
        try {
            Isolate isolate = new Isolate
                    .IsolateBuilder()
                    .withCg(!submission.isEnablePerProcessAndThreadTimeLimit() || !submission.isEnablePerProcessAndThreadMemoryLimit())
                    .withBoxId(submission.getId())
                    .executeCleanUp()
                    .build();

            Runtime.getRuntime().exec(String.join(" ", isolate.getCommandList()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void verify() {

    }


    public void resetMetadataFile() {
    }

    public void getMetadata() {

    }

    private class ProcessHandler extends NuAbstractProcessHandler {
        private final LinkedList<String> cmdList = new LinkedList<>();
        private final Semaphore semaphore = new Semaphore(0);
        private NuProcess nuProcess;

        public void awaitDisconnection() {
            semaphore.acquireUninterruptibly(1);
        }

        public void clear() {
            cmdList.clear();
        }

        public void close() {
            nuProcess.destroy(false);
        }

        //Send direct command
        public void sendCommand(String ch) {
            clear();
            nuProcess.writeStdin(ByteBuffer.wrap(ch.getBytes()));
        }

        //Send command
        public void write(String stack) {
            cmdList.add(stack + "\n");
            nuProcess.wantWrite();
        }

        public synchronized Boolean isPending() {
            return nuProcess.hasPendingWrites();
        }

        @Override
        public void onStart(NuProcess nuProcess) {
            this.nuProcess = nuProcess;
        }

        @Override
        public boolean onStdinReady(ByteBuffer buffer) {
            if (!cmdList.isEmpty()) {
                String cmd = cmdList.poll();
                buffer.put(cmd.getBytes());
                buffer.flip();
            }

            return !cmdList.isEmpty();
        }

        @Override
        public void onStdout(ByteBuffer buffer, boolean closed) {
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);

            fileHelper.saveStdout(new String(bytes));
            logger.info(new String(bytes));

            if (closed) {
                semaphore.release();
            }

            nuProcess.closeStdin(true);
        }

        @Override
        public void onStderr(ByteBuffer buffer, boolean closed) {
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);

            fileHelper.saveStderr(new String(bytes));
        }

        @Override
        public void onExit(int statusCode) {
            fileHelper.cleanUp();
            cleanUpSandbox();
        }
    }
}
