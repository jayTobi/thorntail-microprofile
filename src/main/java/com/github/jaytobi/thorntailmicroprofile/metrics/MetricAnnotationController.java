package com.github.jaytobi.thorntailmicroprofile.metrics;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.time.LocalDateTime;

/**
 * A simple controller showing the basic usage of MicroProfile Metrics annotations.
 * <p>
 * See: https://github.com/eclipse/microprofile-metrics/blob/master/spec/src/main/asciidoc/app-programming-model.adoc
 * <p>
 * The annotations can be found at
 * https://github.com/eclipse/microprofile-metrics/blob/master/spec/src/main/asciidoc/app-programming-model.adoc#annotations
 */
@ApplicationScoped
@Path("/mymetrics")
public class MetricAnnotationController {

    @Counted(name = "countMe", tags = {"simple", "counter", "me"},
            description = "simply countMe with annotation")
    @GET
    @Path(value = "/countMe")
    public String countMe() {
        return "Count me: " + LocalDateTime.now();
    }

    @Metered(description = "my metered value")
    @GET
    @Path(value = "/metered")
    public String metered() {
        return "Metered: " + LocalDateTime.now();
    }

    @Timed(description = "Tracking the time")
    @GET
    @Path(value = "/timed")
    public String timed() {
        return "Timed: " + LocalDateTime.now();
    }
}
