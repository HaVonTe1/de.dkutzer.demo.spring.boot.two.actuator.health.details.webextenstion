package de.dkutzer.demo.spring.boot.two.actuator.health.details.webextenstion;

import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.HealthStatusHttpMapper;
import org.springframework.stereotype.Component;

@Component
@EndpointWebExtension(endpoint = HealthEndpoint.class)
public class HealthEndpointWebExtension {

    private HealthIndicator delegate;

    private final HealthStatusHttpMapper statusHttpMapper;

    public HealthEndpointWebExtension(HealthIndicator delegate,
        HealthStatusHttpMapper statusHttpMapper) {
        this.delegate = delegate;
        this.statusHttpMapper = statusHttpMapper;
    }

    //work in progress
    @ReadOperation
    public WebEndpointResponse<Health> details() {
        final Health health = this.delegate.health();
        final Map<String, Object> details = health.getDetails();
        System.out.println(details);
        Integer status = this.statusHttpMapper.mapStatus(health.getStatus());
        return new WebEndpointResponse<Health>(health, status);


    }



}
