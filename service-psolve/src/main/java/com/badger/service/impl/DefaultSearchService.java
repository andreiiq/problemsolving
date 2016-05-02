package com.badger.service.impl;

import com.badger.data.FilterSearchData;
import com.badger.service.SearchService;
import com.badger.util.UtilConstants;
import com.psolve.dao.StudentRepo;
import com.psolve.dao.TaskRepo;
import com.psolve.dao.specs.TaskSpecs;
import com.psolve.dao.specs.UserSpecs;
import com.psolve.model.AbstractUserModel;
import com.psolve.model.StudentModel;
import com.psolve.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.scheduling.config.Task;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by andre on 4/4/2016.
 */
@Service
public class DefaultSearchService implements SearchService {
	@Autowired
	StudentRepo studentRepo;

	@Autowired
	TaskRepo taskRepo;

	@Override
	public List<StudentModel> getStudentsByName(String name) {
		if (name == null || name.isEmpty()) {
			return null;// TODO Exception
		}
		String[] names = name.trim().split(" ");

		if (names.length < 2) {
			Specification<StudentModel> specFirstName = UserSpecs.studentNameContains(names[0]);
			return studentRepo.findAll(specFirstName);
		}

		Specification<StudentModel> specFullName = UserSpecs.studentNameContains(names[0], names[1]);
		return studentRepo.findAll(specFullName);
	}

	@Override
	public Page<TaskModel> findProjectsByTitle(String title, int page) {
		Specification<TaskModel> specTitle = TaskSpecs.titleContains(title);
		return taskRepo.findAll(specTitle, new PageRequest(page, UtilConstants.NUMBER_RESULTS_PAGINATION));
	}

	@Override
	public Page<TaskModel> filterProjects(FilterSearchData searchData, int page) {
		List<Specification<TaskModel>> specs = new LinkedList<>();

		if (searchData.isOwnedByCurrentUser()) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			String email = (String) auth.getPrincipal();
			AbstractUserModel user = studentRepo.findByEmail(email);
			specs.add(TaskSpecs.ownedBy(email));
		}
		
		String title = searchData.getTitle();
		if(title != null && !title.isEmpty()) {
			specs.add(TaskSpecs.titleContains(title));			
		}
		
		long points = searchData.getPoints();
		specs.add(TaskSpecs.hasAtLeastGivenPoints(points));
		
		String course = searchData.getCourse();
		specs.add(TaskSpecs.fromCourse(course));
		
		Map<String, String> skills = searchData.getSkills();
		specs.add(TaskSpecs.containsSkills(skills));
		return performFilterSearch(specs, page);
	}

	private Page<TaskModel> performFilterSearch(List<Specification<TaskModel>> specs, int page) {
		Specifications<TaskModel> filters =  Specifications.where(specs.get(0));
		for(Specification<TaskModel> spec : specs) {
			filters = filters.and(spec);
		}
			
		return taskRepo.findAll(filters, new PageRequest(0, UtilConstants.NUMBER_RESULTS_PAGINATION));
	}
}
