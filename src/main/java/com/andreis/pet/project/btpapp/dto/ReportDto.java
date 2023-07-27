package com.andreis.pet.project.btpapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReportDto {

    private Long id;
    private String description;
    private LocalDateTime createdAt;
    private Long employeeId;
    private Long projectId;

    public ReportDto(Long id, String description, LocalDateTime createdAt, Long employeeId, Long projectId) {
        this.id = id;
        this.description = description;
        this.createdAt = createdAt;
        this.employeeId = employeeId;
        this.projectId = projectId;
    }
}
