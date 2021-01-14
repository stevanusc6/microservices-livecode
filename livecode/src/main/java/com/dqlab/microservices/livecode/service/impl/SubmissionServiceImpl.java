package com.dqlab.microservices.livecode.service.impl;

import com.dqlab.microservices.livecode.entity.Submission;
import com.dqlab.microservices.livecode.exception.NotFoundException;
import com.dqlab.microservices.livecode.repository.SubmissionRepository;
import com.dqlab.microservices.livecode.service.SubmissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    private static final Logger logger = LoggerFactory.getLogger(SubmissionServiceImpl.class);

    @Autowired
    private SubmissionRepository submissionRepository;

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
        return submissionRepository.save(submission);
    }

    @Override
    public Submission update(Submission submission, UUID token) {
        Optional<Submission> submissionData = submissionRepository.findSubmissionByToken(token);

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
