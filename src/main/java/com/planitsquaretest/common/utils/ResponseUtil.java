package com.planitsquaretest.common.utils;

import com.planitsquaretest.common.dto.CommonResponse;
import com.planitsquaretest.common.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseUtil {

    private ResponseUtil() {
    }

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>("success", null, LocalDateTime.now());
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>("success", data, LocalDateTime.now());
    }

    public static ResponseEntity<ErrorResponse> failure(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new ErrorResponse(message, LocalDateTime.now()));
    }
}
