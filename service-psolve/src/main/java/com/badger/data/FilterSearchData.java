package com.badger.data;

import java.util.Map;

public class FilterSearchData {
	private boolean ownedByCurrentUser;
	private String title;
	private int fromLevel;
	private int toLevel;
	private String course;
	private long points;
	private Map<String, String> skills;
	
	public boolean isOwnedByCurrentUser() {
		return ownedByCurrentUser;
	}
	public void setOwnedByCurrentUser(boolean ownedByCurrentUser) {
		this.ownedByCurrentUser = ownedByCurrentUser;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public int getFromLevel() {
		return fromLevel;
	}
	public void setFromLevel(int fromLevel) {
		this.fromLevel = fromLevel;
	}
	public int getToLevel() {
		return toLevel;
	}
	public void setToLevel(int toLevel) {
		this.toLevel = toLevel;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
	public Map<String, String> getSkills() {
		return skills;
	}
	public void setSkills(Map<String, String> skills) {
		this.skills = skills;
	}
	public long getPoints() {
		return points;
	}
	public void setPoints(long points) {
		this.points = points;
	}

}
