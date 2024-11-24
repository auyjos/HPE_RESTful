package com.hpe.restservice.exception;

public class FileStorageException extends RuntimeException {
    private String message;

    public FileStorageException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
