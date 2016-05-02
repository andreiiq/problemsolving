package com.psolve.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class TeacherModel extends AbstractUserModel {


    @OneToMany(mappedBy = "teacherModel")
    private List<TaskModel> ownedTasks;

    public List<TaskModel> getOwnedTasks() {
        return ownedTasks;
    }

    public void setOwnedTasks(List<TaskModel> ownedTasks) {
        this.ownedTasks = ownedTasks;
    }
}
