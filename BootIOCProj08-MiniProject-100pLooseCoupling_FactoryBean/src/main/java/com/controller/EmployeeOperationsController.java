package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.model.Employee;
import com.service.IEmployeeMgmtService;

@Controller("empController")
public class EmployeeOperationsController {
	@Autowired
	private IEmployeeMgmtService employeeMgmtService;

	public EmployeeOperationsController() {
		System.out.println("EmployeeOperationsController.EmployeeOperationsController()");
	}

	public List<Employee> fetchEmployeesByDesgs(String desg1, String desg2, String desg3) throws Exception {
		System.out.println("EmployeeOperationsController.fetchEmployeesByDesgs()");
		List<Employee> list = employeeMgmtService.showEmployeesByDesgs(desg1, desg2, desg3);
		return list;
	}

	public String processEmployeeRegistration(Employee emp) throws Exception {
		return employeeMgmtService.registerEmployee(emp);
	}

}
