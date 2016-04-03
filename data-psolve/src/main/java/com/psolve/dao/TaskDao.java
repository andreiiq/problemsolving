package com.psolve.dao;

import com.psolve.model.TaskModel;
import org.springframework.data.repository.CrudRepository;

public interface TaskDao extends CrudRepository<TaskModel, Long> {

}
