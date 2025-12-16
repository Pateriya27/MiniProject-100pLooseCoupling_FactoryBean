package com.dao;

import java.util.List;

import com.model.Employee;

public interface IEmployeeDAO {
	public List<Employee> showEmployeesByDesgs(String desg1, String desg2, String desg3) throws Exception;

	public int insertEmployee(Employee emp) throws Exception;

}
