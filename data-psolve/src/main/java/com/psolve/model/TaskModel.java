package com.psolve.model;

import java.util.List;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class TaskModel extends AbstractTaskModel {

	@OneToMany(mappedBy = "parentTask", cascade= CascadeType.ALL)
	private List<SubtaskModel> subtaskModels;
	
	@OneToOne
	@JoinColumn(name = "teacher_id")
	private TeacherModel teacherModel;
	
	@OneToOne
	@JoinColumn(name = "course_id")
	private CourseModel courseModel;

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
	
}
