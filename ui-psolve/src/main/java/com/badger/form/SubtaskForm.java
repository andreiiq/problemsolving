package com.badger.form;

import java.util.List;

public class SubtaskForm {
	private String title;
	private String description;
	private List<String> levelsRequired;
	private double pointsRewarded;

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

	public List<String> getLevelsRequired() {
		return levelsRequired;
	}

	public void setLevelsRequired(List<String> levelsRequired) {
		this.levelsRequired = levelsRequired;
	}

	public double getPointsRewarded() {
		return pointsRewarded;
	}

	public void setPointsRewarded(double pointsRewarded) {
		this.pointsRewarded = pointsRewarded;
	}

}