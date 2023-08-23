package com.andreis.pet.project.btpapp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ReportDto {

    private Long id;
    private String description;
    private LocalDateTime createdAt;
    private Long employeeId;
    private Long projectId;
}
