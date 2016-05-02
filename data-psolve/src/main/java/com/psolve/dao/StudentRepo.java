package com.psolve.dao;

import com.psolve.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<StudentModel, Long>, JpaSpecificationExecutor<StudentModel> {
    StudentModel findByEmail(String email);
}