package com.badger.form;

import java.util.Map;

/**
 * Created by andre on 4/17/2016.
 */
public class TaskFilterForm {
	private String title;
	private boolean ownProject;
	private String course;
	private long points;
	private Map<String, Long> skills;

	public boolean isOwnProject() {
		return ownProject;
	}

	public void setOwnProject(boolean ownProject) {
		this.ownProject = ownProject;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}

	public Map<String, Long> getSkills() {
		return skills;
	}

	public void setSkills(Map<String, Long> skills) {
		this.skills = skills;
	}

	private int page;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
