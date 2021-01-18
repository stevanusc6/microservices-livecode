package com.dqlab.microservices.livecode.dto.mapper;

import com.dqlab.microservices.livecode.dto.LanguageDTO;
import com.dqlab.microservices.livecode.entity.Language;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LanguageMapper {
    LanguageDTO toDto(Language language);

    List<LanguageDTO> toDtos(List<Language> languages);

    Language toEntity(LanguageDTO languageDTO);
}
