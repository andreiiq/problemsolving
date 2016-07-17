package com.psolve.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SubmitedSolutionNotification extends AbstractNotificationModel {
	@ManyToOne
	@JoinColumn(name = "tasks")
	private TaskModel task;

	public TaskModel getTask() {
		return task;
	}

	public void setTask(TaskModel task) {
		this.task = task;
	}


	
}
