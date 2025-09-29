package org.metrics.resource;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/q/metrics/custom-metrics")
public class CustomMetricResource {
    @Inject
    private MeterRegistry registry;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String customMetrics() {
        if (registry instanceof PrometheusMeterRegistry prometheusRegistry) {
            // Optionally filter or format metrics here
            registry.counter("custom_hello_counter").increment();
            return prometheusRegistry.scrape();
        }
        return "# Prometheus registry not available";
    }
}
