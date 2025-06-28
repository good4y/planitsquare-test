package com.planitsquaretest.common.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends BaseException{
    public InternalServerErrorException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }

    public static InternalServerErrorException serverError(String message) {
        return new InternalServerErrorException(message);
    }

    public static InternalServerErrorException serverError(String message,  Throwable cause) {
        return new InternalServerErrorException(message, cause);
    }
}
