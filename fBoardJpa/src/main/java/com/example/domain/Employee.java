package com.example.domain;

import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;


import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
//@Table(name="emp_t")
public class Employee {
	
	@Id
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;
	private Integer sal;
	@Column(columnDefinition = "integer default 0", insertable = false)
	private Integer comm;
	@UpdateTimestamp
	private Date hidredate;
	private Integer deptno;
}
