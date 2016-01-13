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
public class Task extends AbstractTask {

	@OneToMany(mappedBy = "parentTask")
	private List<Subtask> subtasks;
	
	@OneToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	
	@Column(name = "pointsRewarded")
	private double pointsRewarded;
	
	@OneToOne
	@JoinColumn(name = "student_id")
	private Student student;
	
	@OneToOne
	@JoinColumn(name = "course_id")
	private Course course;

	public List<Subtask> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(List<Subtask> subtasks) {
		this.subtasks = subtasks;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public double getPointsRewarded() {
		return pointsRewarded;
	}

	public void setPointsRewarded(double pointsRewarded) {
		this.pointsRewarded = pointsRewarded;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
}
