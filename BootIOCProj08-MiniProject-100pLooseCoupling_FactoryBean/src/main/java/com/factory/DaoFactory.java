package com.factory;

import java.util.Map;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dao.IEmployeeDAO;

@Component("daoFac")
public class DaoFactory implements FactoryBean<IEmployeeDAO> {
	@Value("${dao.id}")
	private String dao;

	@Autowired
	private Map<String, IEmployeeDAO> daos;

	public DaoFactory() {
		System.out.println("DaoFactory.DaoFactory()");
	}

	@Override
	public @Nullable IEmployeeDAO getObject() throws Exception {
		return daos.get(dao);
	}

	@Override
	public @Nullable Class<?> getObjectType() {

		return IEmployeeDAO.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
