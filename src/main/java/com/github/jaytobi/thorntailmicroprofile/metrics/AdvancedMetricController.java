package com.github.jaytobi.thorntailmicroprofile.metrics;

import org.eclipse.microprofile.metrics.Histogram;
import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.time.LocalDateTime;

/**
 * This controller shows the programmatic usage of MicroProfile Metrics.
 * <p>
 * For the annotation based version see {@link MetricAnnotationController}
 */
@ApplicationScoped
@Path("/mymetricsadvanced")
public class AdvancedMetricController {
    @Inject
    private MetricRegistry registry;

    /**
     * This method uses the global MetricsRegistry to access the metrics via direct method calls
     * instead of annotation.
     * It stores a histogram of the query parameter.
     *
     * @param number A long value that will be added to the metric named 'number'
     * @return The String "Histogram: " with the current LocalDatetime appended. (just for showing something)
     */
    @GET
    @Path(value = "/historgram")
    public String histogram(@QueryParam("number") String number) {
        //these 2 line will throw an exception if called multiple times
        //IllegalArgumentException: Previously registered metric itemsAdded was not flagged as reusable
        //Metadata metadata = new Metadata("itemsAdded", MetricType.HISTOGRAM);
        //Histogram histogram = registry.histogram(metadata);
        //either check if the registry already contains the Metadata, move it to "one-time init" or
        //make it reusable
        Metadata metadata = new Metadata("number", MetricType.HISTOGRAM);
        metadata.setReusable(true);
        Histogram histogram = registry.histogram(metadata);
        histogram.update(Long.valueOf(number));
        return "Histogram: " + LocalDateTime.now();
    }
}
