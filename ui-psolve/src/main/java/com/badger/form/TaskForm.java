package com.badger.form;

import java.util.List;

/**
 * Class used to store information of a sutask sent by a User.
 */
public class TaskForm {
	private long id;
	private long grade;
	private String title;
	private String course;
	private String description;
	private double pointsRewarded;
	private boolean isOwnTask;

	private List<SubtaskForm> subtasks;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPointsRewarded() {
		return pointsRewarded;
	}

	public void setPointsRewarded(double pointsRewarded) {
		this.pointsRewarded = pointsRewarded;
	}

	public List<SubtaskForm> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(List<SubtaskForm> subtasks) {
		this.subtasks = subtasks;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getGrade() {
		return grade;
	}

	public void setGrade(long grade) {
		this.grade = grade;
	}

	public boolean isOwnTask() {
		return isOwnTask;
	}

	public void setOwnTask(boolean isOwnTask) {
		this.isOwnTask = isOwnTask;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	

}
