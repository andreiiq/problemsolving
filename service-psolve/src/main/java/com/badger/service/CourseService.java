package com.badger.service;

import java.util.List;

import com.psolve.model.CourseModel;
import com.psolve.model.CoursePointsModel;
import com.psolve.model.StudentModel;

public interface CourseService {
	List<CoursePointsModel> getCoursePointsForUser(StudentModel studentModel);

	List<CourseModel> getCourses();

	CourseModel getCourse(String name);
}
