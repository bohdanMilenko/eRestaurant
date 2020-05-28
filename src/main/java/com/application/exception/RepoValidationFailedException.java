package com.application.exception;

public class RepoValidationFailedException extends Exception{

    public RepoValidationFailedException() {
        super();
    }

    public RepoValidationFailedException(String message) {
        super(message);
    }

    public RepoValidationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepoValidationFailedException(Throwable cause) {
        super(cause);
    }

    protected RepoValidationFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
