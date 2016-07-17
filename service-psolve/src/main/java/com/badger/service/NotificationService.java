package com.badger.service;

import java.util.List;

import com.psolve.model.AbstractNotificationModel;
import com.psolve.model.AbstractQueryNotificationModel;
import com.psolve.model.SubtaskModel;

public interface NotificationService {
	List<AbstractNotificationModel> getNewNotifications();

	void acceptQueryNotification(long notificationID);

	void rejectQueryNotification(long notificationID);

	AbstractNotificationModel findNotificationByID(long id);

	long countNotSeenNotifications();

	void sendAssignToTaskNotification(String receiverEmail, long taskID);

	void sendAssignAsMentorNotification(String receiverEmail, long taskID);

	SubtaskModel getSubtaskModel(AbstractQueryNotificationModel notification);

	void sendSubmitSolutionNotification(String receiverEmail, long taskID);
}
