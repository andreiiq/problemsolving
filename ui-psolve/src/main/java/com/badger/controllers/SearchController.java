package com.badger.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.badger.data.FilterSearchData;
import com.badger.dto.ProjectSearchDTO;
import com.badger.dto.TaskDTO;
import com.badger.dto.UserDTO;
import com.badger.dto.UserSearchDTO;
import com.badger.form.TaskFilterForm;
import com.badger.service.SearchService;
import com.psolve.model.StudentModel;
import com.psolve.model.TaskModel;

/**
 * Created by andre on 4/17/2016.
 */
@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	SearchService searchService;

	@ResponseBody
	@RequestMapping(path = "/filter", method = RequestMethod.POST, produces = "application/json")
	public ProjectSearchDTO filterTasks(@RequestBody TaskFilterForm filter) {
		FilterSearchData searchData = new FilterSearchData();

		searchData.setOwnedByCurrentUser(filter.isOwnProject());
		searchData.setPoints(filter.getPoints());
		searchData.setCourse(filter.getCourse());
		searchData.setTitle(filter.getTitle());
		searchData.setSkills(filter.getSkills());

		Page<TaskModel> tasks = searchService.filterProjects(searchData, filter.getPage());
		return buildFilterSearchResponse(tasks, filter.getPage());
	}

	@ResponseBody
	@RequestMapping(path = "/findAll", method = RequestMethod.POST, produces = "application/json")
	public ProjectSearchDTO getALLTasks(@RequestBody TaskFilterForm filter) {
		Page<TaskModel> tasks = searchService.findAllTasks(filter.getPage());
		return buildFilterSearchResponse(tasks, filter.getPage());
	}

	@ResponseBody
	@RequestMapping(path = "/findUserTasks", method = RequestMethod.POST, produces = "application/json")
	public ProjectSearchDTO getUserTasks(@RequestBody TaskFilterForm filter) {
		Page<TaskModel> tasks = searchService.findCurrentUserTasks(filter.getPage());
		return buildFilterSearchResponse(tasks, filter.getPage());
	}

	@ResponseBody
	@RequestMapping(path = "/findUserMentorTasks", method = RequestMethod.POST, produces = "application/json")
	public ProjectSearchDTO getUserMentorTasks(@RequestBody TaskFilterForm filter) {
		Page<TaskModel> tasks = searchService.getTasksWithCurrentUserMentor(filter.getPage());
		return buildFilterSearchResponse(tasks, filter.getPage());
	}

	@ResponseBody
	@RequestMapping(path = "/findAllEligibleUsers", method = RequestMethod.GET, produces = "application/json")
	public UserSearchDTO getStudentsEligibleForSubtask(@RequestParam String name, @RequestParam long taskId) {
		List<StudentModel> students = searchService.getStudentsWithSkillsForSubtask(name, taskId);
		return buildUserSearchResponse(students);
	}

	@ResponseBody
	@RequestMapping(path = "/findAllEligibleMentors", method = RequestMethod.GET, produces = "application/json")
	public UserSearchDTO getMentorsEligibleForSubtask(@RequestParam String name, @RequestParam long subtaskId) {
		List<StudentModel> students = searchService.getStudentsWithSkillsForSubtask(name, subtaskId);
		return buildUserSearchResponse(students);
	}

	private ProjectSearchDTO buildFilterSearchResponse(Page<TaskModel> tasks, int page) {
		ProjectSearchDTO response = new ProjectSearchDTO();

		response.setCurrentPage(page);
		response.setNumberOfPages(tasks.getTotalPages());

		List<TaskDTO> tasksResp = new LinkedList<>();
		for (TaskModel taskModel : tasks) {
			TaskDTO taskModelResponse = new TaskDTO();
			taskModelResponse.setId(taskModel.getId());
			taskModelResponse.setCourse(taskModel.getCourseModel().getTitle());
			taskModelResponse.setTitle(taskModel.getTitle());
			taskModelResponse.setDescription(taskModel.getDescription());
			taskModelResponse.setCourse(taskModel.getCourseModel().getTitle());
			taskModelResponse.setPoints(taskModel.getPointsRewarded());
			tasksResp.add(taskModelResponse);
		}
		response.setTasks(tasksResp);
		return response;
	}

	private UserSearchDTO buildUserSearchResponse(List<StudentModel> students) {
		List<UserDTO> users = new LinkedList<>();

		for (StudentModel student : students) {
			UserDTO user = new UserDTO();
			user.setEmail(student.getEmail());
			user.setFirstname(student.getFirstname());
			user.setLastname(student.getLastname());
			users.add(user);
		}

		UserSearchDTO searchUsersDTO = new UserSearchDTO();
		searchUsersDTO.setUserSearchDTOs(users);
		return searchUsersDTO;
	}

}
