package com.psolve.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psolve.model.SolutionModel;
import com.psolve.model.TeacherModel;

@Repository
public interface SolutionRepo extends JpaRepository<SolutionModel, Long> {
	List<SolutionModel> findByTeacherOrderByUploadTime(TeacherModel teacher);
}
