package com.dqlab.microservices.livecode.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String sourceFile;

    private String compileCmd;

    private String runCmd;

    private Boolean isActive = true;

}
