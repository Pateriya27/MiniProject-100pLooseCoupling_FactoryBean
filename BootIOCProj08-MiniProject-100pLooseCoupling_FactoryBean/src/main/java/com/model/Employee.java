package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Employee {
	private Integer empno;
	@NonNull
	private String ename;
	@NonNull
	private String desg;
	@NonNull
	private Double salary;
	private Double grossSalary;
	private Double netSalary;

}
