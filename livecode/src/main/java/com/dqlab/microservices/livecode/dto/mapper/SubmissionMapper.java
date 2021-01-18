package com.dqlab.microservices.livecode.dto.mapper;

import com.dqlab.microservices.livecode.dto.SubmissionDTO;
import com.dqlab.microservices.livecode.entity.Submission;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubmissionMapper {
    SubmissionDTO toDto(Submission submission);

    List<SubmissionDTO> toDtos(List<Submission> submissions);

    @Mapping(target = "language.id", source = "languageId")
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "stdout", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "time", ignore = true)
    @Mapping(target = "memory", ignore = true)
    @Mapping(target = "stderr", ignore = true)
    @Mapping(target = "compileOutput", ignore = true)
    @Mapping(target = "exitCode", ignore = true)
    @Mapping(target = "exitSignal", ignore = true)
    @Mapping(target = "message", ignore = true)
    @Mapping(target = "wallTime", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "finishedAt", ignore = true)
    Submission toEntity(SubmissionDTO submissionDTO);
}
