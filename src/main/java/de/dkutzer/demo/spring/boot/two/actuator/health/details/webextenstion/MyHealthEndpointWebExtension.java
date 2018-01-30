package de.dkutzer.demo.spring.boot.two.actuator.health.details.webextenstion;

import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.HealthStatusHttpMapper;
//its not possible to write your own HealthEndpointWebExtension
//becaus of:

/*
Caused by: java.lang.IllegalStateException: Found two extensions for the same endpoint 'org.springframework.boot.actuate.health.HealthEndpoint': de.dkutzer.demo.spring.boot.two.actuator.health.details.webextenstion.HealthEndpointWebExtension and org.springframework.boot.actuate.health.HealthEndpointWebExtension

 */
//@Component
//@EndpointWebExtension(endpoint = HealthEndpoint.class)
public class MyHealthEndpointWebExtension {

    private HealthIndicator delegate;

    private final HealthStatusHttpMapper statusHttpMapper;

    public MyHealthEndpointWebExtension(HealthIndicator delegate,
        HealthStatusHttpMapper statusHttpMapper) {
        this.delegate = delegate;
        this.statusHttpMapper = statusHttpMapper;
    }

    //work in progress
    @ReadOperation
    public WebEndpointResponse<Health> details(@Selector String details) {
        final Health health = this.delegate.health();
        final Map<String, Object> d = health.getDetails();
        System.out.println(d);
        Integer status = this.statusHttpMapper.mapStatus(health.getStatus());
        return new WebEndpointResponse<Health>(health, status);
    }

}
