package com.kuliginstepan.dadata.client;

public class TimeoutSettings {
    private long readTimeout;
    private long writeTimeout;
    private long connectTimeout;

    public TimeoutSettings(long readTimeout, long writeTimeout, long connectTimeout) {
        this.readTimeout = readTimeout;
        this.writeTimeout = writeTimeout;
        this.connectTimeout = connectTimeout;
    }

    public long getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
    }

    public long getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(long writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public long getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
}
