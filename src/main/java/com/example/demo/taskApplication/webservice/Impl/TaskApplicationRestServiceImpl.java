package com.example.demo.taskApplication.webservice.Impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.example.demo.taskApplication.dao.TaskApplicationDao;
import com.example.demo.taskApplication.model.Task;

import io.swagger.annotations.ApiOperation;


@Controller
public class TaskApplicationRestServiceImpl  {
	
	 private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	public TaskApplicationDao taskDao;

	/**
	 * Adds the task.
	 *
	 * @param task the task
	 * @return the string
	 */
	@RequestMapping(method = RequestMethod.POST, value="/Add")
	@ResponseBody
	public String AddTask(@RequestBody Task task) {
		// TODO Auto-generated method stub
		taskDao.save(task);
		return "Task saved";
	}
	
	/**
	 * Delete task.
	 *
	 * @param id the id
	 * @return the string
	 */
	@ApiOperation(value = "Delete Task", response = String.class)
	@RequestMapping(method = RequestMethod.DELETE, value="/delete/{id}")
	@ResponseBody
	public String DeleteTask(@PathParam("id") Long id) {
		taskDao.deleteById(id);
		return "Task Deleted";
	}
	
	/**
	 * Modify task.
	 *
	 * @param task the task
	 * @param id the id
	 * @return the task
	 */
	@ApiOperation(value = "Modify Task", response = Task.class)
	@RequestMapping(method = RequestMethod.PUT, value="/modify/{id}")
	@ResponseBody
	public Task ModifyTask(@RequestBody Task task, @PathVariable("id")
			String id) {
		 Long longid= Long.valueOf(id);
		 Optional<Task>  existingtask=taskDao.findById(longid);
		 existingtask.ifPresent(newTask->{
			 newTask.setDescription(task.getDescription());
			 newTask.setId(longid);
			 newTask.setLabel(task.getLabel());
			 newTask.setPriority(task.getPriority());
			 newTask.setStartdate(task.getStartdate());
			 newTask.setEndDate(task.getEndDate());	
			 taskDao.save(newTask);
		 });
		return taskDao.findById(longid).get();
	}

	
	/**
	 * Find task by priority.
	 *
	 * @param priority the priority
	 * @return the list
	 */
	@ApiOperation(value = "Find Tasks by Priority", response = Task.class)
	@RequestMapping(method = RequestMethod.GET, value="/get/{priority}")
	@ResponseBody
	public List<Task> findTaskByPriority(@PathVariable("priority")
			String priority) {
		List<Task> tasklist = new ArrayList<>();
		taskDao.findAll().forEach(c->{
			if(StringUtils.equalsIgnoreCase(c.getPriority(),priority)){
				tasklist.add(c);
			}
		});
		if(!tasklist.isEmpty()) {
			return tasklist;
		}
		 return new ArrayList<>();
		
	}
	
	
	/**
	 * Find task by date.
	 *
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @return the list
	 * @throws ParseException the parse exception
	 */
	@ApiOperation(value = "Find Tasks by startDate and EndDate", response = Task.class)
	@RequestMapping(method = RequestMethod.GET, value="/get")
	@ResponseBody
	public List<Task> findTaskByDate(@RequestParam(value="fromDate")
			String fromDate,@RequestParam(value="toDate")
	String toDate) throws ParseException {
		Date startDate = df.parse(fromDate);
		Date endDate = df.parse(toDate);
		List<Task> tasklist = new ArrayList<>();
		taskDao.findAll().forEach(c -> {
			if (c.getEndDate().before(endDate)) {
				tasklist.add(c);
			}
		});
		List<Task> finalList = tasklist.stream().filter(k -> k.getStartdate().after(startDate))
				.collect(Collectors.toList());
		if (!finalList.isEmpty()) {
			return finalList;
		}
		return new ArrayList<>();

	}
	
	
	/**
	 * Find today's task particular label.
	 *
	 * @param label the label
	 * @return the list
	 * @throws ParseException the parse exception
	 */
	@ApiOperation(value = "Find Today's task of particularLabel", response = Task.class)
	@RequestMapping(method = RequestMethod.GET, value="/getLabel")
	@ResponseBody
	public List<Task> findTodaySTaskParticularLabel(@RequestParam(value="label")
	String label) throws ParseException {
		List<Task> tasklist = new ArrayList<>();
		taskDao.findAll().forEach(c -> {
         if(StringUtils.equalsIgnoreCase(c.getLabel(), label) && StringUtils.equalsIgnoreCase(df.format(c.getStartdate()), df.format(new Date()))) {
        	 tasklist.add(c);
         }
		});
		if (!tasklist.isEmpty()) {
			return tasklist;
		}
		return new ArrayList<>();

	}
	
}
