package com.dqlab.microservices.livecode.service;

import com.dqlab.microservices.livecode.entity.Language;

import java.util.List;

public interface LanguageService {

    Language get(Long id);

    List<Language> list();
}
