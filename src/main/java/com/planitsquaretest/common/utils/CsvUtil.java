package com.planitsquaretest.common.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.CsvToBeanFilter;
import com.planitsquaretest.common.dto.CsvResultResponse;

import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CsvUtil {

    private CsvUtil() {
    }

    public static <T> CsvResultResponse<T> read(Reader reader, Class<T> dtoType) {
        AtomicInteger failed = new AtomicInteger(0);

        CsvToBeanFilter filter = line -> {
            boolean isNull = Arrays.stream(line).anyMatch(s -> s == null || s.isBlank() || s.equals("null"));
            if (isNull) {
                failed.incrementAndGet();
                return false;
            }

            return true;
        };

        List<T> data = new CsvToBeanBuilder<T>(reader)
                .withType(dtoType)
                .withIgnoreLeadingWhiteSpace(true)
                .withFilter(filter)
                .build()
                .parse();

        return new CsvResultResponse<>(data, failed.get());
    }
}
