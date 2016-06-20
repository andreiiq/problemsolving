package com.psolve.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 * Created by andre on 4/4/2016.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class AbstractUserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "firstname", nullable = false)
	private String firstname;

	@Column(name = "lastname", nullable = false)
	private String lastname;

	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
    @Column(name = "profile_image_path")
    private String profileImagePath;

	@Column(name = "password", nullable = false)
	private String password;

	@OneToMany(mappedBy = "owner")
	private List<AbstractNotificationModel> notifications;

	@OneToMany(mappedBy = "sender")
	private List<AbstractNotificationModel> sentNotifications;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<AbstractNotificationModel> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<AbstractNotificationModel> notifications) {
		this.notifications = notifications;
	}

	public List<AbstractNotificationModel> getSentNotifications() {
		return sentNotifications;
	}

	public void setSentNotifications(List<AbstractNotificationModel> sentNotifications) {
		this.sentNotifications = sentNotifications;
	}

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }
}
