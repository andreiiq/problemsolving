package com.badger.service;

import com.badger.data.FilterSearchData;
import com.psolve.model.StudentModel;
import com.psolve.model.TaskModel;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by andre on 4/4/2016.
 */

public interface SearchService {
    List<StudentModel> getStudentsByName(String name);

    Page<TaskModel> findProjectsByTitle(String title, int page);
    Page<TaskModel> filterProjects(FilterSearchData searchData, int page);
    Page<TaskModel> findAllTasks(int page);
    Page<TaskModel> findCurrentUserTasks(int page);

}
