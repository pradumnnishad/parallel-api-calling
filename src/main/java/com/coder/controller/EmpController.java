package com.coder.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coder.modal.Employee;
import com.coder.service.EmpService;

@RestController
public class EmpController {

	@Autowired
	EmpService eservice;
	
	@GetMapping("/getSpecificData/{id}")
	public ResponseEntity<Map<String, Object>> getEmpSpecificData(@PathVariable int id){
		
		Map<String, Object> empData = eservice.getSpecificEmpData(id);

        if (empData != null && !empData.isEmpty()) {
            return ResponseEntity.ok(empData);  // 200 OK with JSON
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
	}
	
	@GetMapping("/getData/{id}")
	public ResponseEntity<Employee> getEmpdata(@PathVariable int id){
		
		Employee empData = eservice.getEmpDetail(id);

        if (empData != null ) {
            return ResponseEntity.ok(empData);  // 200 OK with JSON
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
	}
	
	@PostMapping(value="/addEmployee")
	private ResponseEntity<String> bookTicket(@RequestBody Employee emp){
		System.out.println(emp.getEmpName());
		String s = eservice.addEmployee(emp);
		return new ResponseEntity<>(s,HttpStatus.CREATED);
	}
}
