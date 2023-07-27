package com.andreis.pet.project.btpapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ProjectDto {

    private Long id;
    private String name;

    public ProjectDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
