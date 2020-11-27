package com.kuliginstepan.dadata.client.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DadataException extends RuntimeException {

    private final int status;
    private final ErrorDetails details;

    public DadataException(int status, ErrorDetails details) {
        this.status = status;
        this.details = details;
    }
}
