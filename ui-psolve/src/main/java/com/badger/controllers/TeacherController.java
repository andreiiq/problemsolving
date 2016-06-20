package com.badger.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.badger.form.SubtaskForm;
import com.badger.form.TaskForm;
import com.badger.inputmapper.TeacherInputMapper;
import com.badger.service.EvaluationService;
import com.badger.service.SolutionService;
import com.badger.service.TaskService;
import com.badger.util.PageConstants;
import com.psolve.model.SubtaskModel;
import com.psolve.model.TaskModel;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	TaskService taskService;

	@Autowired
	TeacherInputMapper teacherInputMapper;

	@Autowired
	EvaluationService evaluationService;

	@RequestMapping(value = "/add-new-task", method = RequestMethod.POST)

	public String addNewTask(TaskForm taskForm, BindingResult bindingResult, Model model) {
		taskService.addNewProject(teacherInputMapper.parseAddTaskRequest(taskForm));
		return PageConstants.PROFILE_PAGE;
	}

	@RequestMapping(value = "/create-task", method = RequestMethod.GET)
	public String getAdministrationPage() {
		return PageConstants.TEAHCER_ADMINISTRATION;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getHomepage() {
		return PageConstants.TEAHCER_ADMINISTRATION;
	}

	@ResponseBody
	@RequestMapping(path = "/evaluateStudent", method = RequestMethod.POST)
	public ResponseEntity<String> evaluateStudent(@RequestBody TaskForm taskForm) {
		TaskModel taskModel = (TaskModel) taskService.findTaskByID(taskForm.getId());
		evaluationService.evaluateTask(taskModel, taskForm.getGrade());
		
		for(SubtaskForm subtaskForm : taskForm.getSubtasks()) {
			SubtaskModel subtaskModel = (SubtaskModel) taskService.findTaskByID(subtaskForm.getId());
			evaluationService.evaluateTask(subtaskModel, subtaskForm.getGrade());
		}

		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

}
