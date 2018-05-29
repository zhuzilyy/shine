package com.qianyi.shine.ui.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/20.
 */

public class OccupationInfo {
    private String weburl;
    private OccupationInnerInfo job_info;
    public OccupationInnerInfo getJob_info() {
        return job_info;
    }

    public void setJob_info(OccupationInnerInfo job_info) {
        this.job_info = job_info;
    }
    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

}
