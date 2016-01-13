package com.psolve.dao;

import org.springframework.data.repository.CrudRepository;

import com.psolve.model.Student;

public interface TaskDao extends CrudRepository<Student, Long> {

}
