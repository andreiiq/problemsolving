package com.badger.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.psolve.model.LevelModel;
import com.psolve.model.SkillModel;
import com.psolve.model.StudentModel;

public interface SkillService {
	Page<SkillModel> getUserBestSkills();

	List<SkillModel> getUserSkills(StudentModel student);

	LevelModel getLevel(long value);

	void saveSkills(List<SkillModel> skills);
}
