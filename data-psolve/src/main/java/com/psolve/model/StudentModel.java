package com.psolve.model;

import javax.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class StudentModel extends AbstractUserModel {

	@Column(name = "education")
	private String education;

	@Transient
	private byte[] profileImage;

	@Column(name = "skypeID")
	private String skypeID;

	@OneToMany(mappedBy = "owner")
	private List<CoursePointsModel> points;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<SkillModel> skills;

	@OneToMany(mappedBy = "student")
	private List<AbstractTaskModel> tasks;

	@OneToMany(mappedBy = "student")
	private List<SolutionModel> solutions;

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public byte[] getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}

	public String getSkypeID() {
		return skypeID;
	}

	public void setSkypeID(String skypeID) {
		this.skypeID = skypeID;
	}

	public List<CoursePointsModel> getPoints() {
		return points;
	}

	public void setPoints(List<CoursePointsModel> points) {
		this.points = points;
	}

	public List<SolutionModel> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<SolutionModel> solutions) {
		this.solutions = solutions;
	}

	public List<SkillModel> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillModel> skills) {
		this.skills = skills;
	}

	public List<AbstractTaskModel> getTasks() {
		return tasks;
	}

	public void setTasks(List<AbstractTaskModel> tasks) {
		this.tasks = tasks;
	}

}
