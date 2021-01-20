package com.dqlab.microservices.compilers.command;

import java.util.List;
import java.util.StringTokenizer;

import static com.dqlab.microservices.compilers.command.constant.IsolateOption.*;

public class Isolate {
    private String meta;
    private String memoryLimit;
    private String time;
    private String wallTime;
    private String extraTime;
    private String boxId;
    private String stack;
    private String fileSize;
    private String quota;
    private String stdin;
    private String stdout;
    private String stderr;
    private String stderrToStdout;
    private String chdir;
    private String processes;
    private String shareNet;
    private String inheritFds;
    private String verbose;
    private String silent;
    private List<String> envParam;
    private String dir;
    private String noDefaultDirs;
    private String cg;
    private String cgTiming;
    private String exec;

    private Isolate() {
    }

    private String getEnv() {
        StringBuilder sb = new StringBuilder();

        if (envParam != null) {
            envParam.forEach(env -> sb.append(" ").append(ENV_PARAM).append(" ").append(env));

            return sb.toString();
        }

        return "";
    }

    public String[] getCommandList() {

        String command = "isolate" + cg + silent + boxId + meta + stderrToStdout +
                time + extraTime + wallTime + stack + processes + memoryLimit + cgTiming +
                fileSize + getEnv() + dir + quota + stdin + stdout + stderr + chdir +
                shareNet + inheritFds + verbose + noDefaultDirs + exec;

        StringTokenizer st = new StringTokenizer(command);
        String[] cmdArr = new String[st.countTokens()];

        for (int i = 0; st.hasMoreTokens(); ++i) {
            cmdArr[i] = st.nextToken();
        }

        return cmdArr;
    }

    public static class IsolateBuilder {
        private String meta;
        private String memoryLimit;
        private String time;
        private String wallTime;
        private String extraTime;
        private String boxId;
        private String stack;
        private String fileSize;
        private String quota;
        private String stdin;
        private String stdout;
        private String stderr;
        private String stderrToStdout;
        private String chdir;
        private String processes;
        private String shareNet;
        private String inheritFds;
        private String verbose;
        private String silent;
        private List<String> envParam;
        private String dir;
        private String noDefaultDirs;
        private String cg;
        private String cgTiming;
        private String exec;

        public static String replaceEmptyString(String str) {
            return str == null ? "" : str;
        }

        public IsolateBuilder withMeta(String file) {
            this.meta = META + " " + file;
            return this;
        }

        public IsolateBuilder setMemoryLimit(Integer size, boolean isControlGroup) {
            this.memoryLimit = isControlGroup ? CG_MEM + "=" + size : MEM + " " + size;
            return this;
        }

        public IsolateBuilder withTime(Double time) {
            this.time = TIME + " " + time;
            return this;
        }

        public IsolateBuilder withWallTimeLimit(Double time) {
            this.wallTime = WALL_TIME_LIMIT + " " + time;
            return this;
        }

        public IsolateBuilder withExtraTime(Double time) {
            this.extraTime = EXTRA_TIME + " " + time;
            return this;
        }

        public IsolateBuilder withBoxId(Long id) {
            this.boxId = BOX_ID + " " + id % 2147483647;
            return this;
        }

        public IsolateBuilder withStack(Integer size) {
            this.stack = STACK + " " + size;
            return this;
        }

        public IsolateBuilder withFileSize(Integer size) {
            this.fileSize = FSIZE + " " + size;
            return this;
        }

        public IsolateBuilder withQuota(String blocksAndInodes) {
            this.quota = QUOTA + " " + blocksAndInodes;
            return this;
        }

        public IsolateBuilder withStdin(String file) {
            this.stdin = STDIN + " " + file;
            return this;
        }

        public IsolateBuilder withStdout(String file) {
            this.stdout = STDOUT + " " + file;
            return this;
        }

        public IsolateBuilder withStderr(String file) {
            this.stderr = STDERR + " " + file;
            return this;
        }

        public IsolateBuilder withStderrToStdout(boolean enable) {
            this.stderrToStdout = enable ? STDERR_TO_STDOUT : "";
            return this;
        }

        public IsolateBuilder withChdir(String dir) {
            this.chdir = CHDIR + " " + dir;
            return this;
        }

        public IsolateBuilder withProcesses(Integer max) {
            this.processes = PROCESSES + "" + max;
            return this;
        }

        public IsolateBuilder withShareNet() {
            this.shareNet = SHARE_NET;
            return this;
        }

        public IsolateBuilder withInheritFds() {
            this.inheritFds = INHERIT_FDS;
            return this;
        }

        public IsolateBuilder withVerbose() {
            this.verbose = VERBOSE;
            return this;
        }

        public IsolateBuilder withSilent() {
            this.silent = SILENT;
            return this;
        }

        public IsolateBuilder setEnv(List<String> environments) {
            this.envParam = environments;
            return this;
        }

        public IsolateBuilder withDir(String option) {
            this.dir = DIR + " " + option;
            return this;
        }

        public IsolateBuilder withNoDefaultDirs() {
            this.noDefaultDirs = NO_DEFAULT_DIRS;
            return this;
        }

        public IsolateBuilder withCg(boolean enable) {
            this.cg = enable ? CG : "";
            return this;
        }

        public IsolateBuilder withCgTiming(boolean enableTimeLimit, boolean isCgActive) {
            if (!enableTimeLimit) {
                this.cgTiming = CG_TIMING;
            } else {
                this.cgTiming = isCgActive ? NO_CG_TIMING : "";
            }
            return this;
        }

        public IsolateBuilder executeInit() {
            this.exec = " --init";
            return this;
        }

        public IsolateBuilder executeCleanUp() {
            this.exec = " --cleanup";
            return this;
        }

        public IsolateBuilder execute(String program) {
            this.exec = " --run -- /bin/bash " + program;
            return this;
        }

        public Isolate build() {
            Isolate isolate = new Isolate();
            isolate.meta = replaceEmptyString(this.meta);
            isolate.memoryLimit = replaceEmptyString(this.memoryLimit);
            isolate.time = replaceEmptyString(this.time);
            isolate.wallTime = replaceEmptyString(this.wallTime);
            isolate.extraTime = replaceEmptyString(this.extraTime);
            isolate.boxId = replaceEmptyString(this.boxId);
            isolate.stack = replaceEmptyString(this.stack);
            isolate.fileSize = replaceEmptyString(this.fileSize);
            isolate.quota = replaceEmptyString(this.quota);
            isolate.stdin = replaceEmptyString(this.stdin);
            isolate.stdout = replaceEmptyString(this.stdout);
            isolate.stderr = replaceEmptyString(this.stderr);
            isolate.stderrToStdout = replaceEmptyString(this.stderrToStdout);
            isolate.chdir = replaceEmptyString(this.chdir);
            isolate.processes = replaceEmptyString(this.processes);
            isolate.shareNet = replaceEmptyString(this.shareNet);
            isolate.inheritFds = replaceEmptyString(this.inheritFds);
            isolate.verbose = replaceEmptyString(this.verbose);
            isolate.silent = replaceEmptyString(this.silent);
            isolate.envParam = this.envParam;
            isolate.dir = replaceEmptyString(this.dir);
            isolate.noDefaultDirs = replaceEmptyString(this.noDefaultDirs);
            isolate.cg = replaceEmptyString(this.cg);
            isolate.cgTiming = replaceEmptyString(this.cgTiming);
            isolate.exec = replaceEmptyString(this.exec);

            return isolate;
        }
    }


}
