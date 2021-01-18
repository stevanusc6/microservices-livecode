package com.dqlab.microservices.compilers.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
public class Language {

    @Id
    private Long id;

    private String name;

    private String sourceFile;

    private String compileCmd;

    private String runCmd;

    private boolean isActive = true;

}
