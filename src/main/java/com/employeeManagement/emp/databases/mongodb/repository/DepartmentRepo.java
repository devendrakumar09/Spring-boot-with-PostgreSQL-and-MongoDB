package com.employeeManagement.emp.databases.mongodb.repository;

import com.employeeManagement.emp.databases.mongodb.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<DepartmentEntity,String> {
}
