package com.dqlab.microservices.livecode.service.impl;

import com.dqlab.microservices.livecode.entity.Language;
import com.dqlab.microservices.livecode.repository.LanguageRepository;
import com.dqlab.microservices.livecode.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public Optional<Language> get(Long id) {
        return languageRepository.findById(id);
    }

    @Override
    public List<Language> list() {
        return languageRepository.findAll();
    }
}
