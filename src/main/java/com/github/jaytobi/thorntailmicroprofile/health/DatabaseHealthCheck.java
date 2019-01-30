package com.github.jaytobi.thorntailmicroprofile.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

import javax.enterprise.context.ApplicationScoped;

//Annotations for automatic CDI discovery
@Health
@ApplicationScoped
public class DatabaseHealthCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder response = HealthCheckResponse.named("database-check");
        boolean historyDatabaseState = checkDatabase("database-history");
        //you can set key-value pairs that appear in the data section of your health check for more details
        response.withData("database-history", historyDatabaseState);
        boolean replicaDatabaseState = checkDatabase("database-replica");
        response.withData("database-replica", "replica database is " + (replicaDatabaseState ? "here" : "not reachable"));
        response.state(historyDatabaseState && replicaDatabaseState); //another way to set the state to UP/DOWN - compare to FreeDiskHealthCheck
        return response.build();
    }

    /**
     * This could be a fancy database check, like checking for existing tables, low pings to the database server, etc.
     *
     * @return true, if the database was checked successfully
     */
    private boolean checkDatabase(String name) {
        //TODO add logging
        System.out.println("checking database " + name);
        return Math.random() < 0.5; //just creating random return values
    }
}
