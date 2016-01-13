package com.badger.form;

import java.util.List;

public class TaskForm {
	List<SubtaskForm> subtasks;
	private double pointsRewarded;
	private String courseTitle;
	
	public List<SubtaskForm> getSubtasks() {
		return subtasks;
	}
	public void setSubtasks(List<SubtaskForm> subtasks) {
		this.subtasks = subtasks;
	}
	public double getPointsRewarded() {
		return pointsRewarded;
	}
	public void setPointsRewarded(double pointsRewarded) {
		this.pointsRewarded = pointsRewarded;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
}
