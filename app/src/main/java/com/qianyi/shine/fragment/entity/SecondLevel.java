package com.qianyi.shine.fragment.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/5/2.
 */

public class SecondLevel {
    private String major_second_cate;
    private String major_second_cate_id;
    private List<ThirdLevel> major_list;

    public String getMajor_second_cate() {
        return major_second_cate;
    }

    public void setMajor_second_cate(String major_second_cate) {
        this.major_second_cate = major_second_cate;
    }

    public String getMajor_second_cate_id() {
        return major_second_cate_id;
    }

    public void setMajor_second_cate_id(String major_second_cate_id) {
        this.major_second_cate_id = major_second_cate_id;
    }

    public List<ThirdLevel> getMajor_list() {
        return major_list;
    }

    public void setMajor_list(List<ThirdLevel> major_list) {
        this.major_list = major_list;
    }
}
