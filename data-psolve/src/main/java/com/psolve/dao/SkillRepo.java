package com.psolve.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psolve.model.SkillModel;

@Repository
public interface SkillRepo extends JpaRepository<SkillModel, Long>{
}
