package com.kuliginstepan.dadata.client;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import lombok.Data;

@Data
public class DadataClientProperties {

    /**
     * Dadata base url
     */
    private String baseUrl = "https://suggestions.dadata.ru/suggestions/api/4_1/rs";

    /**
     * Dadata API token
     */
    private String token;

    /**
     * Request timeout. Default - 5 seconds
     */
    private TimeoutSettings timeoutSettings = new TimeoutSettings(5000, 5000, 5000);

    private ProxySettings proxySettings;
}
