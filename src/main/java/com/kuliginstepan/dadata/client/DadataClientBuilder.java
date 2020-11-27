package com.kuliginstepan.dadata.client;

import static java.util.Optional.ofNullable;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class DadataClientBuilder {

    private OkHttpClient httpClient;
    private TimeoutSettings timeoutSettings;
    private ProxySettings proxySettings;
    private String token;
    private String baseUrl;


    public DadataClientBuilder httpClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    public DadataClientBuilder timeoutSettings(TimeoutSettings timeoutSettings) {
        this.timeoutSettings = timeoutSettings;
        return this;
    }

    public DadataClientBuilder proxySettings(ProxySettings proxySettings) {
        this.proxySettings = proxySettings;
        return this;
    }

    public DadataClientBuilder token(String token) {
        this.token = token;
        return this;
    }

    public DadataClientBuilder baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public DadataClient build() {
        if (token == null) {
            throw new IllegalArgumentException("Token is needed to construct DadataClient");
        }
        DadataClientProperties defaultProps = new DadataClientProperties();
        if (httpClient == null) {
            httpClient = buildClient(ofNullable(proxySettings).orElse(defaultProps.getProxySettings()),
                    ofNullable(timeoutSettings).orElse(defaultProps.getTimeoutSettings()));

        }
        return new DadataClient(httpClient, token, ofNullable(baseUrl).orElse(defaultProps.getBaseUrl()));
    }

    protected OkHttpClient buildClient(ProxySettings proxySettings, TimeoutSettings timeoutSettings) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (proxySettings != null) {
            Proxy proxy = new Proxy(proxySettings.getProxyType(), new InetSocketAddress(proxySettings.getAddress(), proxySettings.getPort()));
            builder.proxy(proxy);
        }
        if (timeoutSettings != null) {
            builder.connectTimeout(timeoutSettings.getConnectTimeout(), TimeUnit.MILLISECONDS)
                    .writeTimeout(timeoutSettings.getWriteTimeout(), TimeUnit.MILLISECONDS)
                    .readTimeout(timeoutSettings.getReadTimeout(), TimeUnit.MILLISECONDS);
        }
        return builder.build();
    }
}
