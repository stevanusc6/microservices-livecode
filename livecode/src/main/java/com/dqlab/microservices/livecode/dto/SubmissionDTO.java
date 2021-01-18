package com.dqlab.microservices.livecode.dto;

import com.dqlab.microservices.livecode.helper.ConfigHelper;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@Data
public class SubmissionDTO {
    private UUID token;
    @NotNull(message = "sourceCode cannot be null")
    private String sourceCode;

    @NotNull(message = "languageId cannot be null")
    private Long languageId;

    private String stdin;

    private String expectedOutput;

    private String stdout;

    private SubmissionDTO.Status status;

    private Double time;

    private Integer memory;

    private String stderr;

    private Integer numberOfRuns;

    private Double cpuTimeLimit;

    private Double cpuExtraTime;

    private Double wallTimeLimit;

    private Integer memoryLimit;

    private Integer stackLimit;

    private Integer maxProcessesAndOrThreads;

    private Integer maxFileSize;

    private String compileOutput;

    private Integer exitCode;

    private Integer exitSignal;

    private String message;

    private Double wallTime;

    private String compilerOptions;

    private String commandLineArguments;

    private boolean enablePerProcessAndThreadTimeLimit;

    private boolean enablePerProcessAndThreadMemoryLimit;

    private boolean redirectStderrToStdout;

    private String callbackUrl;

    private Instant createdAt;

    private Instant finishedAt;


    public Integer getNumberOfRuns() {
        return numberOfRuns == null ? ConfigHelper.NUMBER_OF_RUNS : numberOfRuns;
    }

    public Double getCpuTimeLimit() {
        return cpuTimeLimit == null ? ConfigHelper.CPU_TIME_LIMIT : cpuTimeLimit;
    }

    public Double getCpuExtraTime() {
        return cpuExtraTime == null ? ConfigHelper.CPU_EXTRA_TIME : cpuExtraTime;
    }

    public Double getWallTimeLimit() {
        return wallTimeLimit == null ? ConfigHelper.WALL_TIME_LIMIT : wallTimeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit == null ? ConfigHelper.MEMORY_LIMIT : memoryLimit;
    }

    public Integer getStackLimit() {
        return stackLimit == null ? ConfigHelper.STACK_LIMIT : stackLimit;
    }

    public Integer getMaxProcessesAndOrThreads() {
        return maxProcessesAndOrThreads == null ? ConfigHelper.MAX_PROCESSES_AND_OR_THREADS : maxProcessesAndOrThreads;
    }

    public boolean isEnablePerProcessAndThreadTimeLimit() {
        return enablePerProcessAndThreadTimeLimit || ConfigHelper.ENABLE_PER_PROCESS_AND_THREAD_TIME_LIMIT;
    }

    public boolean isEnablePerProcessAndThreadMemoryLimit() {
        return enablePerProcessAndThreadMemoryLimit || ConfigHelper.ENABLE_PER_PROCESS_AND_THREAD_MEMORY_LIMIT;
    }

    public Integer getMaxFileSize() {
        return maxFileSize == null ? ConfigHelper.MAX_FILE_SIZE : maxFileSize;
    }

    public boolean isRedirectStderrToStdout() {
        return redirectStderrToStdout || ConfigHelper.REDIRECT_STDERR_TO_STDOUT;
    }

    public enum Status {
        IN_QUEUE,
        PROCESSING,
        ACCEPTED,
        WRONG_ANSWER,
        TIME_LIMIT_EXCEEDED,
        COMPILATION_ERROR,
        RUNTIME_ERROR,
        INTERNAL_ERROR,
        EXEC_FORMAT_ERROR
    }
}
