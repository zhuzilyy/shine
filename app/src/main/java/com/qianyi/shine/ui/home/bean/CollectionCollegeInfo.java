package com.qianyi.shine.ui.home.bean;

/**
 * Created by Administrator on 2018/5/20.
 */

public class CollectionCollegeInfo {
    private String id;
    private String member_id;
    private String school_id;
    private String school_name;
    private String create_time;
    private CollectionSchoolInfo schoolinfo;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public CollectionSchoolInfo getSchoolinfo() {
        return schoolinfo;
    }

    public void setSchoolinfo(CollectionSchoolInfo schoolinfo) {
        this.schoolinfo = schoolinfo;
    }
}
