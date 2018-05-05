package com.qianyi.shine.fragment.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/5/2.
 */

public class FirstLevel {
    private String major_cate;
    private String major_cate_id;
    private List<SecondLevel> major_second_list;
    public String getMajor_cate() {
        return major_cate;
    }
    public void setMajor_cate(String major_cate) {
        this.major_cate = major_cate;
    }
    public List<SecondLevel> getMajor_second_list() {
        return major_second_list;
    }

    public void setMajor_second_list(List<SecondLevel> major_second_list) {
        this.major_second_list = major_second_list;
    }

    public String getMajor_cate_id() {
        return major_cate_id;
    }

    public void setMajor_cate_id(String major_cate_id) {
        this.major_cate_id = major_cate_id;
    }
}
