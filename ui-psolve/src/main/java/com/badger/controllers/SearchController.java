package com.badger.controllers;

import com.badger.data.FilterSearchData;
import com.badger.form.TaskFilterForm;
import com.badger.responses.ProjectSearchResponse;
import com.badger.responses.TaskModelResponse;
import com.badger.service.SearchService;
import com.psolve.model.TaskModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public ProjectSearchResponse filterSearch(@RequestBody TaskFilterForm filter) {
		FilterSearchData searchData = new FilterSearchData();
		searchData.setOwnedByCurrentUser(true);
		searchData.setTitle("Java");
		searchData.setCourse("Java");
		searchData.setPoints(20);

		Map<String, String> map = new HashMap<>();
		map.put("Java", "2");
		searchData.setSkills(map);
		Page<TaskModel> tasks = searchService.filterProjects(searchData, filter.getPage());
		return buildFilterSearchResponse(tasks);
	}

	private ProjectSearchResponse buildFilterSearchResponse(Page<TaskModel> tasks) {
		ProjectSearchResponse response = new ProjectSearchResponse();

		response.setCurrentPage(tasks.getNumber());
		response.setNumberOfPages(tasks.getTotalPages());

		List<TaskModelResponse> tasksResp = new LinkedList<>();
		for (TaskModel taskModel : tasks) {
			TaskModelResponse taskModelResponse = new TaskModelResponse();
			taskModelResponse.setTitle(taskModel.getTitle());
			taskModelResponse.setDescription(taskModel.getDescription());
			
			tasksResp.add(taskModelResponse);
		}
		response.setTasks(tasksResp);
		return response;
	}
}
