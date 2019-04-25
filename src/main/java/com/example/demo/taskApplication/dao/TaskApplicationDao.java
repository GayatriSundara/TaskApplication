package com.example.demo.taskApplication.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.taskApplication.model.Task;

@Repository
public interface TaskApplicationDao  extends CrudRepository<Task, Long> {
	
	

}
