package com.dqlab.microservices.livecode.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class ErrorMessage {

    private Integer statusCode;

    private Date timestamp;

    private String message;

    private String description;
}
