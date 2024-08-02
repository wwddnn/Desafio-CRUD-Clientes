package com.devsuperior.desafio_crud.dto;

import java.time.Instant;

public class CustomError {

    //attributes
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;

    //constructor with parameters
    public CustomError(Instant timestamp, Integer status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    //only get methods (set don't)
    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
