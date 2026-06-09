package edu.devops.cicd.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class AppMetrics {

    private final Counter requestsTotal;
    private final Counter errorsTotal;

    public AppMetrics(MeterRegistry registry) {
        this.requestsTotal = Counter.builder("app_requests_total")
                .description("Total number of application requests")
                .register(registry);
        this.errorsTotal = Counter.builder("app_errors_total")
                .description("Total number of application errors")
                .register(registry);
    }

    public void recordRequest() {
        requestsTotal.increment();
    }

    public void recordError() {
        errorsTotal.increment();
    }

    public double getRequestsTotal() {
        return requestsTotal.count();
    }

    public double getErrorsTotal() {
        return errorsTotal.count();
    }
}
