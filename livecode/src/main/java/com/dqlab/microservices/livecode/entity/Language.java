package com.dqlab.microservices.livecode.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String sourceFile;

    private String compileCmd;

    private String runCmd;

    private Boolean isActive = true;

}
