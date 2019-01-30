package com.github.jaytobi.thorntailmicroprofile.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;

/**
 * This is a simple MicroProfile HealthCheck endpoint pretending to check the free disk space.
 */
//Annotations for automatic CDI discovery
@Health
@ApplicationScoped
public class FreeDiskHealthCheck implements HealthCheck {
    /**
     * This is the only method you need to implement.
     * In it you can build a (complex) HealthCheckResponse object with detailed information.
     *
     * @return
     */
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("freedisk-check").up().build();
    }
}
