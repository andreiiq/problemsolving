package com.psolve.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class TaskModel extends AbstractTaskModel {

	@OneToMany(mappedBy = "parentTask")
	private List<SubtaskModel> subtaskModels;
	
	@OneToOne
	@JoinColumn(name = "teacher_id")
	private TeacherModel teacherModel;
	
	@Column(name = "pointsRewarded")
	private double pointsRewarded;
	
	@OneToOne
	@JoinColumn(name = "student_id")
	private StudentModel studentModel;
	
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

	public double getPointsRewarded() {
		return pointsRewarded;
	}

	public void setPointsRewarded(double pointsRewarded) {
		this.pointsRewarded = pointsRewarded;
	}

	public StudentModel getStudentModel() {
		return studentModel;
	}

	public void setStudentModel(StudentModel studentModel) {
		this.studentModel = studentModel;
	}

	public CourseModel getCourseModel() {
		return courseModel;
	}

	public void setCourseModel(CourseModel courseModel) {
		this.courseModel = courseModel;
	}
	
}
