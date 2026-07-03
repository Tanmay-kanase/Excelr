package com.example.employeeprac.controllers;

import com.example.employeeprac.EmployeepracApplication;
import com.example.employeeprac.services.EmployeeService;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeprac.models.Employee;

@RestController
@RequestMapping("api/employee")
@Slf4j
public class EmployeeController {

    private final EmployeepracApplication employeepracApplication;
    private final EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService, EmployeepracApplication employeepracApplication) {
        this.employeeService = employeeService;
        this.employeepracApplication = employeepracApplication;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Integer id) {
        Employee emp = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee emp) {
        log.info("Employee : {}", emp);
        Employee newEmp = employeeService.creatEmployee(emp);
        return new ResponseEntity<>(newEmp, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer id,
            @RequestBody Employee employee) {
        Employee emp = employeeService.updateEmployee(employee, id);
        return new ResponseEntity<>(emp, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(value = "id") Integer id,
            @RequestBody Employee emp) {
        employeeService.deleteEmployee(id);
        Map<String, Boolean> res = new HashMap<>();
        res.put("deleted", true);
        return ResponseEntity.ok(res);
    }
}
