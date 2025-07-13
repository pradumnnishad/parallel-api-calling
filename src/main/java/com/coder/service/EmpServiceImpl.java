package com.coder.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coder.modal.Employee;
import com.coder.modal.EmployeeData;
import com.coder.repo.EmpRepo;
import com.coder.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	EmpRepo repo;
	@Autowired
	RestTemplate restTemplate;
	private final static String GET_SALARY_URL = "http://localhost:8081/salary";
	@Override
	public Employee getEmpDetail(int id) {
		
		return repo.findById(id).orElseThrow();
	}

	@Override
	public String addEmployee(Employee emp) {
		
		 Employee e=repo.save(emp);
		 if(e!=null)
			 return "Employee Detail is added";
		return "failed to add Employee detail";
	}

	@Override
	public Map<String, Object> getSpecificEmpData(int id) {
		
		EmployeeData obj = repo.findByCustom(id);
		if (obj == null) {
		    throw new RuntimeException("Employee not found with ID: " + id);
		}
		System.out.println(obj);
		Map<String,Object> map=new HashMap<>();
		map.put("eid",obj.getEmpid());
		map.put("ename", obj.getEmpName());
		map.put("depart",obj.getDepartment());
		
	    Integer salary = restTemplate.getForObject(GET_SALARY_URL, Integer.class);
	    map.put("salary", salary);
	    
	    System.out.println(map);
		return map;
	}

}
