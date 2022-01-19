package com.monov.cloud.gateway.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GatewayResponseEntity<T> extends ResponseEntity<T> {
    public GatewayResponseEntity(HttpStatus status) {
        super(status);
    }

    public GatewayResponseEntity(T body, HttpStatus status) {
        super(body, status);
    }
}
