package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.service.EmpService;

@Controller
@RequestMapping("emp")
public class EmpController {

	Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	@Autowired
	private EmpService empService;
	
	@RequestMapping("/{step}")
	public String viewEmp(@PathVariable String step) {
		
		logger.info("[EmpController] 요청 " + step);
		
		return "emp/" + step;
	}
	
	
	@RequestMapping("/insertEmp")
	public String insertEmp(Employee evo) {
		logger.info("[EmpController - insertEmp] 요청");
		
		empService.insertEmp(evo);
		
		return "redirect:getEmployeeList";
	}
	
	@RequestMapping("/getEmployeeList")
	public void getEmployeeList(Model m) {
		logger.info("[EmpController - getEmployeeList] 요청");
		
		List<Employee> list = empService.getEmployeeList();
		
		m.addAttribute("empList", list);
	}
}
