package com.TrabajoFinal.TestVocacional.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorResponse extends RuntimeException{
    
    private HttpStatus status;

    public ErrorResponse(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
