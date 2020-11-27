package com.kuliginstepan.dadata.client;

import java.net.Proxy;

public class ProxySettings {
    private Proxy.Type proxyType;
    private String address;
    private int port;

    public ProxySettings(Proxy.Type proxyType, String address, int port) {
        this.proxyType = proxyType;
        this.address = address;
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Proxy.Type getProxyType() {
        return proxyType;
    }

    public void setProxyType(Proxy.Type proxyType) {
        this.proxyType = proxyType;
    }
}
