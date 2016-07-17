package com.psolve.dao.specs;

import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.psolve.model.SkillModel;
import com.psolve.model.SkillModel_;
import com.psolve.model.StudentModel;
import com.psolve.model.StudentModel_;

/**
 * Created by andre on 4/10/2016.
 */
@Component
public class UserSpecs {

	public static Specification<StudentModel> studentNameContains(String firstname, String lastname) {
		return (root, query, builder) -> {
			return builder.or(
					builder.like(builder.lower(root.get(StudentModel_.firstname)), modifyForContains(firstname)),
					builder.like(builder.lower(root.get(StudentModel_.lastname)), modifyForContains(lastname)),
					builder.like(builder.lower(root.get(StudentModel_.firstname)), modifyForContains(lastname)),
					builder.like(builder.lower(root.get(StudentModel_.lastname)), modifyForContains(firstname)));
		};

	}

	public static Specification<StudentModel> hasSkills(List<SkillModel> skillModels) {

		return (root, query, builder) -> {

			Join<StudentModel, SkillModel> skillJoin = root.join(StudentModel_.skills);

			Predicate containsSkills = null;

			for (SkillModel skillModel : skillModels) {
				if (containsSkills != null) {
					containsSkills = builder.and(builder.equal(skillJoin.get(SkillModel_.id), skillModel.getId()),
							containsSkills);
				} else {
					containsSkills = builder.equal(skillJoin.get(SkillModel_.name), skillModel.getName());
				}
			}

			return containsSkills;
		};

	}

	public static Specification<StudentModel> notCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) auth.getPrincipal();

		return (root, query, builder) -> {
			return builder.notEqual(root.get(StudentModel_.email), email);
		};

	}

	public static Specification<StudentModel> studentNameContains(String name) {
		return (root, query, builder) -> {
			return builder.or(builder.like(builder.lower(root.get(StudentModel_.firstname)), modifyForContains(name)),
					builder.like(builder.lower(root.get(StudentModel_.lastname)), modifyForContains(name)));
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
