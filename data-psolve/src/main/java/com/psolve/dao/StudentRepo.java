package com.psolve.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.psolve.model.StudentModel;

@Repository
public interface StudentRepo  extends UserRepo<StudentModel>, JpaSpecificationExecutor<StudentModel> {
}