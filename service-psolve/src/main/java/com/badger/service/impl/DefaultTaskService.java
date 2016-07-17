package com.badger.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.badger.service.TaskService;
import com.badger.service.UserService;
import com.psolve.dao.AbstractTaskRepo;
import com.psolve.dao.TaskRepo;
import com.psolve.dao.specs.TaskSpecs;
import com.psolve.dao.specs.UserSpecs;
import com.psolve.model.AbstractTaskModel;
import com.psolve.model.StudentModel;
import com.psolve.model.TaskModel;

@Service
public class DefaultTaskService implements TaskService {
	@Autowired
	AbstractTaskRepo<AbstractTaskModel> abstractTaskRepo;

	@Autowired
	TaskRepo taskRepo;

	@Autowired
	UserService userService;

	@Override
	@Transactional
	public void addNewProject(TaskModel task) {
		abstractTaskRepo.save(task);
	}

	@Override
	public AbstractTaskModel findTaskByID(long id) {
		return abstractTaskRepo.findById(id);
	}

	@Override
	public void saveTask(AbstractTaskModel task) {
		abstractTaskRepo.save(task);
	}

	@Override
	public void assignTaskToCurrentUser(TaskModel taskModel) {
		StudentModel studentModel = (StudentModel) userService.getCurrentUser();
		taskModel.setStudent(studentModel);
		abstractTaskRepo.save(taskModel);
	}


}
