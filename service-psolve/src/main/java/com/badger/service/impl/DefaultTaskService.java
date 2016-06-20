package com.badger.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.badger.service.TaskService;
import com.badger.service.UserService;
import com.psolve.dao.AbstractTaskRepo;
import com.psolve.model.AbstractTaskModel;
import com.psolve.model.StudentModel;
import com.psolve.model.TaskModel;

@Service
public class DefaultTaskService implements TaskService {
	@Autowired
	AbstractTaskRepo<AbstractTaskModel> taskRepo;

	@Autowired
	UserService userService;

	@Override
	@Transactional
	public void addNewProject(TaskModel task) {
		StudentModel studentModel = new StudentModel();
		studentModel.setEmail("ssdDSA");
		taskRepo.save(task);
	}

	@Override
	public AbstractTaskModel findTaskByID(long id) {
		return taskRepo.findById(id);
	}

	@Override
	public void saveTask(AbstractTaskModel task) {
		taskRepo.save(task);
	}

	@Override
	public void assignTaskToCurrentUser(TaskModel taskModel) {
		StudentModel studentModel = (StudentModel) userService.getCurrentUser();
		taskModel.setStudent(studentModel);
		taskRepo.save(taskModel);
	}

}
