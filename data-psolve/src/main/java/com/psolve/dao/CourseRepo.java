package com.psolve.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psolve.model.CourseModel;

@Repository
public interface CourseRepo extends JpaRepository<CourseModel, Long> {
	CourseModel findByTitle(String title);
}
