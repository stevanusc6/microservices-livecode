package com.dqlab.microservices.livecode.service.impl;

import com.dqlab.microservices.livecode.entity.Language;
import com.dqlab.microservices.livecode.entity.Submission;
import com.dqlab.microservices.livecode.exception.NotFoundException;
import com.dqlab.microservices.livecode.repository.LanguageRepository;
import com.dqlab.microservices.livecode.repository.SubmissionRepository;
import com.dqlab.microservices.livecode.service.SubmissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    private static final Logger logger = LoggerFactory.getLogger(SubmissionServiceImpl.class);
    private final KafkaTemplate<String, Object> kafkaProducer;
    private final SubmissionRepository submissionRepository;
    private final LanguageRepository languageRepository;
    @Value("${KAFKA_TOPIC:submission}")
    private String kafkaTopic;


    @Autowired
    public SubmissionServiceImpl(SubmissionRepository submissionRepository, KafkaTemplate<String, Object> kafkaProducer, LanguageRepository languageRepository) {
        this.submissionRepository = submissionRepository;
        this.kafkaProducer = kafkaProducer;
        this.languageRepository = languageRepository;
    }

    @Override
    public List<Submission> list() {
        return submissionRepository.findAll();
    }

    @Override
    public Submission get(UUID token) {
        return submissionRepository.findSubmissionByToken(token)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Submission create(Submission submission) {
        Language language = languageRepository.findById(submission.getLanguage().getId())
                .orElse(null);

        if (language == null) {
            throw new NotFoundException(String.format("languageId: %s not found", submission.getLanguage().getId()));
        }

        submission.setLanguage(language);
        submission = submissionRepository.save(submission);
        kafkaProducer.send(kafkaTopic, submission);

        return submission;
    }

    @Override
    public Submission update(Submission submission, UUID token) {
        Optional<Submission> submissionData = submissionRepository.findSubmissionByToken(token);
        submission.setToken(token);

        if (submissionData.isEmpty()) {
            throw new NotFoundException();
        }

        return submissionRepository.save(submission);
    }

    @Override
    public void delete(UUID token) {
        submissionRepository.deleteByToken(token);
    }
}
