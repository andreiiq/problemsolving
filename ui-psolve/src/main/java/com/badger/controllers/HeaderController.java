package com.badger.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.psolve.model.AbstractQueryNotificationModel;
import com.psolve.model.StudentModel;

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
	@RequestMapping(path = "/getNotifications", method = RequestMethod.GET, produces = "application/json")
	public UserNotificationsDTO getNewTasks(@AuthenticationPrincipal String email) {
		List<AbstractQueryNotificationModel> notifications = notificationService.getNewNotifications();
		return buildNotificationsDTO(notifications);

	}
	
	
	@ResponseBody
	@RequestMapping(value = "/countNotifications", method = RequestMethod.GET, produces = "application/json")
	public long getUnseenNotifications() {
		return notificationService.countNotSeenNotifications();
	}

	
	@RequestMapping(path = "/acceptNotifiation", method = RequestMethod.POST)
	public void acceptNotification(@RequestParam long notificationID) {
		System.out.println("Intra");
		notificationService.acceptQueryNotification(notificationID);
	}

	@RequestMapping(path = "/rejectNotification", method = RequestMethod.POST)
	public void rejectNotification(@RequestParam long notificationID) {
		notificationService.rejectQueryNotification(notificationID);
	}

	private UserNotificationsDTO buildNotificationsDTO(List<AbstractQueryNotificationModel> notifications) {
		UserNotificationsDTO userNotifications = new UserNotificationsDTO();
		List<NotificationDTO> notificationDTOs = new LinkedList<>();

		for (AbstractQueryNotificationModel notification : notifications) {
			NotificationDTO notificationDTO = new NotificationDTO();
			notificationDTO.setMessage(notification.getMessage());
			notificationDTO.setNotificationID(notification.getId());

			notificationDTOs.add(notificationDTO);
		}

		userNotifications.setNotifications(notificationDTOs);
		return userNotifications;
	}

}