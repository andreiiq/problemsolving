package com.badger.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.badger.service.SkillService;
import com.badger.service.UserService;
import com.badger.util.UtilConstants;
import com.psolve.dao.LevelRepo;
import com.psolve.dao.SkillRepo;
import com.psolve.dao.specs.SkillsSpec;
import com.psolve.model.AbstractUserModel;
import com.psolve.model.LevelModel;
import com.psolve.model.SkillModel;
import com.psolve.model.StudentModel;

@Service
public class DefaultSkillService implements SkillService {
	@Autowired
	SkillsSpec skillSpec;

	@Autowired
	SkillRepo skillRepo;

	@Autowired
	UserService userService;

	@Autowired
	LevelRepo levelRepo;

	@Override
	public Page<SkillModel> getUserBestSkills() {
		AbstractUserModel userModel = userService.getCurrentUser();

		Specification<SkillModel> specLevel = SkillsSpec.sortByLevel();
		Specification<SkillModel> specUser = SkillsSpec.forUser(userModel.getEmail());

		Specifications<SkillModel> filters = Specifications.where(specUser).and(specLevel);

		return skillRepo.findAll(filters,
				new PageRequest(UtilConstants.FIRST_INDEX, UtilConstants.NUMBER_SKILLS_ON_PROFILE));
	}

	@Override
	public List<SkillModel> getUserSkills(StudentModel student) {

		Specification<SkillModel> specUser = SkillsSpec.forUser(student.getEmail());
		Specifications<SkillModel> filters = Specifications.where(specUser);

		return skillRepo.findAll(filters);
	}

	@Override
	public LevelModel getLevel(long value) {
		return levelRepo.findByValue(value);
	}

	@Override
	public void saveSkills(List<SkillModel> skills) {
		skillRepo.save(skills);
	}

}
