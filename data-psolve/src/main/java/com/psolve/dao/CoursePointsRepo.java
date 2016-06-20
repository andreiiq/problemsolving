package com.psolve.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psolve.model.CourseModel;
import com.psolve.model.CoursePointsModel;
import com.psolve.model.StudentModel;

@Repository
public interface CoursePointsRepo extends JpaRepository<CoursePointsModel, Long> {
	CoursePointsModel findByOwnerAndCourse(StudentModel owner, CourseModel course);
}
