package com.dqlab.microservices.livecode.helper;

public class ConfigHelper {

    public static final String ALLOWED_LANGUAGES_FOR_COMPILER_OPTIONS = System.getenv("ALLOWED_LANGUAGES_FOR_COMPILER_OPTIONS") == null ? "" : System.getenv("ALLOWED_LANGUAGES_FOR_COMPILER_OPTIONS");
    public static final Double CPU_TIME_LIMIT = System.getenv("CPU_TIME_LIMIT") == null ? 5 : Double.parseDouble(System.getenv("CPU_TIME_LIMIT"));
    public static final Double CPU_EXTRA_TIME = System.getenv("CPU_EXTRA_TIME") == null ? 1 : Double.parseDouble(System.getenv("CPU_EXTRA_TIME"));
    public static final Double WALL_TIME_LIMIT = System.getenv("WALL_TIME_LIMIT") == null ? 10 : Double.parseDouble(System.getenv("WALL_TIME_LIMIT"));
    public static final Integer CALLBACKS_MAX_TRIES = System.getenv("CALLBACKS_MAX_TRIES") == null ? 3 : Integer.parseInt(System.getenv("CALLBACKS_MAX_TRIES"));
    public static final Integer CALLBACKS_TIMEOUT = System.getenv("CALLBACKS_TIMEOUT") == null ? 5 : Integer.parseInt(System.getenv("CALLBACKS_TIMEOUT"));
    public static final Integer MAX_CPU_TIME_LIMIT = System.getenv("MAX_CPU_TIME_LIMIT") == null ? 15 : Integer.parseInt(System.getenv("MAX_CPU_TIME_LIMIT"));
    public static final Integer MAX_CPU_EXTRA_TIME = System.getenv("MAX_CPU_EXTRA_TIME") == null ? 5 : Integer.parseInt(System.getenv("MAX_CPU_EXTRA_TIME"));
    public static final Integer MAX_WALL_TIME_LIMIT = System.getenv("MAX_WALL_TIME_LIMIT") == null ? 20 : Integer.parseInt(System.getenv("MAX_WALL_TIME_LIMIT"));
    public static final Integer MEMORY_LIMIT = System.getenv("MEMORY_LIMIT") == null ? 128000 : Integer.parseInt(System.getenv("MEMORY_LIMIT"));
    public static final Integer MAX_MEMORY_LIMIT = System.getenv("MAX_MEMORY_LIMIT") == null ? 512000 : Integer.parseInt(System.getenv("MAX_MEMORY_LIMIT"));
    public static final Integer STACK_LIMIT = System.getenv("STACK_LIMIT") == null ? 64000 : Integer.parseInt(System.getenv("STACK_LIMIT"));
    public static final Integer MAX_STACK_LIMIT = System.getenv("MAX_STACK_LIMIT") == null ? 128000 : Integer.parseInt(System.getenv("MAX_STACK_LIMIT"));
    public static final Integer MAX_PROCESSES_AND_OR_THREADS = System.getenv("MAX_PROCESSES_AND_OR_THREADS") == null ? 60 : Integer.parseInt(System.getenv("MAX_PROCESSES_AND_OR_THREADS"));
    public static final Integer MAX_MAX_PROCESSES_AND_OR_THREADS = System.getenv("MAX_MAX_PROCESSES_AND_OR_THREADS") == null ? 120 : Integer.parseInt(System.getenv("MAX_MAX_PROCESSES_AND_OR_THREADS"));
    public static final Integer MAX_FILE_SIZE = System.getenv("MAX_FILE_SIZE") == null ? 1024 : Integer.parseInt(System.getenv("MAX_FILE_SIZE"));
    public static final Integer MAX_MAX_FILE_SIZE = System.getenv("MAX_MAX_FILE_SIZE") == null ? 4096 : Integer.parseInt(System.getenv("MAX_MAX_FILE_SIZE"));
    public static final Integer NUMBER_OF_RUNS = System.getenv("NUMBER_OF_RUNS") == null ? 1 : Integer.parseInt(System.getenv("NUMBER_OF_RUNS"));
    public static final Integer MAX_NUMBER_OF_RUNS = System.getenv("MAX_NUMBER_OF_RUNS") == null ? 20 : Integer.parseInt(System.getenv("MAX_NUMBER_OF_RUNS"));
    public static final Integer MAX_SUBMISSION_BATCH_SIZE = System.getenv("MAX_SUBMISSION_BATCH_SIZE") == null ? 20 : Integer.parseInt(System.getenv("MAX_SUBMISSION_BATCH_SIZE"));
    public static final Integer MAX_EXTRACT_SIZE = System.getenv("MAX_EXTRACT_SIZE") == null ? 10240 : Integer.parseInt(System.getenv("MAX_EXTRACT_SIZE"));
    public static final Boolean ENABLE_ADDITIONAL_FILES = System.getenv("ENABLE_ADDITIONAL_FILES") == null || Boolean.parseBoolean(System.getenv("ENABLE_ADDITIONAL_FILES")); // default: true
    public static final Boolean ENABLE_BATCHED_SUBMISSIONS = System.getenv("ENABLE_BATCHED_SUBMISSIONS") == null || Boolean.parseBoolean(System.getenv("ENABLE_BATCHED_SUBMISSIONS")); // default: true
    public static final Boolean REDIRECT_STDERR_TO_STDOUT = System.getenv("REDIRECT_STDERR_TO_STDOUT") != null && Boolean.parseBoolean(System.getenv("REDIRECT_STDERR_TO_STDOUT")); // default: false
    public static final Boolean MAINTENANCE_MODE = System.getenv("MAINTENANCE_MODE") != null && Boolean.parseBoolean(System.getenv("MAINTENANCE_MODE")); // default: false
    public static final Boolean ENABLE_WAIT_RESULT = System.getenv("ENABLE_WAIT_RESULT") == null || Boolean.parseBoolean(System.getenv("ENABLE_WAIT_RESULT")); // default: true
    public static final Boolean ENABLE_COMPILER_OPTIONS = System.getenv("ENABLE_COMPILER_OPTIONS") == null || Boolean.parseBoolean(System.getenv("ENABLE_COMPILER_OPTIONS")); // default: true
    public static final Boolean ENABLE_COMMAND_LINE_ARGUMENTS = System.getenv("ENABLE_COMMAND_LINE_ARGUMENTS") == null || Boolean.parseBoolean(System.getenv("ENABLE_COMMAND_LINE_ARGUMENTS")); // default: true
    public static final Boolean ENABLE_SUBMISSION_DELETE = System.getenv("ENABLE_SUBMISSION_DELETE") != null && Boolean.parseBoolean(System.getenv("ENABLE_SUBMISSION_DELETE")); // default: false
    public static final Boolean ENABLE_CALLBACKS = System.getenv("ENABLE_CALLBACKS") == null || Boolean.parseBoolean(System.getenv("ENABLE_CALLBACKS")); // default: true
    public static final Boolean ENABLE_PER_PROCESS_AND_THREAD_TIME_LIMIT = System.getenv("ENABLE_PER_PROCESS_AND_THREAD_TIME_LIMIT") != null && Boolean.parseBoolean(System.getenv("ENABLE_PER_PROCESS_AND_THREAD_TIME_LIMIT")); // default: false
    public static final Boolean ALLOW_ENABLE_PER_PROCESS_AND_THREAD_TIME_LIMIT = System.getenv("ALLOW_ENABLE_PER_PROCESS_AND_THREAD_TIME_LIMIT") == null || Boolean.parseBoolean(System.getenv("ALLOW_ENABLE_PER_PROCESS_AND_THREAD_TIME_LIMIT")); // default: true
    public static final Boolean ENABLE_PER_PROCESS_AND_THREAD_MEMORY_LIMIT = System.getenv("ENABLE_PER_PROCESS_AND_THREAD_MEMORY_LIMIT") != null && Boolean.parseBoolean(System.getenv("ENABLE_PER_PROCESS_AND_THREAD_MEMORY_LIMIT")); // default: false
    public static final Boolean ALLOW_ENABLE_PER_PROCESS_AND_THREAD_MEMORY_LIMIT = System.getenv("ALLOW_ENABLE_PER_PROCESS_AND_THREAD_MEMORY_LIMIT") == null || Boolean.parseBoolean(System.getenv("ALLOW_ENABLE_PER_PROCESS_AND_THREAD_MEMORY_LIMIT")); // default: true

    private ConfigHelper() {
    }
}