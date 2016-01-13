package com.psolve.dao;

import org.springframework.data.repository.CrudRepository;

import com.psolve.model.Student;

public interface StudentDAO extends CrudRepository<Student, Long> {
	Student findByEmail(String email);
}