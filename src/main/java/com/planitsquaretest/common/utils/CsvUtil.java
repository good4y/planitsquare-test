package com.planitsquaretest.common.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import com.planitsquaretest.common.exception.InternalServerErrorException;

import java.io.Reader;
import java.util.List;

public class CsvUtil {

    private CsvUtil() {
    }

    public static <T> List<T> read(Reader reader, Class<T> dtoType) {
        try {
            return new CsvToBeanBuilder<T>(reader)
                    .withType(dtoType)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } catch (Exception e) {
            throw InternalServerErrorException.serverError("csv read error: " + e.getMessage());
        }
    }
}
