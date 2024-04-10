package dev.boot.exception;

public class NotFoundWithIdException extends RuntimeException{
    public NotFoundWithIdException() {
    }

    public NotFoundWithIdException(String message) {
        super(message);
    }

    public NotFoundWithIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundWithIdException(Throwable cause) {
        super(cause);
    }

    public NotFoundWithIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }



}
