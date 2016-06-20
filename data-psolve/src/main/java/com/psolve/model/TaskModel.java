package com.psolve.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class TaskModel extends AbstractTaskModel {

	@OneToMany(mappedBy = "parentTask", cascade= CascadeType.ALL)
	private List<SubtaskModel> subtaskModels;
	
	@ManyToOne
	@JoinColumn(name = "ownedTasks")
	private TeacherModel teacherModel;
	
	@OneToOne
	@JoinColumn(name = "course_id")
	private CourseModel courseModel;
	
	@OneToOne
	@JoinColumn(name="solution_id", referencedColumnName="id")
	private SolutionModel solutionModel;
	
	public List<SubtaskModel> getSubtaskModels() {
		return subtaskModels;
	}

	public void setSubtaskModels(List<SubtaskModel> subtaskModels) {
		this.subtaskModels = subtaskModels;
	}

	public TeacherModel getTeacherModel() {
		return teacherModel;
	}

	public void setTeacherModel(TeacherModel teacherModel) {
		this.teacherModel = teacherModel;
	}

	public CourseModel getCourseModel() {
		return courseModel;
	}

	public void setCourseModel(CourseModel courseModel) {
		this.courseModel = courseModel;
	}

	public SolutionModel getSolutionModel() {
		return solutionModel;
	}

	public void setSolutionModel(SolutionModel solutionModel) {
		this.solutionModel = solutionModel;
	}
	
	
	
}
