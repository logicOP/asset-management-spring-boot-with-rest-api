package com.project.ams.service;


import com.project.ams.data.models.Employee;
import com.project.ams.data.payloads.request.EmployeeRequest;
import com.project.ams.data.payloads.response.MessageResponse;
import com.project.ams.data.repository.EmployeeRepository;
import com.project.ams.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public MessageResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee newEmployee = new Employee();
        newEmployee.setFirstName(employeeRequest.getFirstName());
        newEmployee.setLastname(employeeRequest.getLastname());
        newEmployee.setPhoneNumber(employeeRequest.getPhoneNumber());
        newEmployee.setEmail(employeeRequest.getEmail());
        newEmployee.setSalary(employeeRequest.getSalary());
        newEmployee.setDepartment(employeeRequest.getDepartment());
        employeeRepository.save(newEmployee);
        return new MessageResponse("New Employee created successfully");

    }

    @Override
    public Optional<Employee> updateEmployee(Integer employeeId, EmployeeRequest employeeRequest)  throws ResourceNotFoundException{
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty()){
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }
        else
            employee.get().setFirstName(employeeRequest.getFirstName());
        employee.get().setLastname(employeeRequest.getLastname());
        employee.get().setPhoneNumber(employeeRequest.getPhoneNumber());
        employee.get().setEmail(employeeRequest.getEmail());
        employee.get().setSalary(employeeRequest.getSalary());
        employee.get().setDepartment(employeeRequest.getDepartment());
        employeeRepository.save(employee.get());
        return employee;
    }


    @Override
    public Optional<Employee> changeDepartment(Integer employeeId, EmployeeRequest employeeRequest)  throws ResourceNotFoundException{
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty()){
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }
        else {
            employee.get().setDepartment(employeeRequest.getDepartment());
            employeeRepository.save(employee.get());
        }
        return employee;
    }

    @Override
    public Employee getASingleEmployee(Integer employeeId) throws ResourceNotFoundException{
        return employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getEmployeesByDepartment(String department) {
        if (department != null) {
            return employeeRepository.findAll(department);
        }
        return employeeRepository.findAll();
    }
    @Override
    public void deleteEmployee(Integer employeeId) throws ResourceNotFoundException {
        if (employeeRepository.getById(employeeId).getId().equals(employeeId)){
            employeeRepository.deleteById(employeeId);
        }
        else throw new ResourceNotFoundException("Employee", "id", employeeId);
    }
}