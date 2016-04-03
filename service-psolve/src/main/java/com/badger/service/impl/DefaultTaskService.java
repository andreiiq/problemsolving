package com.badger.service.impl;

import com.badger.service.TaskService;
import com.psolve.dao.TaskDao;
import com.psolve.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultTaskService implements TaskService {

    @Autowired
    TaskDao taskDao;

    @Override
    public void addNewProject(TaskModel task) {
        taskDao.save(task);
    }
}
