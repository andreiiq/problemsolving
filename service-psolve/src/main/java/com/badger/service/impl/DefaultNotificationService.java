package com.badger.service.impl;

import java.text.MessageFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.badger.service.NotificationService;
import com.badger.service.TaskService;
import com.badger.service.UserService;
import com.psolve.dao.NotificationRepo;
import com.psolve.dao.QueryNotificationRepo;
import com.psolve.model.AbstractNotificationModel;
import com.psolve.model.AbstractQueryNotificationModel;
import com.psolve.model.AbstractUserModel;
import com.psolve.model.InviteMentorNotificationModel;
import com.psolve.model.InviteTaskNotificationModel;
import com.psolve.model.NotificationStatus;
import com.psolve.model.StudentModel;
import com.psolve.model.SubtaskModel;

@Service
public class DefaultNotificationService implements NotificationService {
	@Autowired
	private UserService userService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private QueryNotificationRepo<AbstractQueryNotificationModel> queryNotificationRepo;

	@Autowired
	private NotificationRepo<AbstractNotificationModel> notifRepo;

	@Value("${notif.task.invite.message}")
	private String inviteTaskMessage;

	@Value("${notif.mentor.invite.message}")
	private String inviteMentorMessage;

	@Override
	public List<AbstractQueryNotificationModel> getNewNotifications() {
		AbstractUserModel user = userService.getCurrentUser();

		List<AbstractQueryNotificationModel> notifications = queryNotificationRepo
				.findByOwnerAndStatusOrderByCreatedAtAsc(user, NotificationStatus.PENDING);
		buildNotificationMessages(notifications);

		for (AbstractNotificationModel notification : notifications) {
			notification.setUnseen(true);
		}

		notifRepo.save(notifications);
		return notifications;
	}

	@Override
	@Transactional
	public void acceptQueryNotification(long notificationID) {
		AbstractQueryNotificationModel notification = (AbstractQueryNotificationModel) this
				.findNotificationByID(notificationID);

		SubtaskModel subtask = getSubtaskModel(notification);
		notification.setStatus(NotificationStatus.ACCEPTED);

		subtask.setStudent((StudentModel) userService.getCurrentUser());
		taskService.saveTask(subtask);
		queryNotificationRepo.save(notification);
	}

	@Override
	public void rejectQueryNotification(long notificationID) {
		AbstractQueryNotificationModel notification = (AbstractQueryNotificationModel) this
				.findNotificationByID(notificationID);

		notification.setStatus(NotificationStatus.REJECTED);
		queryNotificationRepo.save(notification);
	}

	@Override
	public AbstractNotificationModel findNotificationByID(long id) {
		return notifRepo.findOne(id);
	}

	@Override
	public long countNotSeenNotifications() {
		return notifRepo.countByUnseenIsFalse();
	}

	@Override
	public void sendAssignToTaskNotification(String receiverEmail, long taskID) {
		StudentModel student = (StudentModel) userService.findUserByEmail(receiverEmail);
		SubtaskModel subtask = (SubtaskModel) taskService.findTaskByID(taskID);
		
		notifRepo.save(buildInviteToTaskNotification(subtask, student));
	}

	@Override
	public void sendAssignAsMentorNotification(String receiverEmail, long taskID) {
		StudentModel student = (StudentModel) userService.findUserByEmail(receiverEmail);
		SubtaskModel subtask = (SubtaskModel) taskService.findTaskByID(taskID);

		notifRepo.save(buildInviteMentorNotification(subtask, student));

	}

	private InviteTaskNotificationModel buildInviteToTaskNotification(SubtaskModel subtask, StudentModel student) {
		InviteTaskNotificationModel notificationModel = new InviteTaskNotificationModel();

		notificationModel.setStatus(NotificationStatus.PENDING);
		notificationModel.setSubtask(subtask);
		notificationModel.setOwner(student);
		return notificationModel;
	}

	private InviteMentorNotificationModel buildInviteMentorNotification(SubtaskModel subtask, StudentModel student) {
		InviteMentorNotificationModel notificationModel = new InviteMentorNotificationModel();

		notificationModel.setStatus(NotificationStatus.PENDING);
		notificationModel.setSubtask(subtask);
		notificationModel.setOwner(student);
		return notificationModel;
	}

	private SubtaskModel getSubtaskModel(AbstractQueryNotificationModel notification) {
		if (notification instanceof InviteMentorNotificationModel) {
			return ((InviteMentorNotificationModel) notification).getSubtask();
		}
		if (notification instanceof InviteTaskNotificationModel) {
			return ((InviteTaskNotificationModel) notification).getSubtask();
		}
		return null;
	}

	private void buildNotificationMessages(List<AbstractQueryNotificationModel> notifs) {
		for (AbstractQueryNotificationModel notif : notifs) {
			AbstractUserModel user = notif.getOwner();
			if (notif instanceof InviteTaskNotificationModel) {
				SubtaskModel subtask = ((InviteTaskNotificationModel) notif).getSubtask();
				notif.setMessage(buildInviteTaskMesssage(user, subtask));
			}

			if (notif instanceof InviteMentorNotificationModel) {
				SubtaskModel subtask = ((InviteMentorNotificationModel) notif).getSubtask();
				notif.setMessage(buildInviteMentorMesssage(user, subtask));
			}
		}
	}

	private String buildInviteTaskMesssage(AbstractUserModel user, SubtaskModel subtask) {
		String firstName = user.getFirstname();
		String lastName = user.getLastname();

		String subtaskTitle = subtask.getTitle();
		String notifMessage = MessageFormat.format(inviteTaskMessage, firstName + " " + lastName, subtaskTitle);
		return notifMessage;
	}

	private String buildInviteMentorMesssage(AbstractUserModel user, SubtaskModel subtask) {
		return buildInviteTaskMesssage(user, subtask);
	}
}
