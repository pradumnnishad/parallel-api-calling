package com.coder.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
<<<<<<< HEAD
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
=======
>>>>>>> 3e58c6b86bcdad02e12416fd2af53c7ec671ff76

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
<<<<<<< HEAD
	//getting salary from another microservice
	private final static String GET_SALARY_URL = "http://localhost:8081/salary";
	
	private final Executor executor = Executors.newFixedThreadPool(2);
=======
	private final static String GET_SALARY_URL = "http://localhost:8081/salary";
>>>>>>> 3e58c6b86bcdad02e12416fd2af53c7ec671ff76
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

<<<<<<< HEAD
	/*
	 * @Override public Map<String, Object> getSpecificEmpData(int id) {
	 * 
	 * EmployeeData obj = repo.findByCustom(id); if (obj == null) { throw new
	 * RuntimeException("Employee not found with ID: " + id); }
	 * System.out.println(obj); Map<String,Object> map=new HashMap<>();
	 * map.put("eid",obj.getEmpid()); map.put("ename", obj.getEmpName());
	 * map.put("depart",obj.getDepartment());
	 * 
	 * 
	 * ExecutorService service = Executors.newFixedThreadPool(1);
	 * 
	 * 
	 * try { Future<Integer> salaryFuture = service.submit(() ->{ return
	 * restTemplate.getForObject(GET_SALARY_URL, Integer.class); });
	 * 
	 * Integer salary = salaryFuture.get(); map.put("salary", salary); }
	 * catch(Exception e) { e.printStackTrace(); map.put("salary", 0); } finally {
	 * service.shutdown(); }
	 * 
	 * 
	 * // Integer salary = restTemplate.getForObject(GET_SALARY_URL, Integer.class);
	 * // map.put("salary", salary);
	 * 
	 * System.out.println(map); return map; }
	 */
	
	@Override
	public Map<String, Object> getSpecificEmpData(int id) {
//two thread is created
        CompletableFuture<EmployeeData> empFuture = CompletableFuture.supplyAsync(() -> {
        	System.out.println(" DB Call Started: " + Thread.currentThread().getName() + " at " + System.currentTimeMillis());
        return repo.findByCustom(id); 
        }, executor);
        CompletableFuture<Integer> salaryFuture = CompletableFuture.supplyAsync(() -> {
        	System.out.println("Salary API Call Started: " + Thread.currentThread().getName() + " at " + System.currentTimeMillis());
            return restTemplate.getForObject(GET_SALARY_URL, Integer.class);
        }, executor);

        Map<String, Object> map = new HashMap<>();

        try {
           
            EmployeeData emp = empFuture.get();
            Integer salary = salaryFuture.get();

            if (emp != null) {
                map.put("eid", emp.getEmpid());
                map.put("ename", emp.getEmpName());
                map.put("depart", emp.getDepartment());
            } else {
                map.put("error", "Employee not found");
            }

            map.put("salary", salary != null ? salary : 0);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "Failed to fetch data");
        }


        return map;
    
	}



=======
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

>>>>>>> 3e58c6b86bcdad02e12416fd2af53c7ec671ff76
}
