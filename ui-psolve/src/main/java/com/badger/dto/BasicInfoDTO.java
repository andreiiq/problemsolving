package com.badger.dto;

import java.util.List;

public class BasicInfoDTO {
	List<CoursePointsDTO> coursePointsDTOs;
	List<SkillDTO> skillDTOs;

	public List<CoursePointsDTO> getCoursePointsDTOs() {
		return coursePointsDTOs;
	}

	public void setCoursePointsDTOs(List<CoursePointsDTO> coursePointsDTOs) {
		this.coursePointsDTOs = coursePointsDTOs;
	}

	public List<SkillDTO> getSkillDTOs() {
		return skillDTOs;
	}

	public void setSkillDTOs(List<SkillDTO> skillDTOs) {
		this.skillDTOs = skillDTOs;
	}

}
