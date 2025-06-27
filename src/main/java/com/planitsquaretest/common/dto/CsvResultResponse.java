package com.planitsquaretest.common.dto;

import java.util.List;

public record CsvResultResponse<T>(
        List<T> data,
        int failedCount
) {
}
