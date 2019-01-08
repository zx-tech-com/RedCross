package com.zx.redcross.serviceimpl.index;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zx.redcross.dao.index.IEmployee;
import com.zx.redcross.entity.Employee;
import com.zx.redcross.service.index.IEmployeeServ;

@Service
public class EmployeeServImpl implements IEmployeeServ{

	@Resource
	private IEmployee employeeMapper;
	
	public EmployeeServImpl() {
	}
	
	@Cacheable("employees2")
	public List<Employee> listEmployees() {
		return employeeMapper.listEmployees();
	}

}
