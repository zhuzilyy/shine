package com.qianyi.shine.ui.college.entivity;

import java.util.List;

/**
 * Created by Administrator on 2018/5/20.
 */

public class ProfessionBean {
    public ProfessionData getData() {
        return data;
    }

    public void setData(ProfessionData data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private ProfessionData data;
    private String code;
    private String info;

    public class ProfessionData{
        public ProfessionInfo getInfo() {
            return Info;
        }

        public void setInfo(ProfessionInfo info) {
            Info = info;
        }

        private ProfessionInfo Info;

        public class ProfessionInfo{
            private MajorInfo major_info;

            public MajorInfo getMajor_info() {
                return major_info;
            }

            public void setMajor_info(MajorInfo major_info) {
                this.major_info = major_info;
            }

            public class MajorInfo{
                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getMajor_name() {
                    return major_name;
                }

                public void setMajor_name(String major_name) {
                    this.major_name = major_name;
                }

                public String getMajor_id() {
                    return major_id;
                }

                public void setMajor_id(String major_id) {
                    this.major_id = major_id;
                }

                public String getSalary_factor_rank_index() {
                    return salary_factor_rank_index;
                }

                public void setSalary_factor_rank_index(String salary_factor_rank_index) {
                    this.salary_factor_rank_index = salary_factor_rank_index;
                }

                public String getSalary5() {
                    return salary5;
                }

                public void setSalary5(String salary5) {
                    this.salary5 = salary5;
                }

                public String getLoc_name() {
                    return loc_name;
                }

                public void setLoc_name(String loc_name) {
                    this.loc_name = loc_name;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getPhysique() {
                    return physique;
                }

                public void setPhysique(String physique) {
                    this.physique = physique;
                }

                public String getIndustry_name() {
                    return industry_name;
                }

                public void setIndustry_name(String industry_name) {
                    this.industry_name = industry_name;
                }

                public String getSalary_over_ratio() {
                    return salary_over_ratio;
                }

                public void setSalary_over_ratio(String salary_over_ratio) {
                    this.salary_over_ratio = salary_over_ratio;
                }

                public String getMale() {
                    return male;
                }

                public void setMale(String male) {
                    this.male = male;
                }

                public String getFemalte() {
                    return femalte;
                }

                public void setFemalte(String femalte) {
                    this.femalte = femalte;
                }

                public List<MajorSalaryYearList> getMajor_salary_year_list() {
                    return major_salary_year_list;
                }

                public void setMajor_salary_year_list(List<MajorSalaryYearList> major_salary_year_list) {
                    this.major_salary_year_list = major_salary_year_list;
                }

                public List<GeneralSalaryYearList> getGeneral_salary_year_list() {
                    return general_salary_year_list;
                }

                public void setGeneral_salary_year_list(List<GeneralSalaryYearList> general_salary_year_list) {
                    this.general_salary_year_list = general_salary_year_list;
                }

                public List<ZhinengList> getZhineng_list() {
                    return zhineng_list;
                }

                public void setZhineng_list(List<ZhinengList> zhineng_list) {
                    this.zhineng_list = zhineng_list;
                }

                public List<IndInfoList> getInd_info_list() {
                    return ind_info_list;
                }

                public void setInd_info_list(List<IndInfoList> ind_info_list) {
                    this.ind_info_list = ind_info_list;
                }

                public List<CityList> getCity_list() {
                    return city_list;
                }

                public void setCity_list(List<CityList> city_list) {
                    this.city_list = city_list;
                }

                private String id;
                private String major_name;
                private String major_id;
                private String salary_factor_rank_index;
                private String salary5;
                private String loc_name;
                private String description;
                private String physique;
                private String industry_name;
                private String salary_over_ratio;

                private String male;
                private String femalte;


                private List<MajorSalaryYearList> major_salary_year_list;
                private List<GeneralSalaryYearList> general_salary_year_list;
                private List<ZhinengList> zhineng_list;
                private List<IndInfoList> ind_info_list;
                private List<CityList>  city_list;
                private List<Major> major;

                public List<Major> getMajor() {
                    return major;
                }

                public void setMajor(List<Major> major) {
                    this.major = major;
                }

                public class Major{
                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getDscr() {
                        return dscr;
                    }

                    public void setDscr(String dscr) {
                        this.dscr = dscr;
                    }

                    private String title;
                    private String dscr;
                }


                public class MajorSalaryYearList{
                    private String after_graduation_year;
                    private String sample_count;
                    private String salary;
                    private String sample_count_of_year;
                    private String virtual_flag;

                    public String getAfter_graduation_year() {
                        return after_graduation_year;
                    }

                    public void setAfter_graduation_year(String after_graduation_year) {
                        this.after_graduation_year = after_graduation_year;
                    }

                    public String getSample_count() {
                        return sample_count;
                    }

                    public void setSample_count(String sample_count) {
                        this.sample_count = sample_count;
                    }

                    public String getSalary() {
                        return salary;
                    }

                    public void setSalary(String salary) {
                        this.salary = salary;
                    }

                    public String getSample_count_of_year() {
                        return sample_count_of_year;
                    }

                    public void setSample_count_of_year(String sample_count_of_year) {
                        this.sample_count_of_year = sample_count_of_year;
                    }

                    public String getVirtual_flag() {
                        return virtual_flag;
                    }

                    public void setVirtual_flag(String virtual_flag) {
                        this.virtual_flag = virtual_flag;
                    }
                }


                public class GeneralSalaryYearList{
                    private String after_graduation_year;
                    private String salary;
                    private String sample_count_of_year;
                    private String virtual_flag;

                    public String getAfter_graduation_year() {
                        return after_graduation_year;
                    }

                    public void setAfter_graduation_year(String after_graduation_year) {
                        this.after_graduation_year = after_graduation_year;
                    }

                    public String getSalary() {
                        return salary;
                    }

                    public void setSalary(String salary) {
                        this.salary = salary;
                    }

                    public String getSample_count_of_year() {
                        return sample_count_of_year;
                    }

                    public void setSample_count_of_year(String sample_count_of_year) {
                        this.sample_count_of_year = sample_count_of_year;
                    }

                    public String getVirtual_flag() {
                        return virtual_flag;
                    }

                    public void setVirtual_flag(String virtual_flag) {
                        this.virtual_flag = virtual_flag;
                    }
                }

                public class ZhinengList{
                    private String zhineng_name;
                    private String sample_count;
                    private String ratio;
                    private List<String> position_list;
                    private List<String> industry_list;

                    public String getZhineng_name() {
                        return zhineng_name;
                    }

                    public void setZhineng_name(String zhineng_name) {
                        this.zhineng_name = zhineng_name;
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

                    public List<String> getPosition_list() {
                        return position_list;
                    }

                    public void setPosition_list(List<String> position_list) {
                        this.position_list = position_list;
                    }

                    public List<String> getIndustry_list() {
                        return industry_list;
                    }

                    public void setIndustry_list(List<String> industry_list) {
                        this.industry_list = industry_list;
                    }
                }

                public class IndInfoList{
                    private String industry_id;
                    private String industry_name;
                    private String sample_count;
                    private String ratio;

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
                }

                public class CityList{
                    private String loc_id;
                    private String loc_name;
                    private String sample_count;
                    private String ratio;

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
                }

            }
        }
    }
}
