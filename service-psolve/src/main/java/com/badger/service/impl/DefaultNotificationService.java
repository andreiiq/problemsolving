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
import com.psolve.model.AcceptedInvitationNotificationModel;
import com.psolve.model.DeclinedInvitationNotificationModel;
import com.psolve.model.InviteMentorNotificationModel;
import com.psolve.model.InviteTaskNotificationModel;
import com.psolve.model.NotificationStatus;
import com.psolve.model.StudentModel;
import com.psolve.model.SubmitedSolutionNotification;
import com.psolve.model.SubtaskModel;
import com.psolve.model.TaskModel;
import com.psolve.model.TeacherModel;

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

	@Value("${notif.task.invite.accepted.message}")
	private String acceptedSubtaskInviteMessage;

	@Value("${notif.task.invite.decline.message}")
	private String declinedSubtaskInviteMessage;

	@Value("${notif.task.invite.accepted.sender.message}")
	private String acceptedSenderSubtaskInviteMessage;

	@Value("${notif.task.invite.declined.sender.message}")
	private String declinedSenderSubtaskInviteMessage;
	
	@Value("${notif.task.submitted.solution}")
	private String submiteSolutionMessage;

	@Override
	public List<AbstractNotificationModel> getNewNotifications() {
		AbstractUserModel user = userService.getCurrentUser();

		List<AbstractNotificationModel> notifications = notifRepo.findByOwnerOrderByCreatedAtAsc(user);
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

		if (notification instanceof InviteTaskNotificationModel) {
			subtask.setStudent((StudentModel) userService.getCurrentUser());
		}

		if (notification instanceof InviteMentorNotificationModel) {
			subtask.setTutor((StudentModel) userService.getCurrentUser());
		}

		AcceptedInvitationNotificationModel notificationModel = buildAcceptInviteNotification(subtask,
				(StudentModel) notification.getSender());

		taskService.saveTask(subtask);
		notifRepo.save(notificationModel);
		queryNotificationRepo.save(notification);
	}

	@Override
	public void rejectQueryNotification(long notificationID) {
		AbstractQueryNotificationModel notification = (AbstractQueryNotificationModel) this
				.findNotificationByID(notificationID);

		SubtaskModel subtask = getSubtaskModel(notification);

		notification.setStatus(NotificationStatus.REJECTED);

		DeclinedInvitationNotificationModel notificationModel = buildDeclineInvitationNotification(subtask,
				(StudentModel) notification.getSender());

		notifRepo.save(notificationModel);
		queryNotificationRepo.save(notification);
	}

	@Override
	public AbstractNotificationModel findNotificationByID(long id) {
		return notifRepo.findOne(id);
	}

	@Override
	public long countNotSeenNotifications() {
		AbstractUserModel userModel = userService.getCurrentUser();
		return notifRepo.countByUnseenIsFalseAndOwner(userModel);
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

	@Override
	public void sendSubmitSolutionNotification(String receiverEmail, long taskID) {
		TeacherModel teacherModel = (TeacherModel) userService.findUserByEmail(receiverEmail);
		TaskModel taskModel = (TaskModel) taskService.findTaskByID(taskID);

		notifRepo.save(buildSolutionNotification(taskModel, teacherModel));

	}

	private InviteTaskNotificationModel buildInviteToTaskNotification(SubtaskModel subtask, StudentModel student) {
		InviteTaskNotificationModel notificationModel = new InviteTaskNotificationModel();

		notificationModel.setStatus(NotificationStatus.PENDING);
		notificationModel.setSubtask(subtask);
		notificationModel.setOwner(student);
		notificationModel.setSender(userService.getCurrentUser());
		return notificationModel;
	}

	private InviteMentorNotificationModel buildInviteMentorNotification(SubtaskModel subtask, StudentModel student) {
		InviteMentorNotificationModel notificationModel = new InviteMentorNotificationModel();

		notificationModel.setStatus(NotificationStatus.PENDING);
		notificationModel.setSubtask(subtask);
		notificationModel.setOwner(student);
		notificationModel.setSender(userService.getCurrentUser());
		return notificationModel;
	}

	private AcceptedInvitationNotificationModel buildAcceptInviteNotification(SubtaskModel subtask,
			StudentModel student) {
		AcceptedInvitationNotificationModel notificationModel = new AcceptedInvitationNotificationModel();

		notificationModel.setSubtask(subtask);
		notificationModel.setOwner(student);
		notificationModel.setSender(userService.getCurrentUser());
		return notificationModel;
	}

	private DeclinedInvitationNotificationModel buildDeclineInvitationNotification(SubtaskModel subtask,
			StudentModel student) {
		DeclinedInvitationNotificationModel declinedNotification = new DeclinedInvitationNotificationModel();

		declinedNotification.setSubtask(subtask);
		declinedNotification.setOwner(student);
		declinedNotification.setSender(userService.getCurrentUser());
		return declinedNotification;
	}

	private SubmitedSolutionNotification buildSolutionNotification(TaskModel taskModel, TeacherModel teacherModel) {
		SubmitedSolutionNotification notificationModel = new SubmitedSolutionNotification();

		notificationModel.setTask(taskModel);
		notificationModel.setOwner(teacherModel);
		notificationModel.setSender(userService.getCurrentUser());
		return notificationModel;
	}

	public SubtaskModel getSubtaskModel(AbstractQueryNotificationModel notification) {
		if (notification instanceof InviteMentorNotificationModel) {
			return ((InviteMentorNotificationModel) notification).getSubtask();
		}
		if (notification instanceof InviteTaskNotificationModel) {
			return ((InviteTaskNotificationModel) notification).getSubtask();
		}
		return null;
	}

	private void buildNotificationMessages(List<AbstractNotificationModel> notifs) {
		for (AbstractNotificationModel notif : notifs) {
			AbstractUserModel user = notif.getOwner();
			if (notif instanceof InviteTaskNotificationModel) {
				SubtaskModel subtask = ((InviteTaskNotificationModel) notif).getSubtask();

				String notifMessage;
				switch (((InviteTaskNotificationModel) notif).getStatus()) {
				case ACCEPTED:
					notifMessage = MessageFormat.format(acceptedSenderSubtaskInviteMessage, subtask.getTitle());
					break;
				case REJECTED:
					notifMessage = MessageFormat.format(declinedSenderSubtaskInviteMessage, subtask.getTitle());
					break;
				default:
					notifMessage = buildInviteTaskMesssage(notif.getSender(), subtask);
					break;
				}
				notif.setMessage(notifMessage);
			}

			if (notif instanceof InviteMentorNotificationModel) {
				SubtaskModel subtask = ((InviteMentorNotificationModel) notif).getSubtask();

				String notifMessage;
				switch (((InviteMentorNotificationModel) notif).getStatus()) {
				case ACCEPTED:
					notifMessage = MessageFormat.format(acceptedSenderSubtaskInviteMessage, subtask.getTitle());
					break;
				case REJECTED:
					notifMessage = MessageFormat.format(declinedSenderSubtaskInviteMessage, subtask.getTitle());
					break;
				default:
					notifMessage = buildInviteMentorMesssage(notif.getSender(), subtask);
					break;
				}
				notif.setMessage(notifMessage);
			}

			if (notif instanceof AcceptedInvitationNotificationModel) {
				SubtaskModel subtask = ((AcceptedInvitationNotificationModel) notif).getSubtask();
				notif.setMessage(buildAcceptedSubtaskMessage(notif.getSender(), subtask));
			}

			if (notif instanceof DeclinedInvitationNotificationModel) {
				SubtaskModel subtask = ((DeclinedInvitationNotificationModel) notif).getSubtask();
				notif.setMessage(builddeclinedSubtaskMessage(notif.getSender(), subtask));
			}
			
			if (notif instanceof SubmitedSolutionNotification) {
				TaskModel taskModel = ((SubmitedSolutionNotification) notif).getTask();
				notif.setMessage(buildSubmitSolutionMessage(notif.getSender(), taskModel));
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

	private String buildAcceptedSubtaskMessage(AbstractUserModel user, SubtaskModel subtask) {
		String firstName = user.getFirstname();
		String lastName = user.getLastname();

		String subtaskTitle = subtask.getTitle();
		String notifMessage = MessageFormat.format(acceptedSubtaskInviteMessage, firstName + " " + lastName,
				subtaskTitle);
		return notifMessage;
	}

	private String builddeclinedSubtaskMessage(AbstractUserModel user, SubtaskModel subtask) {
		String firstName = user.getFirstname();
		String lastName = user.getLastname();

		String subtaskTitle = subtask.getTitle();
		String notifMessage = MessageFormat.format(acceptedSubtaskInviteMessage, firstName + " " + lastName,
				subtaskTitle);
		return notifMessage;
	}

	private String buildInviteMentorMesssage(AbstractUserModel user, SubtaskModel subtask) {
		String firstName = user.getFirstname();
		String lastName = user.getLastname();

		String subtaskTitle = subtask.getTitle();
		String notifMessage = MessageFormat.format(inviteMentorMessage, firstName + " " + lastName, subtaskTitle);
		return notifMessage;
	}
	
	private String buildSubmitSolutionMessage(AbstractUserModel user, TaskModel taskModel) {
		String firstName = user.getFirstname();
		String lastName = user.getLastname();

		String subtaskTitle = taskModel.getTitle();
		String notifMessage = MessageFormat.format(submiteSolutionMessage, firstName + " " + lastName, subtaskTitle);
		return notifMessage;
	}
}
