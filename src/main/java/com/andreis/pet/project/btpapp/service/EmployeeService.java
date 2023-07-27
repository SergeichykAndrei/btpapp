package com.andreis.pet.project.btpapp.service;

import com.andreis.pet.project.btpapp.model.Employee;
import com.andreis.pet.project.btpapp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ObjectNotFoundException(employeeId, "Employee"));
    }
}
