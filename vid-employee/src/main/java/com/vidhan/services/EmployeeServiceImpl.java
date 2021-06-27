package com.vidhan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidhan.models.Employee;
import com.vidhan.repositories.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo empRepo;

    @Override
    public List<Employee> getAllEmployees() {
        return empRepo.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return empRepo.save(employee);
    }

    @Override
    public Employee fetchStudentById(long id) {
        if( empRepo.findById(id).isPresent())
        {
            return empRepo.findById(id).get();
        } else  return null;
    }



}
