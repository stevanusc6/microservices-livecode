package com.dqlab.microservices.livecode.service;

import com.dqlab.microservices.livecode.entity.Submission;

import java.util.List;
import java.util.UUID;

public interface SubmissionService {

    List<Submission> list();

    Submission get(UUID token);

    Submission create(Submission submission);

    Submission update(Submission submission, UUID token);

    void delete(UUID token);
}
