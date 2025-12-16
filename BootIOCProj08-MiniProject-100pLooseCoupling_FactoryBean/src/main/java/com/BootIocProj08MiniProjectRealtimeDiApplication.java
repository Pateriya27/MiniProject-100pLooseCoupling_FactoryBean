package com;

import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.controller.EmployeeOperationsController;
import com.model.Employee;

@SpringBootApplication
public class BootIocProj08MiniProjectRealtimeDiApplication {

//    @Bean(name = "tomcatDS")
//    org.apache.tomcat.jdbc.pool.DataSource createTomcatDS(Environment env) throws Exception {
//
//	    System.out.println("=== Creating Tomcat JDBC DataSource ===");
//
//	    PoolProperties p = new PoolProperties();
//	    p.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
//	    p.setUrl(env.getRequiredProperty("spring.datasource.url"));
//	    p.setUsername(env.getRequiredProperty("spring.datasource.username"));
//	    p.setPassword(env.getRequiredProperty("spring.datasource.password"));
//
//	    // Pool settings from properties
//	    p.setInitialSize(Integer.parseInt(env.getRequiredProperty("tomcat.initial-size")));
//	    p.setMaxActive(Integer.parseInt(env.getRequiredProperty("tomcat.max-active")));
//	    p.setMinIdle(Integer.parseInt(env.getRequiredProperty("tomcat.min-idle")));
//	    p.setMaxIdle(Integer.parseInt(env.getRequiredProperty("tomcat.max-idle")));
//	    p.setMaxWait(Integer.parseInt(env.getRequiredProperty("tomcat.max-wait")));
//
//	    // Recommended validation
//	    p.setTestOnBorrow(true);
//	    p.setValidationQuery("SELECT 1");
//
//	    // Create DataSource
//	    org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
//	    ds.setPoolProperties(p);
//
//	    return ds;
//	}


	public static void main(String[] args) {

		System.out.println("BootIocProj05MiniProjectRealtimeDiApplication.main()-start");

		try (ConfigurableApplicationContext ctx = SpringApplication
				.run(BootIocProj08MiniProjectRealtimeDiApplication.class, args); Scanner sc = new Scanner(System.in);) {
			System.out.println(ctx.getBean(DataSource.class).getClass().getName());

			EmployeeOperationsController controller = ctx.getBean("empController", EmployeeOperationsController.class);
			boolean loopVar = true;
			while (loopVar) {
				System.out.println(
						"Welcome to Employee Management System :: \n1.View Records Based on Desg\n2.Insert Record\n3.Exit\nEnter Your Choice : ");

				int choice = Integer.parseInt(sc.nextLine());

				switch (choice) {
				case 1 -> {
					System.out.print("Enter Desg 1 : ");
					String desg1 = sc.nextLine();
					System.out.print("Enter Desg 2 : ");
					String desg2 = sc.nextLine();
					System.out.print("Enter Desg 3 : ");
					String desg3 = sc.nextLine();

					try {
						List<Employee> list = controller.fetchEmployeesByDesgs(desg1, desg2, desg3);
						list.forEach(System.out::println);
						System.out.println();
					} catch (Exception e) {
						System.out.println("Internal Error----" + e.getMessage());
						e.printStackTrace();
					}
				}

				case 2 -> {
					System.out.print("Enter Employee Name : ");
					String name = sc.nextLine();
					System.out.print("Enter Employee Desg : ");
					String desg = sc.nextLine();
					System.out.print("Enter Employee Salary : ");
					Double sal = Double.parseDouble(sc.nextLine());
					Employee e = new Employee(name, desg, sal);
					String registration = controller.processEmployeeRegistration(e);
					System.out.println(registration);
					System.out.println();
				}

				case 3 -> {
					System.out.println("Exiting........");
					loopVar = false;
				}

				default -> System.out.println("Unexpected value: " + choice);
				}
			}

		} catch (Exception e) {
			System.out.println("Internal Error----" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("BootIocProj05MiniProjectRealtimeDiApplication.main()-End");
	}

}
