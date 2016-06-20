package com.badger.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.badger.dto.SkillDTO;
import com.badger.dto.SubtaskDTO;
import com.badger.dto.TaskDTO;
import com.badger.form.SubtaskForm;
import com.badger.form.TaskForm;
import com.badger.service.EvaluationService;
import com.badger.service.TaskService;
import com.badger.service.UserService;
import com.psolve.model.SkillModel;
import com.psolve.model.StudentModel;
import com.psolve.model.SubtaskModel;
import com.psolve.model.TaskModel;

@Controller
public class TaskController {
	@Autowired
	TaskService taskService;

	@Autowired
	EvaluationService evaluationService;

	@Autowired
	UserService userService;

	@ResponseBody
	@RequestMapping(value = "/getTask", method = RequestMethod.GET, produces = "application/json")
	public TaskDTO getTask(@RequestParam long taskId) {
		TaskModel taskModel = (TaskModel) taskService.findTaskByID(taskId);
		TaskDTO taskDTO = buildTaskDTO(taskModel);
		return taskDTO;

	}

	@ResponseBody
	@RequestMapping(value = "/assignTask", method = RequestMethod.POST)
	public ResponseEntity<String> assignTask(@RequestBody TaskForm taskForm) {
		TaskModel taskModel = (TaskModel) taskService.findTaskByID(taskForm.getId());
		taskService.assignTaskToCurrentUser(taskModel);

		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/evaluateTask", method = RequestMethod.POST)
	public ResponseEntity<String> evaluatTask(@RequestBody TaskForm taskForm) {
		TaskModel taskModel = (TaskModel) taskService.findTaskByID(taskForm.getId());
		evaluationService.evaluateTask(taskModel, taskForm.getGrade());

		List<SubtaskForm> subtaskForms = taskForm.getSubtasks();
		for (SubtaskForm subtaskForm : subtaskForms) {
			SubtaskModel subtaskModel = (SubtaskModel) taskService.findTaskByID(subtaskForm.getId());
			evaluationService.evaluateTask(subtaskModel, subtaskForm.getGrade());

		}

		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	private TaskDTO buildTaskDTO(TaskModel taskModel) {
		TaskDTO taskDTO = new TaskDTO();

		taskDTO.setId(taskModel.getId());
		taskDTO.setTitle(taskModel.getTitle());
		taskDTO.setDescription(taskModel.getDescription());
		taskDTO.setOwnerEmail(taskModel.getStudent().getEmail());

		StudentModel studentModel = taskModel.getStudent();

		if (studentModel == null) {
			taskDTO.setOwned(false);
		} else {
			taskDTO.setOwned(true);
		}

		if (taskModel.getSolutionModel() != null) {
			taskDTO.setHasSolution(true);
		}

		List<SubtaskDTO> subtaskDTOs = new LinkedList<>();
		taskDTO.setSubtasks(subtaskDTOs);

		for (SubtaskModel subtaskModel : taskModel.getSubtaskModels()) {
			SubtaskDTO subtaskDTO = new SubtaskDTO();

			List<SkillDTO> skillDTOs = new LinkedList<>();
			subtaskDTO.setSkills(skillDTOs);

			subtaskDTO.setId(subtaskModel.getId());
			subtaskDTO.setTitle(subtaskModel.getTitle());
			subtaskDTO.setDescription(subtaskModel.getDescription());

			for (SkillModel skillModel : subtaskModel.getSkillsGained()) {
				SkillDTO skillDTO = new SkillDTO();

				skillDTO.setName(skillModel.getName());

				long level = skillModel.getLevelModel().getValue();
				skillDTO.setLevel(level);

				skillDTOs.add(skillDTO);
			}

			subtaskDTOs.add(subtaskDTO);
		}
		return taskDTO;
	}
}
