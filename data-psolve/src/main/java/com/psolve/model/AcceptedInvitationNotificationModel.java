package com.psolve.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AcceptedInvitationNotificationModel extends AbstractNotificationModel {
	@ManyToOne
	@JoinColumn(name = "subtasks")
	private SubtaskModel subtask;

	public SubtaskModel getSubtask() {
		return subtask;
	}

	public void setSubtask(SubtaskModel subtask) {
		this.subtask = subtask;
	}

}
