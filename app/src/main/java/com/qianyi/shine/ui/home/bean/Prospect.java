package com.qianyi.shine.ui.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/18.
 */

public class Prospect {
    private String id;
    private String wmzy_school_id;
    private String salary_factor_rank_index;
    private String salary_factor;
    private String sample_count;
    private String salary_over_ratio;
    private String over_than_general_salary;
    private String sch_loc_ratio;
    private String most_city;
    private String most_major;
    private String most_industry;
    private List<CityListInfo> city_list;
    private List<SalaryMajorInfo> salary_major_list;
    private List<IndInfo> ind_info_list;
    private List<MajorListInfo> major_list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWmzy_school_id() {
        return wmzy_school_id;
    }

    public void setWmzy_school_id(String wmzy_school_id) {
        this.wmzy_school_id = wmzy_school_id;
    }

    public String getSalary_factor_rank_index() {
        return salary_factor_rank_index;
    }

    public void setSalary_factor_rank_index(String salary_factor_rank_index) {
        this.salary_factor_rank_index = salary_factor_rank_index;
    }

    public String getSalary_factor() {
        return salary_factor;
    }

    public void setSalary_factor(String salary_factor) {
        this.salary_factor = salary_factor;
    }

    public String getSample_count() {
        return sample_count;
    }

    public void setSample_count(String sample_count) {
        this.sample_count = sample_count;
    }

    public String getSalary_over_ratio() {
        return salary_over_ratio;
    }

    public void setSalary_over_ratio(String salary_over_ratio) {
        this.salary_over_ratio = salary_over_ratio;
    }

    public String getOver_than_general_salary() {
        return over_than_general_salary;
    }

    public void setOver_than_general_salary(String over_than_general_salary) {
        this.over_than_general_salary = over_than_general_salary;
    }

    public String getSch_loc_ratio() {
        return sch_loc_ratio;
    }

    public void setSch_loc_ratio(String sch_loc_ratio) {
        this.sch_loc_ratio = sch_loc_ratio;
    }

    public String getMost_city() {
        return most_city;
    }

    public void setMost_city(String most_city) {
        this.most_city = most_city;
    }

    public String getMost_major() {
        return most_major;
    }

    public void setMost_major(String most_major) {
        this.most_major = most_major;
    }

    public String getMost_industry() {
        return most_industry;
    }

    public void setMost_industry(String most_industry) {
        this.most_industry = most_industry;
    }

    public List<CityListInfo> getCity_list() {
        return city_list;
    }

    public void setCity_list(List<CityListInfo> city_list) {
        this.city_list = city_list;
    }

    public List<SalaryMajorInfo> getSalary_major_list() {
        return salary_major_list;
    }

    public void setSalary_major_list(List<SalaryMajorInfo> salary_major_list) {
        this.salary_major_list = salary_major_list;
    }

    public List<IndInfo> getInd_info_list() {
        return ind_info_list;
    }

    public void setInd_info_list(List<IndInfo> ind_info_list) {
        this.ind_info_list = ind_info_list;
    }

    public List<MajorListInfo> getMajor_list() {
        return major_list;
    }

    public void setMajor_list(List<MajorListInfo> major_list) {
        this.major_list = major_list;
    }
}
