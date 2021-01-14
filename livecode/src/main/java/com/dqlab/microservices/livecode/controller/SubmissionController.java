package com.dqlab.microservices.livecode.controller;

import com.dqlab.microservices.livecode.entity.Submission;
import com.dqlab.microservices.livecode.service.SubmissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/submissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubmissionController {

    private static final Logger logger = LoggerFactory.getLogger(SubmissionController.class);

    @Autowired
    private SubmissionService submissionService;

    @GetMapping
    public ResponseEntity<List<Submission>> getAll() {
        return new ResponseEntity<>(
                submissionService.list(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{token}")
    public ResponseEntity<Submission> get(@PathVariable UUID token) {
        return new ResponseEntity<>(
                submissionService.get(token),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Submission> create(Submission submission) {
        return new ResponseEntity<>(
                submissionService.create(submission),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{token}")
    public ResponseEntity<Submission> update(@RequestBody Submission submission, @PathVariable UUID token) {
        return new ResponseEntity<>(
                submissionService.update(submission, token),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{token}")
    public ResponseEntity<HttpStatus> delete(@PathVariable UUID token) {
        submissionService.delete(token);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
