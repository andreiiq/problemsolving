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
public class SubtaskModel extends AbstractTaskModel {
	@ManyToOne
	@JoinColumn(name = "subtasks")
	private TaskModel parentTask;

	@OneToOne
	private SkillModel skillModelGained;

	@OneToMany
	private List<LevelModel> levelsRequired;

	@OneToOne
	private StudentModel studentModel;

	@OneToOne
	private StudentModel tutor;

	@Column(name = "pointsRewarded")
	private double pointsRewarded;

	public TaskModel getParentTask() {
		return parentTask;
	}

	public void setParentTask(TaskModel parentTask) {
		this.parentTask = parentTask;
	}

	public SkillModel getSkillModelGained() {
		return skillModelGained;
	}

	public void setSkillModelGained(SkillModel skillModelGained) {
		this.skillModelGained = skillModelGained;
	}

	public List<LevelModel> getLevelsRequired() {
		return levelsRequired;
	}

	public void setLevelsRequired(List<LevelModel> levelsRequired) {
		this.levelsRequired = levelsRequired;
	}

	public StudentModel getStudentModel() {
		return studentModel;
	}

	public void setStudentModel(StudentModel studentModel) {
		this.studentModel = studentModel;
	}

	public StudentModel getTutor() {
		return tutor;
	}

	public void setTutor(StudentModel tutor) {
		this.tutor = tutor;
	}

	public double getPointsRewarded() {
		return pointsRewarded;
	}

	public void setPointsRewarded(double pointsRewarded) {
		this.pointsRewarded = pointsRewarded;
	}
	
}
