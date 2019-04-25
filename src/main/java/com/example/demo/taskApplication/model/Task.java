package com.example.demo.taskApplication.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "TASK")
public class Task {
	
	@Column(name = "Priority")
	private String priority;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "Label")
	private String label;
	
	@Column(name = "Start_date")
	private Date startdate;
	
	@Column(name = "End_date")
	private Date endDate;
	
	@Id
	@GeneratedValue
	@Column(name = "Id", nullable = false)
	private Long id;
	
	
	
	
	
	
	

}
