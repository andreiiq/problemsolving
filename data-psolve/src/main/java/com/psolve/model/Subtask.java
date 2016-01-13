package com.psolve.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Subtask extends AbstractTask {
	@ManyToOne
	@JoinColumn(name = "subtasks")
	private Task parentTask;

	@OneToOne
	private Skill skillGained;

	@OneToMany
	private List<Level> levelsRequired;

	@OneToOne
	private Student student;

	@OneToOne
	private Student tutor;

	@Column(name = "pointsRewarded")
	private double pointsRewarded;

	public Task getParentTask() {
		return parentTask;
	}

	public void setParentTask(Task parentTask) {
		this.parentTask = parentTask;
	}

	public Skill getSkillGained() {
		return skillGained;
	}

	public void setSkillGained(Skill skillGained) {
		this.skillGained = skillGained;
	}

	public List<Level> getLevelsRequired() {
		return levelsRequired;
	}

	public void setLevelsRequired(List<Level> levelsRequired) {
		this.levelsRequired = levelsRequired;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Student getTutor() {
		return tutor;
	}

	public void setTutor(Student tutor) {
		this.tutor = tutor;
	}

	public double getPointsRewarded() {
		return pointsRewarded;
	}

	public void setPointsRewarded(double pointsRewarded) {
		this.pointsRewarded = pointsRewarded;
	}
	
}
