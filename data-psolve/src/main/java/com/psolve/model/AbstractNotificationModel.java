package com.psolve.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractNotificationModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdAt;

	@ManyToOne
	@JoinColumn(name = "notifications")
	private AbstractUserModel owner;
	
	
	@ManyToOne
	@JoinColumn(name = "sentNotifications")
	private AbstractUserModel sender;
	
	@Column(name = "unseen")
	private boolean unseen;

	@Transient
	private String message;


	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public AbstractUserModel getOwner() {
		return owner;
	}

	public void setOwner(AbstractUserModel owner) {
		this.owner = owner;
	}

	public AbstractUserModel getSender() {
		return sender;
	}

	public void setSender(AbstractUserModel sender) {
		this.sender = sender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isUnseen() {
		return unseen;
	}

	public void setUnseen(boolean unseen) {
		this.unseen = unseen;
	}

}
