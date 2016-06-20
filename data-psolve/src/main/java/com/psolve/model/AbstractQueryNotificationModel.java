package com.psolve.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Inheritance
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public abstract class AbstractQueryNotificationModel extends AbstractNotificationModel {
	
	@Enumerated(EnumType.STRING)
	private NotificationStatus status;

	public NotificationStatus getStatus() {
		return status;
	}

	public void setStatus(NotificationStatus status) {
		this.status = status;
	}

}
