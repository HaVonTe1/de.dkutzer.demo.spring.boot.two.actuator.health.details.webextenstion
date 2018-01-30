package de.dkutzer.demo.spring.boot.two.actuator.health.details.webextenstion;

import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.HealthStatusHttpMapper;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "healthdetails")
public class HealthDetailsEndpoint {

    private HealthIndicator delegate;

    private final HealthStatusHttpMapper statusHttpMapper;

    public HealthDetailsEndpoint(HealthIndicator delegate,
        HealthStatusHttpMapper statusHttpMapper) {
        this.delegate = delegate;
        this.statusHttpMapper = statusHttpMapper;
    }

    //work in progress
    @ReadOperation
    public WebEndpointResponse<Health> healthdetails() {
        final Health health = this.delegate.health();
        Integer status = this.statusHttpMapper.mapStatus(health.getStatus());
        return new WebEndpointResponse<Health>(health, status);
    }



}
