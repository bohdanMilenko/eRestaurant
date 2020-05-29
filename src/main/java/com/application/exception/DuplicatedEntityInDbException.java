package com.application.exception;

public class DuplicatedEntityInDbException extends RepoException {

    public DuplicatedEntityInDbException() {
        super();
    }

    public DuplicatedEntityInDbException(String message) {
        super(message);
    }

    public DuplicatedEntityInDbException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedEntityInDbException(Throwable cause) {
        super(cause);
    }

    protected DuplicatedEntityInDbException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
