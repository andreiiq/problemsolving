package com.badger.controllers;

import com.badger.service.SearchService;
import com.badger.util.PageConstants;
import com.psolve.model.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by andre on 4/4/2016.
 */
@Controller
public class HeaderController {
    @Autowired
    SearchService searchService;


    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
    public List<StudentModel> searchAll(@RequestParam String name) {
        List<StudentModel> students = searchService.getStudentsByName(name);
        System.out.println(students.size());
        return students;
    }
}