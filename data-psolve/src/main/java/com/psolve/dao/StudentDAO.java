package com.psolve.dao;

import org.springframework.data.repository.CrudRepository;

import com.psolve.model.StudentModel;

public interface StudentDAO extends CrudRepository<StudentModel, Long> {
	StudentModel findByEmail(String email);
}