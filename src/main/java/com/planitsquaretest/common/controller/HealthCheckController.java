package com.planitsquaretest.common.controller;

import com.planitsquaretest.api.HealthApi;
import com.planitsquaretest.model.HealthResponse;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController implements HealthApi {

    @Override
    public HealthResponse healthCheck() {
        return new HealthResponse().status("OK");
    }
}
