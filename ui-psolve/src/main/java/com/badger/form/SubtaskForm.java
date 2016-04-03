package com.badger.form;

import java.util.List;

/**
 * Class used to store information of a SubtaskModel sent by a User.
 */
public class SubtaskForm {
    private String title;
    private String description;
    private List<SkillForm> skills;
    private double pointsRewarded;

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

    public List<SkillForm> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillForm> skills) {
        this.skills = skills;
    }

    public double getPointsRewarded() {
        return pointsRewarded;
    }

    public void setPointsRewarded(double pointsRewarded) {
        this.pointsRewarded = pointsRewarded;
    }
}