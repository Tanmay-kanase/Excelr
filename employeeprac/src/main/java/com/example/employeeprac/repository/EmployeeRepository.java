package com.example.employeeprac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeeprac.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
