package com.planitsquaretest.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

@Configuration
public class ApiPrefixConfig implements WebMvcRegistrations {

    private static final String API_PREFIX_V1 = "/api/v1";

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping() {

            @Override
            protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
                RequestMappingInfo apiPrefixInfo = RequestMappingInfo
                        .paths(API_PREFIX_V1)
                        .build();
                mapping = apiPrefixInfo.combine(mapping);

                super.registerHandlerMethod(handler, method, mapping);
            }
        };
    }
}