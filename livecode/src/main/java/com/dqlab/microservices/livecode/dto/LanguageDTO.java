package com.dqlab.microservices.livecode.dto;

import lombok.Data;

@Data
public class LanguageDTO {

    private Long id;

    private String name;

    private String sourceFile;

    private String compileCmd;

    private String runCmd;

    private boolean isActive;
}
