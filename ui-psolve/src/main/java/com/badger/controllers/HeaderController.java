package com.badger.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.badger.dto.NotificationDTO;
import com.badger.dto.UserNotificationsDTO;
import com.badger.service.NotificationService;
import com.badger.service.SearchService;
import com.badger.service.TaskService;
import com.psolve.model.AbstractNotificationModel;
import com.psolve.model.AbstractQueryNotificationModel;
import com.psolve.model.AcceptedInvitationNotificationModel;
import com.psolve.model.DeclinedInvitationNotificationModel;
import com.psolve.model.StudentModel;
import com.psolve.model.SubmitedSolutionNotification;

/**
 * Created by andre on 4/4/2016.
 */
@Controller
public class HeaderController {
	@Autowired
	SearchService searchService;

	@Autowired
	TaskService taskService;

	@Autowired
	NotificationService notificationService;

	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
	public List<StudentModel> searchAll(@RequestParam String name) {
		List<StudentModel> students = searchService.getStudentsByName(name);
		return students;
	}

	@ResponseBody
	@RequestMapping(path = "/getNotifications", method = RequestMethod.GET)
	public UserNotificationsDTO getNewTasks(@AuthenticationPrincipal String email) {
		List<AbstractNotificationModel> notifications = notificationService.getNewNotifications();
		return buildNotificationsDTO(notifications);

	}

	@ResponseBody
	@RequestMapping(value = "/countNotifications", method = RequestMethod.GET)
	public long getUnseenNotifications() {
		return notificationService.countNotSeenNotifications();
	}

	@RequestMapping(path = "/acceptNotifiation", method = RequestMethod.GET)
	public ResponseEntity<String> acceptNotification(@RequestParam long notificationID) {
		notificationService.acceptQueryNotification(notificationID);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);

	}

	@RequestMapping(path = "/rejectNotification", method = RequestMethod.GET)
	public ResponseEntity<String> rejectNotification(@RequestParam long notificationID) {
		notificationService.rejectQueryNotification(notificationID);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);

	}

	private UserNotificationsDTO buildNotificationsDTO(List<AbstractNotificationModel> notifications) {
		UserNotificationsDTO userNotifications = new UserNotificationsDTO();
		List<NotificationDTO> notificationDTOs = new LinkedList<>();

		for (AbstractNotificationModel notification : notifications) {
			NotificationDTO notificationDTO = new NotificationDTO();
			notificationDTO.setMessage(notification.getMessage());
			notificationDTO.setNotificationID(notification.getId());

			if (notification instanceof AbstractQueryNotificationModel) {
				notificationDTO.setTaskId(notificationService
						.getSubtaskModel((AbstractQueryNotificationModel) notification).getParentTask().getId());
				notificationDTO.setStatus(((AbstractQueryNotificationModel) notification).getStatus().toString());
			}
			if (notification instanceof DeclinedInvitationNotificationModel) {
				notificationDTO.setTaskId(
						((DeclinedInvitationNotificationModel) notification).getSubtask().getParentTask().getId());
			}

			if (notification instanceof AcceptedInvitationNotificationModel) {
				notificationDTO.setTaskId(
						((AcceptedInvitationNotificationModel) notification).getSubtask().getParentTask().getId());

			}
			if (notification instanceof SubmitedSolutionNotification) {
				notificationDTO.setTaskId(((SubmitedSolutionNotification) notification).getTask().getId());

			}

			notificationDTOs.add(notificationDTO);
		}

		userNotifications.setNotifications(notificationDTOs);
		return userNotifications;
	}

}