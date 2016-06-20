package com.badger.dto;

import java.util.List;

public class UserNotificationsDTO {
	private List<NotificationDTO> notifications;

	public List<NotificationDTO> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<NotificationDTO> notifications) {
		this.notifications = notifications;
	}

}
