package com.dqlab.microservices.livecode.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private Integer statusCode;

    private Date timestamp;

    private String message;

    private String description;
}
