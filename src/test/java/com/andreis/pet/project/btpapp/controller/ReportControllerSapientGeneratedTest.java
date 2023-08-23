package com.andreis.pet.project.btpapp.controller;

import com.andreis.pet.project.btpapp.dto.ReportDto;
import com.andreis.pet.project.btpapp.dto.ReportRequestDto;
import com.andreis.pet.project.btpapp.filters.ReportFilter;
import com.andreis.pet.project.btpapp.model.Employee;
import com.andreis.pet.project.btpapp.model.Project;
import com.andreis.pet.project.btpapp.model.Report;
import com.andreis.pet.project.btpapp.service.EmployeeService;
import com.andreis.pet.project.btpapp.service.ProjectService;
import com.andreis.pet.project.btpapp.service.ReportService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReportControllerSapientGeneratedTest {

    //Sapient generated method id: ${2284fc3d-473b-3992-886d-8fe431bd702d}
    @Test()
    void createTest() {
        EmployeeService employeeServiceMock = mock(EmployeeService.class);
        ProjectService projectServiceMock = mock(ProjectService.class);
        ReportService reportServiceMock = mock(ReportService.class);
        ReportController target = new ReportController(employeeServiceMock, projectServiceMock, reportServiceMock);
        Employee employeeMock = mock(Employee.class);
        doReturn(employeeMock).when(employeeServiceMock).getEmployee(0L);
        Project projectMock = mock(Project.class);
        doReturn(projectMock).when(projectServiceMock).getProject(0L);
        LocalDateTime localDateTimeMock = mock(LocalDateTime.class);
        Project project = new Project();
        project.setId(0L);
        Employee employee = new Employee();
        employee.setId(0L);
        Report report = new Report();
        report.setCreatedAt(localDateTimeMock);
        report.setDescription("description1");
        report.setProject(project);
        report.setId(0L);
        report.setEmployee(employee);
        doReturn(report).when(reportServiceMock).save(projectMock, employeeMock, "description1");
        ReportRequestDto reportRequestDto = new ReportRequestDto();
        reportRequestDto.setDescription("description1");
        reportRequestDto.setEmployeeId(0L);
        reportRequestDto.setProjectId(0L);
        ReportDto result = target.create(reportRequestDto);
        ReportDto reportDto = new ReportDto(0L, "description1", localDateTimeMock, 0L, 0L);
        //TODO: Please implement equals method in ReportDto for verification to succeed or you need to adjust respective assertion statements
        assertAll("result", () -> {
            assertThat(result, equalTo(reportDto));
            verify(employeeServiceMock).getEmployee(0L);
            verify(projectServiceMock).getProject(0L);
            verify(reportServiceMock).save(projectMock, employeeMock, "description1");
        });
    }

    //Sapient generated method id: ${9c25887d-767d-38e9-b5ae-c2e4012c49be}
    @Test()
    void getTest() {
        /*
         * TODO: Help needed! Please adjust the input/test parameter values manually to satisfy the requirements of the given test scenario.
         *  The test code, including the assertion statements, has been successfully generated.
         */
        EmployeeService employeeServiceMock = mock(EmployeeService.class);
        ProjectService projectServiceMock = mock(ProjectService.class);
        ReportService reportServiceMock = mock(ReportService.class);
        ReportController target = new ReportController(employeeServiceMock, projectServiceMock, reportServiceMock);
        Iterable<Report> iterableMock = mock(Iterable.class);
        doReturn(iterableMock).when(reportServiceMock).getAll((ReportFilter) any());
        LocalDateTime localDateTimeMock = mock(LocalDateTime.class);
        LocalDateTime localDateTimeMock2 = mock(LocalDateTime.class);
        List<ReportDto> result = target.get(0L, 0L, localDateTimeMock, localDateTimeMock2);
        assertAll("result", () -> {
            assertThat(result.size(), equalTo(0));
            verify(reportServiceMock).getAll((ReportFilter) any());
        });
    }
}
