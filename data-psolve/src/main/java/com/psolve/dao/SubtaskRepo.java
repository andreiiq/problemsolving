package com.psolve.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.psolve.model.SubtaskModel;

@Repository
public interface SubtaskRepo extends AbstractTaskRepo<SubtaskModel>, JpaSpecificationExecutor<SubtaskModel> {
}
