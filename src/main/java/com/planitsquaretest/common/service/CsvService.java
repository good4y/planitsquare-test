package com.planitsquaretest.common.service;

import com.planitsquaretest.common.dto.CsvResultResponse;
import com.planitsquaretest.common.exception.InternalServerErrorException;
import com.planitsquaretest.common.utils.CsvUtil;
import com.planitsquaretest.country.service.CountryService;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Service
public class CsvService {

    public <T> CsvResultResponse<T> readCsv(String filePath, Class<T> type) {
        InputStream inputStream = CountryService.class.getResourceAsStream(filePath);

        if (inputStream == null) {
            throw InternalServerErrorException.serverError(String.format("CSV 파일을 찾을 수 없습니다: %s", filePath));
        }

        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return CsvUtil.read(reader, type);
        } catch (Exception e) {
            throw InternalServerErrorException.serverError("csv 읽기 실패", e);
        }
    }
}
