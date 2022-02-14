package com.springboot.project1.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.project1.exception.ResourceNotFound;
import com.springboot.project1.model.Employee;
import com.springboot.project1.repository.EmployeeRepository;
import com.springboot.project1.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee = employeeRepository.findById(id);
//		if(employee.isPresent()) {
//			return employee.get();
//		} else {
//			throw new ResourceNotFound("Employee", "Id", id);
//		}
		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Employee", "Id", id));

	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// check if employee with given id exists in db
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Employee", "Id", id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail()); 
		employeeRepository.save(existingEmployee);
		
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		//check whether the employee exist in db
		employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Employee", "Id", id));
		employeeRepository.deleteById(id);
	}

}
