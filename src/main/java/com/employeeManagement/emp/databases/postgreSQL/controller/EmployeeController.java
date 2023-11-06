package com.employeeManagement.emp.databases.postgreSQL.controller;

import com.employeeManagement.emp.databases.postgreSQL.entity.EmployeeEntity;
import com.employeeManagement.emp.databases.postgreSQL.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/emp")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    @GetMapping

    public List<EmployeeEntity> getAll(){
        return this.employeeServices.getEmployees();
    }

    @PostMapping
    public ResponseEntity<Void> newEntit(@RequestBody EmployeeEntity employeeEntity){
        return this.employeeServices.postEmployee(employeeEntity);
    }

    @GetMapping("/{id}")
    public EmployeeEntity getById(@PathVariable long id){
        return this.employeeServices.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@RequestBody EmployeeEntity employeeEntity, @PathVariable long id){
        return this.employeeServices.updateEmployeeById(employeeEntity,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        return this.employeeServices.deleteEmployeeById(id);
    }
}
