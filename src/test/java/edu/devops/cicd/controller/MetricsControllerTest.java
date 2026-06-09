package edu.devops.cicd.controller;

import edu.devops.cicd.metrics.AppMetrics;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MetricsController.class)
class MetricsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AppMetrics appMetrics;

    @Test
    void metricsEndpointReturnsPrometheusFormat() throws Exception {
        when(appMetrics.getRequestsTotal()).thenReturn(10.0);
        when(appMetrics.getErrorsTotal()).thenReturn(2.0);

        mockMvc.perform(get("/metrics"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("app_requests_total 10.0")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("app_errors_total 2.0")));
    }
}
