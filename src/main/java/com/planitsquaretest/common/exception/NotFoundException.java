package com.planitsquaretest.common.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }

    public static NotFoundException notFound(String message, Throwable cause) {
        return new NotFoundException(message, cause);
    }

    public static NotFoundException notFound(String message) {
        return new NotFoundException(message);
    }
}
