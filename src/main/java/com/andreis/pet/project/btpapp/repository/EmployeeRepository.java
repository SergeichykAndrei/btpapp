package com.andreis.pet.project.btpapp.repository;

import com.andreis.pet.project.btpapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
