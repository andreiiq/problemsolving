package com.badger.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.badger.service.CourseService;
import com.psolve.dao.CoursePointsRepo;
import com.psolve.dao.CourseRepo;
import com.psolve.model.CourseModel;
import com.psolve.model.CoursePointsModel;
import com.psolve.model.StudentModel;

@Service
public class DefaultCourseService implements CourseService {
	@Autowired
	CoursePointsRepo coursePointsRepo;

	@Autowired
	CourseRepo courseRepo;

	@Override
	public List<CoursePointsModel> getCoursePointsForUser(StudentModel studentModel) {
		return null;
	}

	@Override
	public List<CourseModel> getCourses() {
		return courseRepo.findAll();
	}

	@Override
	public CourseModel getCourse(String name) {
		return courseRepo.findByTitle(name);
	}

}
