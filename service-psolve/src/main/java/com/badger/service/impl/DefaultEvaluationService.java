package com.badger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.badger.service.EvaluationService;
import com.badger.strategy.EvaluationStrategy;
import com.psolve.model.StudentModel;
import com.psolve.model.SubtaskModel;
import com.psolve.model.TaskModel;

@Service
public class DefaultEvaluationService implements EvaluationService {

	@Autowired
	EvaluationStrategy evaluationStrategy;

	@Override
	public void evaluateTask(TaskModel taskModel, long grade) {
		StudentModel student = taskModel.getStudent();

		evaluationStrategy.evaluateStudent(student, taskModel, grade);
	}

	@Override
	public void evaluateTask(SubtaskModel taskModel, long grade) {
		StudentModel student = taskModel.getStudent();

		evaluationStrategy.evaluateStudent(student, taskModel, grade);

	}

}
