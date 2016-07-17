package com.badger.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.badger.exception.NotAuthorizedException;
import com.badger.service.AuthorizationService;
import com.badger.service.SolutionService;
import com.badger.service.TaskService;
import com.badger.service.UserService;
import com.badger.util.BuserFileManager;
import com.badger.util.TimeManager;
import com.psolve.dao.SolutionRepo;
import com.psolve.model.SolutionModel;
import com.psolve.model.StudentModel;
import com.psolve.model.TaskModel;
import com.psolve.model.TeacherModel;

@Service
public class DefaultSolutionService implements SolutionService {
	@Autowired
	BuserFileManager fileManager;

	@Autowired
	UserService userService;

	@Autowired
	TaskService taskService;
	

	@Autowired
	TaskService taskRepo;


	@Autowired
	SolutionRepo solutionRepo;

	@Autowired
	TimeManager timeManager;

	@Autowired
	AuthorizationService authorizationService;

	@Override
	public void uploadSolution(byte[] solution, long taskId) {
		SolutionModel solutionModel = new SolutionModel();

		TaskModel task = (TaskModel) taskService.findTaskByID(taskId);
		StudentModel user = task.getStudent();

		SolutionModel existingSolution = task.getSolutionModel();

		if (existingSolution != null) {
			existingSolution.setFile(solution);
			existingSolution.setUploadTime(Calendar.getInstance());

			fileManager.buildSolutionPath(existingSolution, user, task);
			fileManager.uploadSolution(existingSolution);

			solutionRepo.save(existingSolution);

		} else {
			solutionModel.setTask(task);
			solutionModel.setStudent(user);
			solutionModel.setFile(solution);

			TeacherModel teacherOwner = task.getTeacherModel();
			solutionModel.setTeacher(teacherOwner);

			solutionModel.setUploadTime(Calendar.getInstance());
			task.setSolutionModel(solutionModel);

			fileManager.buildSolutionPath(solutionModel, user, task);
			fileManager.uploadSolution(solutionModel);
			
			solutionRepo.save(solutionModel);
			taskRepo.saveTask(task);
		}
	}

	public List<SolutionModel> getSolutionsForCurrentTeacher() {
		TeacherModel teacher = (TeacherModel) userService.getCurrentUser();
		return solutionRepo.findByTeacherOrderByUploadTime(teacher);
	}

	@Override
	public String getSolutionPath(long solutionId) {
		SolutionModel solutionModel = solutionRepo.findOne(solutionId);
		return solutionModel.getSolutionPath();
	}

}
