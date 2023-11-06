package com.employeeManagement.emp.databases.postgreSQL.services;

import com.employeeManagement.emp.databases.postgreSQL.entity.EmployeeEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeServices {

    public List<EmployeeEntity> getEmployees();
    public ResponseEntity<Void> postEmployee(EmployeeEntity employeeEntity);
    public EmployeeEntity getEmployeeById(long id);
    public ResponseEntity<Void> updateEmployeeById(EmployeeEntity employeeEntity,long id);
    public ResponseEntity<Void> deleteEmployeeById(long id);
}
