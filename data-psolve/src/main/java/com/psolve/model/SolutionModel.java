package com.psolve.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class SolutionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String solutionPath;

	@OneToOne(mappedBy = "solutionModel")
	private TaskModel task;

	@ManyToOne
	@JoinColumn(name = "solutions")
	private StudentModel student;
	
	@ManyToOne
	@JoinColumn(name = "sentSolutions")
	private TeacherModel teacher;
	
	@Column(name = "uploadTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar uploadTime;

	@Transient
	private byte[] file;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSolutionPath() {
		return solutionPath;
	}

	public void setSolutionPath(String solutionPath) {
		this.solutionPath = solutionPath;
	}

	public TaskModel getTask() {
		return task;
	}

	public void setTask(TaskModel task) {
		this.task = task;
	}

	public StudentModel getStudent() {
		return student;
	}

	public void setStudent(StudentModel student) {
		this.student = student;
	}

	public Calendar getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Calendar uploadTime) {
		this.uploadTime = uploadTime;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public TeacherModel getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherModel teacher) {
		this.teacher = teacher;
	}

}
