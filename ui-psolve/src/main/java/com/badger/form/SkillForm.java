package com.badger.form;

/**
 * Class used to hold the information of a skill sent by a User.
 */
public class SkillForm {

    private String name;
    private long level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }
}
