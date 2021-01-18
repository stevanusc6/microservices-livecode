package com.dqlab.microservices.compilers.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
public class Submission {

    @Id
    private Long id;

    private UUID token;

    private String sourceCode;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    private String stdin;

    private String expectedOutput;

    private String stdout;

    private Status status;

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

    private boolean enablePerProcessAndThreadTimeLimit;

    private boolean enablePerProcessAndThreadMemoryLimit;

    private Integer maxFileSize;

    private String compileOutput;

    private Integer exitCode;

    private Integer exitSignal;

    private String message;

    private Double wallTime;

    private String compilerOptions;

    private String commandLineArguments;

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
