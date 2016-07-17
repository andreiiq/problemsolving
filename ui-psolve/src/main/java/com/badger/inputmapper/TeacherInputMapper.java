package com.badger.inputmapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badger.form.SkillForm;
import com.badger.form.SubtaskForm;
import com.badger.form.TaskForm;
import com.badger.service.CourseService;
import com.badger.service.SkillService;
import com.badger.service.UserService;
import com.psolve.model.LevelModel;
import com.psolve.model.SkillModel;
import com.psolve.model.SubtaskModel;
import com.psolve.model.TaskModel;
import com.psolve.model.TeacherModel;

@Component
public class TeacherInputMapper {
	@Autowired
	SkillService skillService;

	@Autowired
	CourseService courseService;

	@Autowired
	UserService userService;

	public TaskModel parseAddTaskRequest(TaskForm taskForm) {
		TaskModel taskModel = new TaskModel();

		taskModel.setTeacherModel((TeacherModel) userService.getCurrentUser());
		taskModel.setTitle(taskForm.getTitle());
		taskModel.setDescription(taskForm.getDescription());
		taskModel.setPointsRewarded(taskForm.getPointsRewarded());

		taskModel.setCourseModel(courseService.getCourse(taskForm.getCourse()));

		List<SubtaskModel> subtaskModels = new ArrayList<>();

		if (taskForm.getSubtasks() == null) {
			return taskModel;
		}

		for (SubtaskForm subtaskForm : taskForm.getSubtasks()) {

			SubtaskModel subtaskModel = new SubtaskModel();
			subtaskModel.setTitle(subtaskForm.getTitle());
			subtaskModel.setDescription(subtaskForm.getDescription());

			subtaskModel.setParentTask(taskModel);

			subtaskModel.setPointsRewarded(subtaskForm.getPointsRewarded());

			List<SkillModel> skillModels = new ArrayList<>();

			if (subtaskForm.getSkills() != null) {

				for (SkillForm skillForm : subtaskForm.getSkills()) {
					SkillModel skillModel = new SkillModel();
					skillModel.setName(skillForm.getName());

					LevelModel levelModel = skillService.getLevel(skillForm.getLevel());
					skillModel.setLevelModel(levelModel);
					skillModel.setExperience(levelModel.getXpObtained());

					skillModels.add(skillModel);
				}
			}

			subtaskModel.setSkillsGained(skillModels);
			subtaskModels.add(subtaskModel);
		}

		taskModel.setSubtaskModels(subtaskModels);
		return taskModel;
	}
}
