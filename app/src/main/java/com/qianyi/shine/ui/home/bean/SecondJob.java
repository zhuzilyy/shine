package com.qianyi.shine.ui.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/5.
 */

public class SecondJob {
    private String cate_two_name;
    private String cate_two_id;
    private List<ThirdJob> cate_three_list;

    public String getCate_two_name() {
        return cate_two_name;
    }

    public void setCate_two_name(String cate_two_name) {
        this.cate_two_name = cate_two_name;
    }

    public String getCate_two_id() {
        return cate_two_id;
    }

    public void setCate_two_id(String cate_two_id) {
        this.cate_two_id = cate_two_id;
    }
    public List<ThirdJob> getCate_three_list() {
        return cate_three_list;
    }

    public void setCate_three_list(List<ThirdJob> cate_three_list) {
        this.cate_three_list = cate_three_list;
    }
}
