package com.andreis.pet.project.btpapp.controller;

import com.andreis.pet.project.btpapp.dto.ReportDto;
import com.andreis.pet.project.btpapp.dto.ReportRequestDto;
import com.andreis.pet.project.btpapp.filters.ReportFilter;
import com.andreis.pet.project.btpapp.model.Employee;
import com.andreis.pet.project.btpapp.model.Report;
import com.andreis.pet.project.btpapp.model.Project;
import com.andreis.pet.project.btpapp.service.EmployeeService;
import com.andreis.pet.project.btpapp.service.ProjectService;
import com.andreis.pet.project.btpapp.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reports")
public class ReportController {

    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private final ReportService reportService;

    @PostMapping
    public ReportDto create(@RequestBody ReportRequestDto reportRequestDto) {
        Employee employee = employeeService.getEmployee(reportRequestDto.getEmployeeId());
        Project project = projectService.getProject(reportRequestDto.getProjectId());
        Report report = reportService.save(project, employee, reportRequestDto.getDescription());
        return convertToReportDto(report);
    }

    @GetMapping
    public List<ReportDto> get(@RequestParam(value = "employee_id", required = false) @Positive Long employeeId,
                               @RequestParam(value = "project_id", required = false) @Positive Long projectId,
                               @RequestParam(value = "date_from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
                               @RequestParam(value = "date_to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo) {
        ArrayList<ReportDto> reports = new ArrayList<>();
        reportService.getAll(new ReportFilter(employeeId, projectId, dateFrom, dateTo))
                .forEach(report -> reports.add(convertToReportDto(report)));
        return reports;
    }

    private static ReportDto convertToReportDto(Report report) {
        return new ReportDto(report.getId(), report.getDescription(), report.getCreatedAt(),
                report.getEmployee().getId(), report.getProject().getId());
    }
}
