package com.badger.service;

import java.util.List;

import com.psolve.model.AbstractTaskModel;
import com.psolve.model.TaskModel;

/**
 * Created by andre on 4/3/2016.
 */

public interface TaskService {
	void addNewProject(TaskModel task);

	void saveTask(AbstractTaskModel task);

	AbstractTaskModel findTaskByID(long id);

	void assignTaskToCurrentUser(TaskModel taskModel);


}
