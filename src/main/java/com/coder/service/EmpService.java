package com.coder.service;



import java.util.Map;

import com.coder.modal.Employee;

public interface EmpService {

	public Employee getEmpDetail(int id);
	
	public String addEmployee(Employee emp);
	
	public Map<String, Object> getSpecificEmpData(int id);
}
