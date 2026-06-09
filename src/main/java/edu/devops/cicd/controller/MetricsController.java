package edu.devops.cicd.controller;

import edu.devops.cicd.metrics.AppMetrics;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MetricsController {

    private final AppMetrics appMetrics;

    @GetMapping(value = "/metrics", produces = "text/plain; version=0.0.4; charset=utf-8")
    public String metrics() {
        return """
                # HELP app_requests_total Total number of application requests
                # TYPE app_requests_total counter
                app_requests_total %s
                # HELP app_errors_total Total number of application errors
                # TYPE app_errors_total counter
                app_errors_total %s
                """.formatted(appMetrics.getRequestsTotal(), appMetrics.getErrorsTotal());
    }
}
