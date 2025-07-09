package com.example.service;

import java.util.List;

import com.example.domain.Employee;

public interface EmpService {
	public void insertEmp(Employee evo);
	public List<Employee> getEmployeeList();
}
