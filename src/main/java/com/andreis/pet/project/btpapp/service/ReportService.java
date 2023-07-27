package com.andreis.pet.project.btpapp.service;

import com.andreis.pet.project.btpapp.filters.ReportFilter;
import com.andreis.pet.project.btpapp.filters.ReportFilterBuilder;
import com.andreis.pet.project.btpapp.model.Employee;
import com.andreis.pet.project.btpapp.model.Report;
import com.andreis.pet.project.btpapp.model.enums.Project;
import com.andreis.pet.project.btpapp.repository.ReportRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportService {

    private final ReportRepository reportRepository;

    @Transactional
    public Report save(Project project, Employee employee, String description) {
        return reportRepository.save(new Report(project, employee, description));
    }

    public Iterable<Report> getAll(ReportFilter reportFilter) {
        ReportFilterBuilder reportFilterBuilder = new ReportFilterBuilder();
        Predicate predicate = reportFilterBuilder.build(reportFilter);
        return reportRepository.findAll(predicate);
    }
}
