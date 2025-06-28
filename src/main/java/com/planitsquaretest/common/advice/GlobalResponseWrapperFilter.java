package com.planitsquaretest.common.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planitsquaretest.common.dto.CommonResponse;
import com.planitsquaretest.common.utils.ResponseUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class GlobalResponseWrapperFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingResponseWrapper responseWrapper =
                new ContentCachingResponseWrapper(response);

        filterChain.doFilter(request, responseWrapper);

        if (responseWrapper.getStatus() < 300
            && responseWrapper.getContentSize() == 0) {

            responseWrapper.setContentType(MediaType.APPLICATION_JSON_VALUE);

            CommonResponse<?> wrapper = ResponseUtil.success();
            String json = objectMapper.writeValueAsString(wrapper);
            responseWrapper.getWriter().write(json);
        }

        responseWrapper.copyBodyToResponse();
    }
}
