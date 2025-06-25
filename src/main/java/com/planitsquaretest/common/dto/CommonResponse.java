package com.planitsquaretest.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CommonResponse<T>(String message, T data, LocalDateTime timestamp) {
}
