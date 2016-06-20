package com.badger.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.badger.form.AssignSubtaskForm;
import com.badger.service.NotificationService;

@Controller
@RequestMapping("/assign")
public class AssigntationController {

	@Autowired
	NotificationService notificationService;

	@RequestMapping(value = "/assignTask", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> assignTask(@RequestBody AssignSubtaskForm subtaskForm) {
		String email = subtaskForm.getReceiverEmail();
		long taskID = subtaskForm.getSubtaskID();

		notificationService.sendAssignToTaskNotification(email, taskID);
		
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/assignMentor", method = RequestMethod.POST, consumes = "application/json")
	public void assignMentor(@RequestParam String email, @RequestParam long taskID) {
	}

}
