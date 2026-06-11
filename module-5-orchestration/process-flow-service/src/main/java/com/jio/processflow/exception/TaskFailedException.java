package com.jio.processflow.exception;

/** Thrown by a task executor when a step fails. Stops the flow immediately. */
public class TaskFailedException extends RuntimeException {

    private final String taskName;

    public TaskFailedException(String taskName, String message) {
        super(message);
        this.taskName = taskName;
    }

    public String getTaskName() { return taskName; }
}
