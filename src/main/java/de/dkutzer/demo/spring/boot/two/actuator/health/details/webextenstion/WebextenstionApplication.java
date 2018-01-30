package de.dkutzer.demo.spring.boot.two.actuator.health.details.webextenstion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.HealthStatusHttpMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebextenstionApplication {

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnEnabledEndpoint
	public HealthDetailsEndpoint myHealthEndpoint(HealthEndpoint delegate,
		HealthStatusHttpMapper statusHttpMapper) {
		return new HealthDetailsEndpoint(delegate,
			statusHttpMapper);
	}
//throws:
	/*
	Caused by: java.lang.IllegalStateException: Found two extensions for the same endpoint 'org.springframework.boot.actuate.health.HealthEndpoint': de.dkutzer.demo.spring.boot.two.actuator.health.details.webextenstion.HealthEndpointWebExtension and org.springframework.boot.actuate.health.HealthEndpointWebExtension

	 */
//	@Bean
//    @ConditionalOnMissingBean
//	public MyHealthEndpointWebExtension myHealthEndpointExtension(HealthIndicator delegate,
//		HealthStatusHttpMapper statusMapper){
//		return new MyHealthEndpointWebExtension(delegate, statusMapper);
//	}

	public static void main(String[] args) {
		SpringApplication.run(WebextenstionApplication.class, args);
	}
}
