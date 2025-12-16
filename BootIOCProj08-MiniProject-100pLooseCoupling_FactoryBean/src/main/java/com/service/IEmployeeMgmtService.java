package com.service;

import java.util.List;

import com.model.Employee;

public interface IEmployeeMgmtService {

	public List<Employee> showEmployeesByDesgs(String desg1, String desg2, String desg3) throws Exception;

	public String registerEmployee(Employee emp) throws Exception;
}
