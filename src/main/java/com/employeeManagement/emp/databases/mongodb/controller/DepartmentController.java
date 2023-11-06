package com.employeeManagement.emp.databases.mongodb.controller;

import com.employeeManagement.emp.databases.mongodb.entity.DepartmentEntity;
import com.employeeManagement.emp.databases.mongodb.services.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department/")
public class DepartmentController {

    @Autowired
    private DepartmentServices departmentServices;

    @GetMapping
    public List<DepartmentEntity> getAll(){
        return this.departmentServices.getDepartments();
    }


    @PostMapping
    public ResponseEntity<Void> newEntity(@RequestBody DepartmentEntity departmentEntity){
        this.departmentServices.postDepartment(departmentEntity);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public DepartmentEntity getById(@PathVariable String id){
        return this.departmentServices.getDepartmentById(id);
    }

    @PutMapping
    public ResponseEntity<Void> updateById(@RequestBody DepartmentEntity departmentEntity, @PathVariable String id){
        return this.departmentServices.updateDepartmentById(departmentEntity,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        return  this.departmentServices.deleteDepartmentById(id);

    }

}
