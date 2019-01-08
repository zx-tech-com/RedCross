package com.zx.redcross.dao.index;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zx.redcross.entity.Employee;

public interface IEmployee {
	
	List<Employee> listEmployees();
	
	Integer addEmployee(@Param("employee")Employee empl);
}
