package com.qianyi.shine.fragment.entity;

/**
 * Created by Administrator on 2018/3/29.
 */

public class CollegeEntity {
    private int collegeImg;
    private String collegeUrl;
    private String collegeName;
    private String collegeDes;

    public int getCollegeImg() {
        return collegeImg;
    }

    public void setCollegeImg(int collegeImg) {
        this.collegeImg = collegeImg;
    }

    public String getCollegeUrl() {
        return collegeUrl;
    }

    public void setCollegeUrl(String collegeUrl) {
        this.collegeUrl = collegeUrl;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCollegeDes() {
        return collegeDes;
    }

    public void setCollegeDes(String collegeDes) {
        this.collegeDes = collegeDes;
    }

    public CollegeEntity(int collegeImg, String collegeUrl, String collegeName, String collegeDes) {

        this.collegeImg = collegeImg;
        this.collegeUrl = collegeUrl;
        this.collegeName = collegeName;
        this.collegeDes = collegeDes;
    }
}
