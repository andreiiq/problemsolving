package com.psolve.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class TeacherModel extends AbstractUserModel {

	@OneToMany(mappedBy = "teacherModel")
	private List<TaskModel> ownedTasks;

	@OneToMany(mappedBy = "teacher")
	private List<SolutionModel> sentSolutions;

	public List<TaskModel> getOwnedTasks() {
		return ownedTasks;
	}

	public void setOwnedTasks(List<TaskModel> ownedTasks) {
		this.ownedTasks = ownedTasks;
	}

	public List<SolutionModel> getSentSolutions() {
		return sentSolutions;
	}

	public void setSentSolutions(List<SolutionModel> sentSolutions) {
		this.sentSolutions = sentSolutions;
	}

}
