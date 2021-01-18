package com.dqlab.microservices.livecode.controller;

import com.dqlab.microservices.livecode.dto.LanguageDTO;
import com.dqlab.microservices.livecode.dto.mapper.LanguageMapper;
import com.dqlab.microservices.livecode.service.LanguageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/languages", produces = MediaType.APPLICATION_JSON_VALUE)
public class LanguageController {

    private static final Logger logger = LoggerFactory.getLogger(LanguageController.class);

    private final LanguageMapper languageMapper;
    private final LanguageService languageService;

    @Autowired
    public LanguageController(LanguageMapper languageMapper, LanguageService languageService) {
        this.languageMapper = languageMapper;
        this.languageService = languageService;
    }


    @GetMapping
    public ResponseEntity<List<LanguageDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(languageMapper.toDtos(languageService.list()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanguageDTO> get(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(languageMapper.toDto(languageService.get(id)));
    }
}
