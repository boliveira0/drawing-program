package org.boliveira.drawing.control;

public class CommandExecutionFailedException extends RuntimeException {
    public CommandExecutionFailedException(String message, Exception exception) {
        super(message, exception);
    }
}