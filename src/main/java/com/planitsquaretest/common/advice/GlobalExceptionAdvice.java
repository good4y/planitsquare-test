package com.planitsquaretest.common.advice;

import com.planitsquaretest.common.exception.InternalServerErrorException;
import com.planitsquaretest.common.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler({Exception.class, InternalServerErrorException.class})
    public final ResponseEntity<?> handleDefaultExceptions(Exception e, HttpServletRequest request) {
        log.error("source = {} {}, message = {}",
                request.getMethod(), request.getRequestURI(), e.getMessage(), e);

        return ResponseUtil.failure(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

}
