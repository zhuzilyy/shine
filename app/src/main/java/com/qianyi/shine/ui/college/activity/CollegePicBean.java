package com.qianyi.shine.ui.college.activity;

/**
 * Created by Administrator on 2018/5/7.
 */

public class CollegePicBean {
    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public CollegePicBean(String thumbnail_url, String image_url) {
        this.thumbnail_url = thumbnail_url;
        this.image_url = image_url;
    }

    public String getImage_url() {

        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    private String thumbnail_url;
    private String image_url;
}
