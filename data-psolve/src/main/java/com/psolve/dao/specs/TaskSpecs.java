package com.psolve.dao.specs;

import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.psolve.model.CourseModel;
import com.psolve.model.CourseModel_;
import com.psolve.model.LevelModel;
import com.psolve.model.LevelModel_;
import com.psolve.model.SkillModel;
import com.psolve.model.SkillModel_;
import com.psolve.model.StudentModel;
import com.psolve.model.StudentModel_;
import com.psolve.model.SubtaskModel;
import com.psolve.model.SubtaskModel_;
import com.psolve.model.TaskModel;
import com.psolve.model.TaskModel_;

/**
 * Created by andre on 4/17/2016.
 */
public class TaskSpecs {

	public static Specification<TaskModel> titleContains(String title) {
		return (root, query, builder) -> {
			return builder.like(builder.lower(root.get(TaskModel_.title)), modifyForContains(title));
		};
	}

	public static Specification<TaskModel> ownedBy(String student) {
		return (root, query, builder) -> {
			Join<TaskModel, StudentModel> ownerJoin = root.join("student");
			return builder.equal(ownerJoin.get(StudentModel_.email), student);
		};
	}

	public static Specification<TaskModel> fromCourse(String course) {
		return (root, query, builder) -> {
			Join<TaskModel, CourseModel> courseJoin = root.join(TaskModel_.courseModel);
			return builder.like(builder.lower(courseJoin.get(CourseModel_.title)), modifyForContains(course));
		};
	}

	public static Specification<TaskModel> hasAtLeastGivenPoints(double points) {
		return (root, query, builder) -> {
			return builder.greaterThanOrEqualTo(root.get(TaskModel_.pointsRewarded), points);
		};
	}

	public static Specification<TaskModel> levelRange(long fromLevel, long toLevel) {
		return (root, query, builder) -> {
			ParameterExpression<Long> fromLevelParam = builder.parameter(Long.class, String.valueOf(fromLevel));
			ParameterExpression<Long> toLevelParam = builder.parameter(Long.class, String.valueOf(toLevel));

			Join<SubtaskModel, LevelModel> levelJoin = root.join(TaskModel_.subtaskModels)
					.join(SubtaskModel_.levelsRequired);
			return builder.between(levelJoin.get(LevelModel_.value), fromLevelParam, toLevelParam);
		};
	}

	public static Specification<TaskModel> containsSkills(Map<String, String> skills) {
		return (root, query, builder) -> {

			Join<SubtaskModel, SkillModel> skillJoin = root.join(TaskModel_.subtaskModels)
					.join(SubtaskModel_.skillsGained);

			Join<SkillModel, LevelModel> levelJoin = skillJoin.join(SkillModel_.levelModel);

			Predicate containsSkills = null;

			for (Entry<String, String> entry : skills.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				
				Predicate levelRange =  builder.lessThanOrEqualTo(levelJoin.get(LevelModel_.value), Long.valueOf(value));
				Predicate entryPredicate = builder
						.and(builder.equal(skillJoin.get(SkillModel_.name), key), levelRange);

				if (containsSkills != null) {
					containsSkills = builder.and(entryPredicate, containsSkills);
				} else {
					containsSkills = entryPredicate;
				}
			}
			
			return containsSkills;
		};
	}

	private static String modifyForContains(String searchTerm) {
		if (searchTerm == null || searchTerm.isEmpty()) {
			return "%";
		} else {
			return "%" + searchTerm.toLowerCase() + "%";
		}
	}
}
