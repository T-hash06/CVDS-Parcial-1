package com.tomas.cvds.parcial.exceptions;

import org.springframework.http.ResponseEntity;

/**
 * Abstract class representing a custom application exception.
 * This class extends the standard Java Exception class and provides
 * additional functionality for handling HTTP response status codes.
 */
public abstract class AppException extends Exception {

    private Integer statusCode;
    public AppException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public ResponseEntity<Object> getResponse() {
        return ResponseEntity.status(statusCode).body(this.getMessage());
    }
}
