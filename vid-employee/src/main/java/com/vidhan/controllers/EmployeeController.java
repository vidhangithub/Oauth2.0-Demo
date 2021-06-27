package com.vidhan.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vidhan.models.Employee;
import com.vidhan.repositories.EmployeeRepo;
import com.vidhan.services.EmployeeService;

@CrossOrigin(origins =  "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeService empService;

    @Autowired
    private EmployeeRepo empRepo;

    @GetMapping("/employees")
    @PreAuthorize("hasAnyRole('ROLE_operator','ROLE_admin')")
    //@PreAuthorize("hasAuthority('delete_profile')")
    public List<Employee> getAllEmployees() {
        return empService.getAllEmployees();
    }

    @PostMapping("/employee")
    @PreAuthorize("hasAnyRole('ROLE_operator','ROLE_admin')")
    public Employee createEmployee(@RequestBody Employee employee) {
        return empService.createEmployee(employee);
    }

    @GetMapping("/employee/{empId}")
    @PreAuthorize("hasAnyRole('ROLE_operator','ROLE_admin')")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("empId") final long id) {
        Employee employee = empService.fetchStudentById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        } else return ResponseEntity.ok().body(employee);
    }

    @PutMapping("/employee/{id}")
    @PreAuthorize("hasAnyRole('ROLE_operator','ROLE_admin')")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        Employee employee = empService.fetchStudentById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        } else {
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setEmailId(employeeDetails.getEmailId());
            Employee updatedEmployee = empRepo.save(employee);
            return ResponseEntity.ok().body(updatedEmployee);
        }
    }


    @DeleteMapping("/employee/{id}")
    @PreAuthorize("hasAuthority('delete_profile')")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        Employee employee = empService.fetchStudentById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        } else {
            empRepo.delete(employee);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return ResponseEntity.ok(response);
        }
    }

}
