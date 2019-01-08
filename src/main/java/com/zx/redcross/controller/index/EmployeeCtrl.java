package com.zx.redcross.controller.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.redcross.entity.Employee;
import com.zx.redcross.service.index.IEmployeeServ;

@Controller
public class EmployeeCtrl {

	@Autowired
	private IEmployeeServ employeeServImpl;
	
	@RequestMapping("employees")
	@ResponseBody
	public List<Employee> listEmployees() {
		return employeeServImpl.listEmployees();
	}
	
}
