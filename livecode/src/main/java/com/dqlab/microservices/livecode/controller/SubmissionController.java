package com.dqlab.microservices.livecode.controller;

import com.dqlab.microservices.livecode.dto.SubmissionDTO;
import com.dqlab.microservices.livecode.dto.mapper.SubmissionMapper;
import com.dqlab.microservices.livecode.entity.Submission;
import com.dqlab.microservices.livecode.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/submissions", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SubmissionController {

    private static final Logger logger = LoggerFactory.getLogger(SubmissionController.class);

    private final SubmissionMapper submissionMapper;
    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService, SubmissionMapper submissionMapper) {
        this.submissionMapper = submissionMapper;
        this.submissionService = submissionService;
    }

    @GetMapping
    public ResponseEntity<List<SubmissionDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(submissionMapper.toDtos(submissionService.list()));
    }

    @GetMapping("/{token}")
    public ResponseEntity<SubmissionDTO> get(@PathVariable UUID token) {
        return ResponseEntity.status(HttpStatus.OK).body(submissionMapper.toDto(submissionService.get(token)));
    }

    @PostMapping
    public ResponseEntity<SubmissionDTO> create(@Valid @RequestBody SubmissionDTO submissionDTO) {
        Submission submission = submissionService.create(submissionMapper.toEntity(submissionDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(submissionMapper.toDto(submission));
    }

    @PutMapping("/{token}")
    public ResponseEntity<SubmissionDTO> update(@Valid @RequestBody SubmissionDTO submissionDTO, @PathVariable UUID token) {
        submissionService.update(submissionMapper.toEntity(submissionDTO), token);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(submissionDTO);
    }

    @DeleteMapping("/{token}")
    public ResponseEntity<HttpStatus> delete(@PathVariable UUID token) {
        submissionService.delete(token);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
