package com.psolve.dao;

import com.psolve.model.AbstractTaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by andre on 4/17/2016.
 */
@NoRepositoryBean
public interface AbstractTaskRepo<T extends AbstractTaskModel> extends JpaRepository<T, Long> {
}
