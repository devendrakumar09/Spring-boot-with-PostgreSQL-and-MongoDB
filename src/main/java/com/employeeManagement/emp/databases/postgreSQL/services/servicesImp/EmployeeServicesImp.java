package com.employeeManagement.emp.databases.postgreSQL.services.servicesImp;

import com.employeeManagement.emp.databases.postgreSQL.entity.EmployeeEntity;
import com.employeeManagement.emp.databases.postgreSQL.repository.EmployeeRepo;
import com.employeeManagement.emp.databases.postgreSQL.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServicesImp implements EmployeeServices {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Override
    public List<EmployeeEntity> getEmployees() {
        return this.employeeRepo.findAll();
    }

    @Override
    public ResponseEntity<Void> postEmployee(EmployeeEntity employeeEntity) {
        EmployeeEntity employee = employeeEntity;
        employee.setCreatedAt(new Date());
         EmployeeEntity saved = this.employeeRepo.save(employee);
         if(saved == null){
             return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
         }else {
             return new ResponseEntity<Void>(HttpStatus.CREATED);
         }
    }

    @Override
    public EmployeeEntity getEmployeeById(long id) {
        Optional<EmployeeEntity> emp = this.employeeRepo.findById(id);
        if (emp.isPresent()){
            return emp.get();
        }else {
            return null ;
        }
    }

    @Override
    public ResponseEntity<Void> updateEmployeeById(EmployeeEntity employeeEntity, long id) {
        Optional<EmployeeEntity> emp = this.employeeRepo.findById(id);
        if (emp.isPresent()){
            EmployeeEntity existEmp = emp.get();
            existEmp.setFirstName(employeeEntity.getFirstName());
            existEmp.setLastName(employeeEntity.getFirstName());
            existEmp.setDepartmentId(employeeEntity.getDepartmentId());
            existEmp.setUpdatedAt(new Date());
            this.employeeRepo.save(existEmp);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Void> deleteEmployeeById(long id) {
        Optional<EmployeeEntity> emp = this.employeeRepo.findById(id);
        if (emp.isPresent()){
            this.employeeRepo.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
