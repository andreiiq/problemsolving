package com.badger.strategy.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.badger.strategy.EvaluationStrategy;
import com.psolve.dao.CoursePointsRepo;
import com.psolve.dao.LevelRepo;
import com.psolve.dao.StudentRepo;
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

	@Value("${percentage-points-for-mentor}")
	private double mentorPercentage;

	@Override
	@Transactional
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

		coursePointsRepo.save(coursePoints);

		studentRepo.save(student);
	}

	@Override
	@Transactional
	public void evaluateStudent(StudentModel student, SubtaskModel subtask, long grade) {
		if (student == null) {
			return;
		}

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

		StudentModel tutor = subtask.getTutor();
		if (tutor != null) {

			CoursePointsModel tutorPoints = coursePointsRepo.findByOwnerAndCourse(tutor, course);
			if (tutorPoints == null) {
				tutorPoints = new CoursePointsModel();
				tutorPoints.setCourse(course);
				tutorPoints.setOwner(tutor);

			}
			tutorPoints.setPoints(tutorPoints.getPoints() + (pointsRewarded * mentorPercentage));

			coursePointsRepo.save(tutorPoints);
		}
		coursePointsRepo.save(coursePoints);

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
				SkillModel newSkill = new SkillModel();
				newSkill.setLevelModel(gainedSkill.getLevelModel());
				newSkill.setName(gainedSkill.getName());
				newSkill.setStudent(student);
				addExperience(newSkill, gainedSkill);
				existingSkills.add(newSkill);
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

		while (sum > expNeededForLevelUp) {
			if (sum > expNeededForLevelUp) {
				long currentLevelValue = levelModel.getValue();
				LevelModel nextLevel = levelRepo.findByValue(currentLevelValue + 1);

				existingSkill.setExperience(sum - expNeededForLevelUp);
				existingSkill.setLevelModel(nextLevel);
				sum = sum - expNeededForLevelUp;
			} else {
				existingSkill.setExperience(sum);
				break;
			}
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
