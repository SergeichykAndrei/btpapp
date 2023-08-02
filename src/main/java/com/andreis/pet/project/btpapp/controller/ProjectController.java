package com.andreis.pet.project.btpapp.controller;

import com.andreis.pet.project.btpapp.dto.ProjectDto;
import com.andreis.pet.project.btpapp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/projects")
public class ProjectController {

    private final EmployeeService employeeService;

    @GetMapping
    @PreAuthorize("hasAuthority('Display')")
    public List<ProjectDto> getProjects(@RequestParam("employeeId") @Positive Long employeeId) {
        log.info("Get Projects method is invoked!!!");
        return employeeService.getEmployee(employeeId).getProjects().stream()
                .map(project -> new ProjectDto(project.getId(), project.getName()))
                .collect(Collectors.toList());
    }
}
