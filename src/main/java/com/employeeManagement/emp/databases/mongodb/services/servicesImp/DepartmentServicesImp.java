package com.employeeManagement.emp.databases.mongodb.services.servicesImp;

import com.employeeManagement.emp.databases.mongodb.entity.DepartmentEntity;
import com.employeeManagement.emp.databases.mongodb.repository.DepartmentRepo;
import com.employeeManagement.emp.databases.mongodb.services.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServicesImp implements DepartmentServices {

    @Autowired
    private DepartmentRepo departmentRepo;
    @Override
    public List<DepartmentEntity> getDepartments() {

        return this.departmentRepo.findAll();
    }

    @Override
    public ResponseEntity<Void> postDepartment(DepartmentEntity departmentEntity) {
        DepartmentEntity department = departmentEntity;
        department.setCreatedAt(new Date());
        DepartmentEntity saved = this.departmentRepo.save(department);
         if (saved == null){
             return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
         }else {
             return new ResponseEntity<Void>(HttpStatus.OK);
         }
    }

    @Override
    public DepartmentEntity getDepartmentById(String id) {

        Optional<DepartmentEntity> department = this.departmentRepo.findById(id);
        if (department.isPresent()){
            return department.get();
        }else {
            return null;
        }
    }

    @Override
    public ResponseEntity<Void> updateDepartmentById(DepartmentEntity departmentEntity, String id) {
        Optional<DepartmentEntity> department = this.departmentRepo.findById(id);
        if (department.isPresent()){
            DepartmentEntity existDepartment = department.get();
            existDepartment.setDepartmentName(departmentEntity.getDepartmentName());
            existDepartment.setUpdatedAt(new Date());
            this.departmentRepo.save(existDepartment);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> deleteDepartmentById(String id) {
        Optional<DepartmentEntity> department = this.departmentRepo.findById(id);
        if (department.isPresent()){
            this.departmentRepo.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
