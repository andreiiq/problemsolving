package com.badger.service;

import java.util.List;

import com.psolve.model.AbstractNotificationModel;
import com.psolve.model.AbstractQueryNotificationModel;

public interface NotificationService {
	List<AbstractQueryNotificationModel> getNewNotifications();

	void acceptQueryNotification(long notificationID);

	void rejectQueryNotification(long notificationID);

	AbstractNotificationModel findNotificationByID(long id);

	long countNotSeenNotifications();

	void sendAssignToTaskNotification(String receiverEmail, long taskID);

	void sendAssignAsMentorNotification(String receiverEmail, long taskID);
}
