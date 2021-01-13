package com.dqlab.microservices.livecode.controller;

import com.dqlab.microservices.livecode.entity.Language;
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

    @Autowired
    private LanguageService languageService;

    @GetMapping
    public ResponseEntity<List<Language>> getAll() {
        List<Language> languages = languageService.list();

        return new ResponseEntity<>(languages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Language> get(@PathVariable Long id) {
        Language language = languageService.get(id).orElse(null);

        return new ResponseEntity<>(language, HttpStatus.OK);
    }
}
