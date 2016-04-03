package com.badger.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Class used to store information of a sutask sent by a User.
 */
public class TaskForm {
    @NotNull
    @NotEmpty
    private String title;
    private String description;
    private double pointsRewarded;
    private List<SubtaskForm> subtasks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPointsRewarded() {
        return pointsRewarded;
    }

    public void setPointsRewarded(double pointsRewarded) {
        this.pointsRewarded = pointsRewarded;
    }

    public List<SubtaskForm> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<SubtaskForm> subtasks) {
        this.subtasks = subtasks;
    }
}
