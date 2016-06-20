package com.badger.strategy.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.badger.strategy.EvaluationStrategy;
import com.psolve.dao.AbstractTaskRepo;
import com.psolve.dao.CoursePointsRepo;
import com.psolve.dao.LevelRepo;
import com.psolve.dao.StudentRepo;
import com.psolve.model.AbstractTaskModel;
import com.psolve.model.CourseModel;
import com.psolve.model.CoursePointsModel;
import com.psolve.model.LevelModel;
import com.psolve.model.SkillModel;
import com.psolve.model.StudentModel;
import com.psolve.model.SubtaskModel;
import com.psolve.model.TaskModel;

@Component
public class DefaultEvaluationStrategy implements EvaluationStrategy {

	@Autowired
	CoursePointsRepo coursePointsRepo;

	@Autowired
	LevelRepo levelRepo;

	@Autowired
	StudentRepo studentRepo;

	@Value("${exprate-for-high-level}")
	private double rateForHigherLevel;

	@Value("${exprate-for-same-level}")
	private double rateForSameLevel;

	@Value("${exprate-for-low-level}")
	private double rateForLowerLevel;

	@Override
	public void evaluateStudent(StudentModel student, TaskModel task, long grade) {
		double pointsRewarded = computeReceivedPoints(task.getPointsRewarded(), grade);

		CourseModel course = task.getCourseModel();

		CoursePointsModel coursePoints = coursePointsRepo.findByOwnerAndCourse(student, course);
		if (coursePoints == null) {
			coursePoints = new CoursePointsModel();
			coursePoints.setCourse(course);
			coursePoints.setOwner(student);

		}

		double points = coursePoints.getPoints();
		coursePoints.setPoints(points + pointsRewarded);
		student.getPoints().add(coursePoints);

		studentRepo.save(student);
	}

	@Override
	public void evaluateStudent(StudentModel student, SubtaskModel subtask, long grade) {
		double pointsRewarded = computeReceivedPoints(subtask.getPointsRewarded(), grade);

		TaskModel project = subtask.getParentTask();
		CourseModel course = project.getCourseModel();

		CoursePointsModel coursePoints = coursePointsRepo.findByOwnerAndCourse(student, course);
		if (coursePoints == null) {
			coursePoints = new CoursePointsModel();
			coursePoints.setCourse(course);
			coursePoints.setOwner(student);

		}

		double points = coursePoints.getPoints();
		coursePoints.setPoints(points + pointsRewarded);
		student.getPoints().add(coursePoints);

		List<SkillModel> existingSkills = student.getSkills();
		List<SkillModel> gainedSkills = subtask.getSkillsGained();

		for (SkillModel gainedSkill : gainedSkills) {
			if (existingSkills.contains(gainedSkill)) {
				for (SkillModel existingSkill : existingSkills) {
					if (existingSkill.equals(gainedSkill)) {
						addExperience(existingSkill, gainedSkill);

					}
				}
			} else {
				existingSkills.add(gainedSkill);
			}
		}

		studentRepo.save(student);
	}

	protected double computeReceivedPoints(double points, long grade) {
		return ((grade * 10) / 100) * points;
	}

	protected void addExperience(SkillModel existingSkill, SkillModel gainedSkill) {
		double experience = existingSkill.getExperience();
		double experienceGained = computeExperienceGained(existingSkill, gainedSkill);
		double sum = experience + experienceGained;

		LevelModel levelModel = existingSkill.getLevelModel();
		double expNeededForLevelUp = levelModel.getXpNeeded();

		if (sum > expNeededForLevelUp) {
			long currentLevelValue = levelModel.getValue();
			LevelModel nextLevel = levelRepo.findByValue(currentLevelValue + 1);

			existingSkill.setExperience(sum - expNeededForLevelUp);
			existingSkill.setLevelModel(nextLevel);
		} else {
			existingSkill.setExperience(sum);
		}

	}

	protected double computeExperienceGained(SkillModel existingSkill, SkillModel gainedSkill) {
		long existingLevel = existingSkill.getLevelModel().getValue();
		long requiredLevel = gainedSkill.getLevelModel().getValue();

		double experienceRate = computeExperienceRate(existingLevel, requiredLevel);

		return gainedSkill.getExperience() * experienceRate;
	}

	protected double computeExperienceRate(long existingLevel, long requiredLevel) {
		if (existingLevel < requiredLevel) {
			return rateForHigherLevel;
		} else if (existingLevel < requiredLevel) {
			return rateForLowerLevel;
		} else {
			return rateForSameLevel;
		}
	}

}
