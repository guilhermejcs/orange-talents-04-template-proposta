package br.com.zupacademy.guilhermejcs.proposta.health;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "status-sistema", url = "http://localhost:8080")
public interface HealthClient {

    @GetMapping(value = "/actuator/health")
    Health health();
}