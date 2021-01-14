package com.dqlab.microservices.livecode.service.impl;

import com.dqlab.microservices.livecode.entity.Language;
import com.dqlab.microservices.livecode.exception.NotFoundException;
import com.dqlab.microservices.livecode.repository.LanguageRepository;
import com.dqlab.microservices.livecode.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public Language get(Long id) {
        return languageRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Language> list() {
        return languageRepository.findAll();
    }
}
