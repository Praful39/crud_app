package com.assigenment.crudOperation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assigenment.crudOperation.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee , Long> {

}
