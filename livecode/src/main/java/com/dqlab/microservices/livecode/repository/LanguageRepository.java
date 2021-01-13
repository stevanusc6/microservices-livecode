package com.dqlab.microservices.livecode.repository;

import com.dqlab.microservices.livecode.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

}
