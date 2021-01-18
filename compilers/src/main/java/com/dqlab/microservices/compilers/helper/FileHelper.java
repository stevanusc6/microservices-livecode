package com.dqlab.microservices.compilers.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class FileHelper {
    public static final String STDIN_FILENAME = "stdin.txt";
    public static final String STDOUT_FILENAME = "stdout.txt";
    public static final String STDERR_FILENAME = "stderr.txt";
    public static final String METADATA_FILENAME = "metadata.txt";
    public static final String RUN_SCRIPT_FILENAME = "run";
    public static final String COMPILE_SCRIPT_FILENAME = "compile";
    private static final Logger logger = LoggerFactory.getLogger(FileHelper.class);
    private static final String BOX_DIR = "/box";
    private static final String TEMP_DIR = "/tmp";
    private String workingDir;

    public String getWorkingDir() {
        return this.workingDir;
    }

    public void setWorkingDir(String currentDirectory) {
        this.workingDir = currentDirectory;

        try {
            Files.createDirectories(Paths.get(getBoxDir()));
            Files.createDirectories(Paths.get(getTempDir()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBoxDir() {
        return this.workingDir + BOX_DIR;
    }

    public String getTempDir() {
        return this.workingDir + TEMP_DIR;
    }

    public void saveStdin(String content) {
        write(STDIN_FILENAME, content);
    }

    public void saveStdout(String content) {
        write(STDOUT_FILENAME, content);
    }

    public void saveStderr(String content) {
        write(STDERR_FILENAME, content);
    }

    public void saveSourceCode(String sourceCode, String content) {
        write(getBoxDir() + "/" + sourceCode, content);
    }

    public void saveRunScript(String content) {
        write(getBoxDir() + "/" + RUN_SCRIPT_FILENAME, content);
    }

    public void saveCompileScript(String content) {
        write(BOX_DIR + "/" + COMPILE_SCRIPT_FILENAME, content);
    }

    public void cleanUp() {
        File file = new File(workingDir);
        FileSystemUtils.deleteRecursively(file);
    }

    private void write(String filename, String data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            bufferedWriter.write(data);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    public String read(String filename) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }

            return content.toString();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }
}
