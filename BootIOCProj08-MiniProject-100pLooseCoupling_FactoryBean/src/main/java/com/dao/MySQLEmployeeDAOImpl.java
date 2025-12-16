package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.model.Employee;

@Repository("MySQLEmpDAO")
public class MySQLEmployeeDAOImpl implements IEmployeeDAO {

	private static final String GET_EMPS_BY_DESG = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN(?,?,?) ORDER BY JOB";

	private static final String INSERT_EMP_QUERY = "INSERT INTO EMP(ENAME,JOB,SAL) VALUES(?,?,?) ";

	public MySQLEmployeeDAOImpl(DataSource ds) {
		System.out.println("MySQLEmployeeDAOImpl.OracleEmployeeDAOImpl()");
		this.ds = ds;
	}

	private DataSource ds;

	@Override
	public List<Employee> showEmployeesByDesgs(String desg1, String desg2, String desg3) throws Exception {
		System.out.println("EmployeeDAOImpl.showEmployeesByDesgs()");
		List<Employee> list = null;

		try (Connection con = ds.getConnection(); PreparedStatement pstm = con.prepareStatement(GET_EMPS_BY_DESG)) {

			pstm.setString(1, desg1);
			pstm.setString(2, desg2);
			pstm.setString(3, desg3);

			try (ResultSet rs = pstm.executeQuery();) {
				list = new ArrayList<>();
				while (rs.next()) {

					Employee emp = new Employee();
					emp.setEmpno(rs.getInt(1));
					emp.setEname(rs.getString(2));
					emp.setDesg(rs.getString(3));
					emp.setSalary(rs.getDouble(4));

					list.add(emp);
				}
			}

		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}

		return list;
	}

	@Override
	public int insertEmployee(Employee emp) throws Exception {
		int result = 0;

		try (Connection con = ds.getConnection()) {

			PreparedStatement ps = con.prepareStatement(INSERT_EMP_QUERY);

			ps.setString(1, emp.getEname());
			ps.setString(2, emp.getDesg());
			ps.setDouble(3, emp.getSalary());

			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return result;
	}
}
