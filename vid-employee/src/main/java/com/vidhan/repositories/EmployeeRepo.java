package com.vidhan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vidhan.models.Employee;

@Repository
public interface EmployeeRepo  extends  JpaRepository<Employee, Long>{

}