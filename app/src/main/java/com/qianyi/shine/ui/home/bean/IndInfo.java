package com.qianyi.shine.ui.home.bean;

/**
 * Created by Administrator on 2018/5/18.
 */

public class IndInfo {
    private String sch_id;
    private String industry_id;
    private String industry_name;
    private String sample_count;
    private String ratio;
    private String etl_date;
    public String getSch_id() {
        return sch_id;
    }

    public void setSch_id(String sch_id) {
        this.sch_id = sch_id;
    }

    public String getIndustry_id() {
        return industry_id;
    }

    public void setIndustry_id(String industry_id) {
        this.industry_id = industry_id;
    }

    public String getIndustry_name() {
        return industry_name;
    }

    public void setIndustry_name(String industry_name) {
        this.industry_name = industry_name;
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
