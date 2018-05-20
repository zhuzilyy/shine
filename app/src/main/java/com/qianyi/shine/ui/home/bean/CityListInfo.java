package com.qianyi.shine.ui.home.bean;

/**
 * Created by Administrator on 2018/5/18.
 */

public class CityListInfo {
    private String sch_id;
    private String loc_id;
    private String loc_name;
    private String sample_count;
    private String ratio;
    private String etl_date;
    public String getSch_id() {
        return sch_id;
    }

    public void setSch_id(String sch_id) {
        this.sch_id = sch_id;
    }

    public String getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(String loc_id) {
        this.loc_id = loc_id;
    }

    public String getLoc_name() {
        return loc_name;
    }

    public void setLoc_name(String loc_name) {
        this.loc_name = loc_name;
    }

    public String getSample_count() {
        return sample_count;
    }

    public void setSample_count(String sample_count) {
        this.sample_count = sample_count;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getEtl_date() {
        return etl_date;
    }

    public void setEtl_date(String etl_date) {
        this.etl_date = etl_date;
    }
}
