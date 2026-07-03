package com.example.employeeprac.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.employeeprac.models.Employee;
import com.example.employeeprac.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not found"));
    }

    public Employee creatEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee, Integer id) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));
        existing.setName(employee.getName());
        existing.setSalary(employee.getSalary());
        existing.setDepartment(employee.getDepartment());
        return employeeRepository.save(existing);
    }

    public void deleteEmployee(Integer id) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));
        employeeRepository.delete(existing);
    }
}
