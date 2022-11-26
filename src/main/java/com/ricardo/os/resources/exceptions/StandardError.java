package com.ricardo.os.resources.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long timestamp;
    private Integer status;
    private String error;

    public StandardError() {
        super();
    }

    public StandardError(Long timestamp, Integer status, String error) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
    }
}