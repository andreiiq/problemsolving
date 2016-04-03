package com.badger.inputmapper;

import com.badger.form.SkillForm;
import com.badger.form.SubtaskForm;
import com.badger.form.TaskForm;
import com.psolve.model.LevelModel;
import com.psolve.model.SubtaskModel;
import com.psolve.model.TaskModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 4/3/2016.
 */
@Component
public class TeacherInputMapper {
    public TaskModel parseAddTaskRequest(TaskForm taskForm) {
        TaskModel taskModel = new TaskModel();

        taskModel.setTitle(taskForm.getTitle());
        taskModel.setDescription(taskForm.getDescription());
        taskModel.setPointsRewarded(taskForm.getPointsRewarded());

        List<SubtaskModel> subtaskModels = new ArrayList<>();
        for (SubtaskForm subtaskForm : taskForm.getSubtasks()) {

            SubtaskModel subtaskModel = new SubtaskModel();
            subtaskModel.setTitle(subtaskForm.getTitle());
            subtaskModel.setDescription(subtaskForm.getDescription());

            subtaskModel.setPointsRewarded(subtaskForm.getPointsRewarded());

            List<LevelModel> levelModels = new ArrayList<>();

            for (SkillForm skillForm : subtaskForm.getSkills()) {
                LevelModel levelModel = new LevelModel();
                levelModel.setName(skillForm.getName());
                levelModel.setValue(skillForm.getLevel());

                levelModels.add(levelModel);
            }

            subtaskModel.setLevelsRequired(levelModels);
            subtaskModels.add(subtaskModel);
        }

        taskModel.setSubtaskModels(subtaskModels);
        return taskModel;
    }
}
