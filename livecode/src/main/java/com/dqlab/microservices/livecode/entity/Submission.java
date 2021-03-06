package com.dqlab.microservices.livecode.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID token = UUID.randomUUID();

    @Column(columnDefinition = "TEXT")
    private String sourceCode;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Column(columnDefinition = "TEXT")
    private String stdin;

    @Column(columnDefinition = "TEXT")
    private String expectedOutput;

    @Column(columnDefinition = "TEXT")
    private String stdout;

    @Enumerated(EnumType.STRING)
    private Status status = Status.IN_QUEUE;

    private Double time;

    private Integer memory;

    @Column(columnDefinition = "TEXT")
    private String stderr;

    private Integer numberOfRuns;

    private Double cpuTimeLimit;

    private Double cpuExtraTime;

    private Double wallTimeLimit;

    private Integer memoryLimit;

    private Integer stackLimit;

    private Integer maxProcessesAndOrThreads;

    private Boolean enablePerProcessAndThreadTimeLimit;

    private Boolean enablePerProcessAndThreadMemoryLimit;

    private Integer maxFileSize;

    @Column(columnDefinition = "TEXT")
    private String compileOutput;

    private Integer exitCode;

    private Integer exitSignal;

    @Column(columnDefinition = "TEXT")
    private String message;

    private Double wallTime;

    private String compilerOptions;

    private String commandLineArguments;

    private Boolean redirectStderrToStdout;

    private String callbackUrl;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
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
