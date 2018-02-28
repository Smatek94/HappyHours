package com.toreforge.pinball.exception;

public class BoardLoaderException extends Throwable {
    public BoardLoaderException() {
        super();
    }

    public BoardLoaderException(String message) {
        super(message);
    }

    public BoardLoaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public BoardLoaderException(Throwable cause) {
        super(cause);
    }
}
