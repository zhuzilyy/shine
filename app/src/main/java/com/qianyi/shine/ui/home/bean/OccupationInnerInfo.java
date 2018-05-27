package com.qianyi.shine.ui.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/20.
 */

public class OccupationInnerInfo {
    private String zhineng_id;
    private String name;
    private String category;
    private String content;
    private String femalte_ratio;
    private String male_ratio;
    private String weburl;
    private List<JobMajor> job_major;
    private List<SalaryMarginInfo> salary_margin_all;
    private List<SalaryMarginInfo> salary_margin;
    public List<SalaryMarginInfo> getSalary_margin_all() {
        return salary_margin_all;
    }
    public void setSalary_margin_all(List<SalaryMarginInfo> salary_margin_all) {
        this.salary_margin_all = salary_margin_all;
    }

    public List<SalaryMarginInfo> getSalary_margin() {
        return salary_margin;
    }

    public void setSalary_margin(List<SalaryMarginInfo> salary_margin) {
        this.salary_margin = salary_margin;
    }
    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public String getZhineng_id() {
        return zhineng_id;
    }

    public void setZhineng_id(String zhineng_id) {
        this.zhineng_id = zhineng_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFemalte_ratio() {
        return femalte_ratio;
    }

    public void setFemalte_ratio(String femalte_ratio) {
        this.femalte_ratio = femalte_ratio;
    }

    public String getMale_ratio() {
        return male_ratio;
    }

    public void setMale_ratio(String male_ratio) {
        this.male_ratio = male_ratio;
    }

    public List<JobMajor> getJob_major() {
        return job_major;
    }

    public void setJob_major(List<JobMajor> job_major) {
        this.job_major = job_major;
    }
}
