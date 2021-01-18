package com.dqlab.microservices.compilers.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class SubmissionDTO {
    private UUID token;
    private String sourceCode;
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

