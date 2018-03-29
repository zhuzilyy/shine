package com.qianyi.shine.fragment.entity;

/**
 * Created by Administrator on 2018/3/29.
 */

public class TestEntity {
    private int img;
    private String des;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public TestEntity(int img, String des) {

        this.img = img;
        this.des = des;
    }
}
