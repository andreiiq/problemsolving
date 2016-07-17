package com.badger.dto;

import java.util.List;

public class TaskDTO {
	private long id;
	private String title;
	private String description;
	private String course;
	private double points;
	private String ownerEmail;
	private String teacherEmail;
	private boolean hasSolution;
	private boolean isOwned; 
	private SolutionDTO solutionDTO;

	private List<SubtaskDTO> subtasks;

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

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public List<SubtaskDTO> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(List<SubtaskDTO> subtasks) {
		this.subtasks = subtasks;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public boolean isHasSolution() {
		return hasSolution;
	}

	public void setHasSolution(boolean hasSolution) {
		this.hasSolution = hasSolution;
	}

	public boolean isOwned() {
		return isOwned;
	}

	public void setOwned(boolean isOwned) {
		this.isOwned = isOwned;
	}

	public SolutionDTO getSolutionDTO() {
		return solutionDTO;
	}

	public void setSolutionDTO(SolutionDTO solutionDTO) {
		this.solutionDTO = solutionDTO;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}
	

}
