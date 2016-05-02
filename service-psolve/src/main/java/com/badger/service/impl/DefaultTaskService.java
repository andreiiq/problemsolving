package com.badger.service.impl;

import com.badger.service.TaskService;
import com.psolve.dao.TaskRepo;
import com.psolve.model.StudentModel;
import com.psolve.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultTaskService implements TaskService {

    @Autowired
    TaskRepo taskDao;

    @Override
    @Transactional
    public void addNewProject(TaskModel task) {
        StudentModel studentModel = new StudentModel();
        studentModel.setEmail("ssdDSA");
        taskDao.save(task);
    }


}
