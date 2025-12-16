package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.IEmployeeDAO;
import com.model.Employee;

@Service("empService")
public class EmployeeMgmtServiceImpl implements IEmployeeMgmtService {
	@Autowired
	@Qualifier("daoFac")
	private IEmployeeDAO employeeDAO;

	public EmployeeMgmtServiceImpl() {
		System.out.println("EmployeeMgmtServiceImpl.EmployeeMgmtServiceImpl()");
	}

	@Override
	public List<Employee> showEmployeesByDesgs(String desg1, String desg2, String desg3) throws Exception {

		System.out.println("EmployeeMgmtServiceImpl.showEmployeesByDesgs()");

		desg1 = desg1.toUpperCase();
		desg2 = desg2.toUpperCase();
		desg3 = desg3.toUpperCase();

		// use service
		List<Employee> list = employeeDAO.showEmployeesByDesgs(desg1, desg2, desg3);
		list.forEach((e -> {
			// Business logic calculations
			e.setGrossSalary(e.getSalary() + (e.getSalary() * 0.4));
			e.setNetSalary(e.getGrossSalary() - (e.getGrossSalary() * 0.2));
		}));
		return list;
	}

	@Override
	public String registerEmployee(Employee emp) throws Exception {

		emp.setEname(emp.getEname().toUpperCase());
		emp.setDesg(emp.getDesg().toUpperCase());

		int result = employeeDAO.insertEmployee(emp);
		return result == 0 ? " Employee registration failed " : " Employee registered Successfully ";
	}

}
