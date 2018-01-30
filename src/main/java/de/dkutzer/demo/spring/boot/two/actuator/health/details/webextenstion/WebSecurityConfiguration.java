package de.dkutzer.demo.spring.boot.two.actuator.health.details.webextenstion;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.EndpointRequest;
import org.springframework.boot.autoconfigure.security.StaticResourceRequest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${demo.security.actuator.username}")
    private String actuatorUsername;
    @Value("${demo.security.actuator.password}")
    private String actuatorPassword;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
            .requestMatchers(EndpointRequest.to( "info", "health"))
            .permitAll()
            .requestMatchers(EndpointRequest.toAnyEndpoint())
            .hasRole("ACTUATOR")
            .requestMatchers(StaticResourceRequest.toCommonLocations())
            .permitAll()
            .antMatchers("/health/details")
            .hasRole("ACTUATOR")

            .anyRequest().fullyAuthenticated()
            .and()
            .httpBasic()
            .and()
            .csrf().disable();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(
                new InMemoryUserDetailsManager(
                    Arrays.asList(
                        new User(actuatorUsername, actuatorPassword,
                            Arrays.asList(new SimpleGrantedAuthority("ROLE_ACTUATOR"),new SimpleGrantedAuthority("ROLE_ADMIN")))
                    )));
    }
}
