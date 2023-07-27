package com.andreis.pet.project.btpapp.repository;

import com.andreis.pet.project.btpapp.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ReportRepository extends JpaRepository<Report, Long>, QuerydslPredicateExecutor<Report> {
}
