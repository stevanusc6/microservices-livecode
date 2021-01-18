package com.dqlab.microservices.compilers.repository;

import com.dqlab.microservices.compilers.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

}
