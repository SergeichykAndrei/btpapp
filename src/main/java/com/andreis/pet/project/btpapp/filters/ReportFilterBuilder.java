package com.andreis.pet.project.btpapp.filters;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import static com.andreis.pet.project.btpapp.model.QReport.report;


public class ReportFilterBuilder {

    public Predicate build(ReportFilter filter) {
        BooleanBuilder builder = new BooleanBuilder();
        if (filter.getEmployeeId() != null) {
            builder.and(report.employee.id.eq(filter.getEmployeeId()));
        }
        if (filter.getProjectId() != null) {
            builder.and(report.project.id.eq(filter.getProjectId()));
        }
        if (filter.getDateFrom() != null) {
            builder.and(report.createdAt.after(filter.getDateFrom()));
        }
        if (filter.getDateTo() != null) {
            builder.and(report.createdAt.before(filter.getDateTo()));
        }

        return builder;
    }

}
