package com.example.demo.taskApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.taskApplication.dao.TaskApplicationDao;
import com.example.demo.taskApplication.model.Priority;
import com.example.demo.taskApplication.model.Task;

@Component
public class DataInit implements ApplicationRunner {
	
	private TaskApplicationDao taskDao;
	
	 private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	public DataInit(TaskApplicationDao taskDao) {
		this.taskDao=taskDao;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
		long count = taskDao.count();
		
		if(count==0) {

			Task taskone = new Task();
			taskone.setDescription("Create an Application");
			taskone.setPriority(Priority.HIGH.name());
			taskone.setLabel("Development");
			taskone.setStartdate(df.parse("2019-02-23"));
			taskone.setEndDate(df.parse("2019-02-23"));
			taskDao.save(taskone);
			
			
			Task tasktwo = new Task();
			tasktwo.setDescription("Read NewsPaper");
			tasktwo.setPriority(Priority.LOW.name());
			tasktwo.setLabel("GK");
			tasktwo.setStartdate(df.parse("2019-02-24"));
			tasktwo.setEndDate(df.parse("2019-02-24"));
			taskDao.save(tasktwo);
			
			Task taskthree = new Task();
			taskthree.setDescription("Gym");
			taskthree.setPriority(Priority.MEDIUM.name());
			taskthree.setLabel("Fitness");
			taskthree.setStartdate(df.parse("2019-02-25"));
			taskthree.setEndDate(df.parse("2019-02-29"));
			taskDao.save(taskthree);
			
			
			Task taskfour = new Task();
			taskfour.setDescription("Fruits");
			taskfour.setPriority(Priority.LOW.name());
			taskfour.setLabel("Fitness");
			taskfour.setStartdate(df.parse("2019-02-10"));
			taskfour.setEndDate(df.parse("2019-02-29"));
			taskDao.save(taskfour);
			
			
			Task taskfive = new Task();
			taskfive.setDescription("New Learning");
			taskfive.setPriority(Priority.LOW.name());
			taskfive.setLabel("Tech");
			taskfive.setStartdate(new Date());
			taskfive.setEndDate(df.parse("2019-02-29"));
			taskDao.save(taskfive);
		}
		
	}

}
