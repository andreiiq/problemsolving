package com.psolve.dao;

import com.psolve.model.TaskModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends AbstractTaskRepo<TaskModel>, JpaSpecificationExecutor<TaskModel> {
	
}
