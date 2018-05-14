package com.qianyi.shine.ui.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/5.
 */

public class FirstJob {
    private String cate_one_name;
    private String cate_one_id;
    private List<SecondJob> cate_two_list;
    public String getCate_one_name() {
        return cate_one_name;
    }

    public void setCate_one_name(String cate_one_name) {
        this.cate_one_name = cate_one_name;
    }

    public String getCate_one_id() {
        return cate_one_id;
    }

    public void setCate_one_id(String cate_one_id) {
        this.cate_one_id = cate_one_id;
    }
    public List<SecondJob> getCate_two_list() {
        return cate_two_list;
    }

    public void setCate_two_list(List<SecondJob> cate_two_list) {
        this.cate_two_list = cate_two_list;
    }
}
