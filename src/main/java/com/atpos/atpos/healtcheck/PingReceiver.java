package com.atpos.atpos.healtcheck;

import com.atpos.atpos.utils.ATPosServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingReceiver {

    private final HealthCheckService healthCheckService;

    public PingReceiver(HealthCheckService healthCheckService) {
        this.healthCheckService = healthCheckService;
    }


    @GetMapping("/health")
    public ATPosServerResponse healthCheck() throws InterruptedException {
        return healthCheckService.check();
    }
}
