package com.badger.strategy;

import com.psolve.model.StudentModel;
import com.psolve.model.SubtaskModel;
import com.psolve.model.TaskModel;

public interface EvaluationStrategy {
	void evaluateStudent(StudentModel student, TaskModel task, long grade);
	void evaluateStudent(StudentModel student, SubtaskModel subtask, long grade);

}
