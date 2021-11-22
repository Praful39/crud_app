package com.assigenment.crudOperation.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assigenment.crudOperation.exception.ResourceNotFoundException;
import com.assigenment.crudOperation.model.Employee;
import com.assigenment.crudOperation.repository.EmployeeRepository;

@RestController 
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository EmployeeRepository ;
	

	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return EmployeeRepository.findAll();
	}
	//create employee
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return EmployeeRepository.save(employee);
	}
	//get employee by id
	
	@GetMapping("employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") long employeeId) throws ResourceNotFoundException{
		Employee employee = EmployeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee Not Found for this id ::" + employeeId));
		return ResponseEntity.ok().body(employee);
	}
	//update employee
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") long employeeId, @RequestBody Employee employeeDetails) throws ResourceNotFoundException{
			Employee employee = EmployeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee Not Found for this id ::" + employeeId));
		employee.setFirsName(employeeDetails.getFirsName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		EmployeeRepository.save(employee);
		return ResponseEntity.ok().body(employee);
	}
	//delete employee by id
	
	//update employee
	
	
	@DeleteMapping("/employess/{id}") 
	public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") long employeeId) throws ResourceNotFoundException{
		EmployeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee Not Found for this id ::" + employeeId));
	
	EmployeeRepository.deleteById(employeeId);
	return ResponseEntity.ok().build();
	}
	

}
