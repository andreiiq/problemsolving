package com.psolve.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psolve.model.LevelModel;

@Repository
public interface LevelRepo extends JpaRepository<LevelModel, Long> {
	LevelModel findByValue(long value);
}
