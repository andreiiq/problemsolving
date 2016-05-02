package com.psolve.dao.specs;

import com.psolve.model.StudentModel;
import com.psolve.model.StudentModel_;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by andre on 4/10/2016.
 */
public final class UserSpecs {
    public static Specification<StudentModel> studentNameContains(String firstname, String lastname) {
        return (root, query, builder) -> {
            return builder.or(builder.like(builder.lower(root.get(StudentModel_.firstname)), modifyForContains(firstname)),
                    builder.like(builder.lower(root.get(StudentModel_.lastname)), modifyForContains(lastname)),
                    builder.like(builder.lower(root.get(StudentModel_.firstname)), modifyForContains(lastname)),
                    builder.like(builder.lower(root.get(StudentModel_.lastname)), modifyForContains(firstname)));
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
