package com.andreis.pet.project.btpapp.filters;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import java.util.Objects;

import static com.andreis.pet.project.btpapp.model.QReport.report;


public class ReportFilterBuilder {

    public Predicate build(ReportFilter filter) {
        BooleanBuilder builder = new BooleanBuilder();
        if (Objects.nonNull(filter.getEmployeeId())) {
            builder.and(report.employee.id.eq(filter.getEmployeeId()));
        }
        if (Objects.nonNull(filter.getProjectId())) {
            builder.and(report.project.id.eq(filter.getProjectId()));
        }
        if (Objects.nonNull(filter.getDateFrom())) {
            builder.and(report.createdAt.after(filter.getDateFrom()));
        }
        if (Objects.nonNull(filter.getDateTo())) {
            builder.and(report.createdAt.before(filter.getDateTo()));
        }

        return builder;
    }

}
