package com.gptasktwo.exception;

public class DeveloperNotFoundException extends RuntimeException{

    public DeveloperNotFoundException() {
    }

    public DeveloperNotFoundException(String message) {
        super(message);
    }

    public DeveloperNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeveloperNotFoundException(Throwable cause) {
        super(cause);
    }
}
