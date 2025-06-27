package com.planitsquaretest.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public abstract class BaseException extends RuntimeException {
    private final HttpStatusCode status;

    public BaseException(String message, HttpStatusCode status) {
        super(message);
        this.status = status;
    }

    public BaseException(String message, HttpStatusCode status, Throwable cause) {
        super(message, cause);
        this.status = status;
    }
}
