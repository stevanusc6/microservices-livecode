package com.dqlab.microservices.livecode.service;

import com.dqlab.microservices.livecode.entity.Language;

import java.util.List;
import java.util.Optional;

public interface LanguageService {

    Optional<Language> get(Long id);

    List<Language> list();

}
