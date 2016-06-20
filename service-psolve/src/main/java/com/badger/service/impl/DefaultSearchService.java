package com.badger.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.badger.data.FilterSearchData;
import com.badger.service.SearchService;
import com.badger.service.UserService;
import com.badger.util.UtilConstants;
import com.psolve.dao.StudentRepo;
import com.psolve.dao.TaskRepo;
import com.psolve.dao.specs.TaskSpecs;
import com.psolve.dao.specs.UserSpecs;
import com.psolve.model.AbstractUserModel;
import com.psolve.model.StudentModel;
import com.psolve.model.TaskModel;

/**
 * Created by andre on 4/4/2016.
 */
@Service
public class DefaultSearchService implements SearchService {
	@Autowired
	StudentRepo studentRepo;

	@Autowired
	UserService userService;

	@Autowired
	TaskRepo taskRepo;

	@Override
	public List<StudentModel> getStudentsByName(String name) {
		if (name == null || name.isEmpty()) {
			return null;// TODO Exception
		}

		Specifications<StudentModel> specs = Specifications.where(UserSpecs.notCurrentUser());

		String[] names = name.trim().split(" ");

		if (names.length < 2) {
			Specification<StudentModel> specFirstName = UserSpecs.studentNameContains(names[0]);
			return studentRepo.findAll(specs.and(specFirstName));
		}

		Specification<StudentModel> specFullName = UserSpecs.studentNameContains(names[0], names[1]);
		return studentRepo.findAll(specs.and(specFullName));
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
			AbstractUserModel userModel = userService.getCurrentUser();
			specs.add(TaskSpecs.ownedBy(userModel.getEmail()));
		}

		String title = searchData.getTitle();
		if (title != null && !title.isEmpty()) {
			specs.add(TaskSpecs.titleContains(title));
		}

		long points = searchData.getPoints();
		specs.add(TaskSpecs.hasAtLeastGivenPoints(points));

		String course = searchData.getCourse();
		if (course != null && !course.isEmpty()) {
			specs.add(TaskSpecs.fromCourse(course));
		}

		Map<String, Long> skills = searchData.getSkills();
		if (skills != null && !skills.isEmpty()) {
			specs.add(TaskSpecs.containsSkills(skills));
		}
		return performFilterSearch(specs, page);
	}

	@Override
	public Page<TaskModel> findCurrentUserTasks(int page) {
		AbstractUserModel userModel = userService.getCurrentUser();
		String email = userModel.getEmail();

		return taskRepo.findAll(TaskSpecs.ownedBy(email),
				new PageRequest(page, UtilConstants.NUMBER_RESULTS_PAGINATION));
	}

	private Page<TaskModel> performFilterSearch(List<Specification<TaskModel>> specs, int page) {
		if (specs.isEmpty()) {
			return taskRepo.findAll(new PageRequest(page, UtilConstants.NUMBER_RESULTS_PAGINATION));
		}
		Specifications<TaskModel> filters = Specifications.where(specs.get(0));
		for (Specification<TaskModel> spec : specs) {
			filters = filters.and(spec);
		}

		return taskRepo.findAll(filters, new PageRequest(page, UtilConstants.NUMBER_RESULTS_PAGINATION));
	}

	@Override
	public Page<TaskModel> findAllTasks(int page) {
		return taskRepo.findAll(new PageRequest(page, UtilConstants.NUMBER_RESULTS_PAGINATION));
	}

}
