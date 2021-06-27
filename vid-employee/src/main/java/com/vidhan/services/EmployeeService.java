package com.vidhan.services;

import java.util.List;

import com.vidhan.models.Employee;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee createEmployee(Employee employee);

    Employee fetchStudentById(long id);
}
