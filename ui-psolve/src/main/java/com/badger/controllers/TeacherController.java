package com.badger.controllers;

import com.badger.inputmapper.TeacherInputMapper;
import com.badger.service.TaskService;
import com.psolve.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.badger.form.TaskForm;
import com.badger.util.PageConstants;

import javax.validation.Valid;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TaskService taskService;

    @Autowired
    TeacherInputMapper teacherInputMapper;

    @RequestMapping(value = "/add-new-task", method = RequestMethod.POST)

    public String addNewTask(TaskForm taskForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(BindingResult.class.getName() + ".taskForm", bindingResult);
            return PageConstants.PROFILE_PAGE;
        }

        taskService.addNewProject(teacherInputMapper.parseAddTaskRequest(taskForm));
        return PageConstants.PROFILE_PAGE;
    }

    @RequestMapping(value = "/create-task", method = RequestMethod.GET)
    public String getAdministrationPage() {
        return PageConstants.TEAHCER_ADMINISTRATION;
    }

    @RequestMapping(value = "/getCreateProjectForm", method = RequestMethod.GET)
    public String getCreateProjectForm() {
        return PageConstants.PROJECT_CREATE_FORM;
    }
}
