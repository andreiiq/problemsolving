package com.psolve.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.psolve.model.AbstractTaskModel;

/**
 * Created by andre on 4/17/2016.
 */
@Repository
public interface AbstractTaskRepo<T extends AbstractTaskModel> extends JpaRepository<T, Long> {
	AbstractTaskModel findById(long id);
}
