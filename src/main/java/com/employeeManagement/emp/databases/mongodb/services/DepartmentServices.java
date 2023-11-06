package com.employeeManagement.emp.databases.mongodb.services;

import com.employeeManagement.emp.databases.mongodb.entity.DepartmentEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartmentServices {

    public List<DepartmentEntity> getDepartments();
    public ResponseEntity<Void> postDepartment(DepartmentEntity departmentEntity);
    public DepartmentEntity getDepartmentById(String id);
    public ResponseEntity<Void> updateDepartmentById(DepartmentEntity departmentEntity, String id);
    public ResponseEntity<Void> deleteDepartmentById(String id);

}
