package com.psolve.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psolve.model.AbstractUserModel;

@Repository
public interface UserRepo <T extends AbstractUserModel> extends JpaRepository<T, Long>  {
    AbstractUserModel findByEmail(String email);
}
