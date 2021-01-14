package com.dqlab.microservices.livecode.repository;

import com.dqlab.microservices.livecode.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    Optional<Submission> findSubmissionByToken(UUID token);

    void deleteByToken(UUID token);
}
