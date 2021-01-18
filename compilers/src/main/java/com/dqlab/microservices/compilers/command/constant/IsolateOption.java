package com.dqlab.microservices.compilers.command.constant;

public final class IsolateOption {
    public static final String META = " -M";
    public static final String MEM = " -m";
    public static final String TIME = " -t";
    public static final String WALL_TIME_LIMIT = " -w";
    public static final String EXTRA_TIME = " -x";
    public static final String BOX_ID = " -b";
    public static final String STACK = " -k";
    public static final String FSIZE = " -f";
    public static final String QUOTA = " -q";
    public static final String STDIN = " -i";
    public static final String STDOUT = " -o";
    public static final String STDERR = " -r";
    public static final String STDERR_TO_STDOUT = " --stderr-to-stdout";
    public static final String CHDIR = " -c";
    public static final String PROCESSES = " -p";
    public static final String SHARE_NET = " --share-net";
    public static final String INHERIT_FDS = " --inherit-fds";
    public static final String VERBOSE = " -v";
    public static final String SILENT = " -s";
    public static final String ENV_PARAM = " -E";
    public static final String DIR = " -d";
    public static final String NO_DEFAULT_DIRS = " --no-default-dirs";
    public static final String CG = " --cg";
    public static final String CG_MEM = " --cg-mem";
    public static final String CG_TIMING = " --cg-timing";
    public static final String NO_CG_TIMING = " --no-cg-timing";

    private IsolateOption() {
    }
}
