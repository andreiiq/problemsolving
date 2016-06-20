package com.psolve.dao.specs;

import javax.persistence.criteria.Join;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.psolve.model.AbstractUserModel;
import com.psolve.model.LevelModel;
import com.psolve.model.LevelModel_;
import com.psolve.model.SkillModel;
import com.psolve.model.SkillModel_;
import com.psolve.model.StudentModel;
import com.psolve.model.StudentModel_;

@Component
public class SkillsSpec {
	
	
	public static Specification<SkillModel> sortByLevel() {
		return (root, query, builder) -> {
			Join<SkillModel, LevelModel> levelJoin = root.join(SkillModel_.levelModel);
			query.orderBy(builder.desc(levelJoin.get(LevelModel_.value)));
			return query.getRestriction();
		};
	}

	public static Specification<SkillModel> skillsForUser(StudentModel student) {
		return (root, query, builder) -> {
			Join<SkillModel, StudentModel> levelJoin = root.join(SkillModel_.)

			query.orderBy(builder.desc(levelJoin.get(LevelModel_.value)));
			return query.getRestriction();
		};
	}
	}
}
