package com.badger.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.badger.service.SkillService;
import com.badger.util.UtilConstants;
import com.psolve.dao.specs.SkillsSpec;
import com.psolve.dao.specs.TaskSpecs;
import com.psolve.model.SkillModel;
import com.psolve.model.TaskModel;

@Service
public class DefaultSkillService implements SkillService {
	@Autowired
	SkillsSpec skillSpec;

	@Override
	public List<SkillModel> getUserBestSkills() {
		
		Specification<SkillModel> spec = SkillsSpec.sortByLevel();
		return taskRepo.findAll(specTitle, new PageRequest(page, UtilConstants.NUMBER_RESULTS_PAGINATION));
		skillSpec.sortByLevel(title)
		return null;
	}

}
