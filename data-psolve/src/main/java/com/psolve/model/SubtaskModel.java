package com.psolve.model;

import java.util.List;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class SubtaskModel extends AbstractTaskModel {
	@ManyToOne
	@JoinColumn(name = "subtasks")
	private TaskModel parentTask;

	@OneToMany(cascade = CascadeType.ALL)
	private List<SkillModel> skillsGained;

	@OneToMany(cascade = CascadeType.ALL)
	private List<LevelModel> levelsRequired;

	@OneToOne
	private StudentModel tutor;

	public TaskModel getParentTask() {
		return parentTask;
	}

	public void setParentTask(TaskModel parentTask) {
		this.parentTask = parentTask;
	}


	public List<SkillModel> getSkillsGained() {
		return skillsGained;
	}

	public void setSkillsGained(List<SkillModel> skillsGained) {
		this.skillsGained = skillsGained;
	}

	public List<LevelModel> getLevelsRequired() {
		return levelsRequired;
	}

	public void setLevelsRequired(List<LevelModel> levelsRequired) {
		this.levelsRequired = levelsRequired;
	}

	public StudentModel getTutor() {
		return tutor;
	}

	public void setTutor(StudentModel tutor) {
		this.tutor = tutor;
	}

}
