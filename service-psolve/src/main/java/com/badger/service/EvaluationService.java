package com.badger.service;

import com.psolve.model.SubtaskModel;
import com.psolve.model.TaskModel;

public interface EvaluationService {
	void evaluateTask(TaskModel taskModel, long grade);
	void evaluateTask(SubtaskModel taskModel, long grade);

}
