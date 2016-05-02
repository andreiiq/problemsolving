package com.psolve.model;

import javax.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class StudentModel extends AbstractUserModel {

    @Column(name = "education")
    private String education;

    @Column(name = "profile_image_path")
    private String profileImagePath;

    @Transient
    private byte[] profileImage;

    @Column(name = "skypeID")
    private String skypeID;

    @OneToMany
    private List<CoursePointsModel> points;

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public String getSkypeID() {
        return skypeID;
    }

    public void setSkypeID(String skypeID) {
        this.skypeID = skypeID;
    }

    public List<CoursePointsModel> getPoints() {
        return points;
    }

    public void setPoints(List<CoursePointsModel> points) {
        this.points = points;
    }
}
