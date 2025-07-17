package com.coder.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.coder.modal.Employee;
import com.coder.modal.EmployeeData;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer>{

	@Query("Select e.empid as empid,e.empName as empName,e.department as department from Employee e where e.empid = :eid")
	EmployeeData findByCustom(@Param("eid")  int eid);

}
