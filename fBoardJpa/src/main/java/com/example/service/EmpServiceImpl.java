package com.example.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Employee;
import com.example.persistence.EmpRepository;

@Service
public class EmpServiceImpl implements EmpService{

	@Autowired
	EmpRepository empRepo;
	
	Logger log = LoggerFactory.getLogger(EmpServiceImpl.class);
	
	public void insertEmp(Employee evo) {
		log.info("[EmpServiceImpl - insertEmp] 요청"+ evo);
		empRepo.save(evo);
		
	}
	public List<Employee> getEmployeeList() {
		log.info("[EmpServiceImpl - getEmployeeList] 요청");
		List<Employee> list = (List<Employee>)empRepo.findAll();
		
		return list;
	}
}
