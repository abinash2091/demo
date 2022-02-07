package com.springboot.project1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.project1.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	
}
