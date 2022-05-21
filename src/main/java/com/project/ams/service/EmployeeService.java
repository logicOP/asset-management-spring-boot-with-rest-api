package com.project.ams.service;

import com.project.ams.data.models.Employee;
import com.project.ams.data.payloads.request.EmployeeRequest;
import com.project.ams.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface EmployeeService {
    MessageResponse createEmployee(EmployeeRequest employeeRequest);
    Optional<Employee> updateEmployee(Integer employeeId, EmployeeRequest employeeRequest);
    void deleteEmployee(Integer employeeId);
    Employee getASingleEmployee(Integer employeeId);
    List<Employee> getAllEmployee();
}