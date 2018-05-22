package com.qianyi.shine.ui.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/7.
 */

public class CollegeDetailsBean {
    public CollegeDetailsData getData() {
        return data;
    }

    public void setData(CollegeDetailsData data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private CollegeDetailsData data;
    private String info;
    private String code;

    public class CollegeDetailsData{
        public CollegeDetailsInfo getInfo() {
            return Info;
        }

        public void setInfo(CollegeDetailsInfo info) {
            Info = info;
        }

        private CollegeDetailsInfo Info;
        public class CollegeDetailsInfo{
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getIs_985() {
                return is_985;
            }

            public void setIs_985(String is_985) {
                this.is_985 = is_985;
            }

            public String getIs_211() {
                return is_211;
            }

            public void setIs_211(String is_211) {
                this.is_211 = is_211;
            }

            public String getIs_yan() {
                return is_yan;
            }

            public void setIs_yan(String is_yan) {
                this.is_yan = is_yan;
            }

            public String getRank() {
                return rank;
            }

            public void setRank(String rank) {
                this.rank = rank;
            }

            public String getLeaer() {
                return leaer;
            }

            public void setLeaer(String leaer) {
                this.leaer = leaer;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getSchool_type() {
                return school_type;
            }

            public void setSchool_type(String school_type) {
                this.school_type = school_type;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getSexual_ratio() {
                return sexual_ratio;
            }

            public void setSexual_ratio(String sexual_ratio) {
                this.sexual_ratio = sexual_ratio;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getXxw_url_id() {
                return xxw_url_id;
            }

            public void setXxw_url_id(String xxw_url_id) {
                this.xxw_url_id = xxw_url_id;
            }

            public String getWmzy_school_id() {
                return wmzy_school_id;
            }

            public void setWmzy_school_id(String wmzy_school_id) {
                this.wmzy_school_id = wmzy_school_id;
            }

            public String getYzy_school_id() {
                return yzy_school_id;
            }

            public void setYzy_school_id(String yzy_school_id) {
                this.yzy_school_id = yzy_school_id;
            }

            public List<SchoolScenery> getSchool_scenery() {
                return school_scenery;
            }

            public void setSchool_scenery(List<SchoolScenery> school_scenery) {
                this.school_scenery = school_scenery;
            }

            public List<Major> getMajor() {
                return major;
            }

            public void setMajor(List<Major> major) {
                this.major = major;
            }

            public UniversityResearch getUniversity_research() {
                return university_research;
            }

            public void setUniversity_research(UniversityResearch university_research) {
                this.university_research = university_research;
            }

            public UniversitySatisfaction getUniversity_satisfaction() {
                return university_satisfaction;
            }

            public void setUniversity_satisfaction(UniversitySatisfaction university_satisfaction) {
                this.university_satisfaction = university_satisfaction;
            }

            public MajorRecommend getMajor_recommend() {
                return major_recommend;
            }

            public void setMajor_recommend(MajorRecommend major_recommend) {
                this.major_recommend = major_recommend;
            }

            private String id;
            private String name;
            private String area;
            private String level;
            private String is_985;
            private String is_211;
            private String is_yan;
            private String rank;
            private String leaer;
            private String type;
            private String school_type;
            private String  introduction;
            private String logo;
            private String sexual_ratio;
            private String address;
            private String website;
            private String phone;
            private String xxw_url_id;
            private String wmzy_school_id;
            private String yzy_school_id;

            private List<SchoolScenery> school_scenery;
            private List<Major> major ;
            private UniversityResearch university_research;
            private UniversitySatisfaction university_satisfaction;
            private MajorRecommend major_recommend;




            //校园照片
            public class SchoolScenery{
                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }

                public String getThumbnail_url() {
                    return thumbnail_url;
                }

                public void setThumbnail_url(String thumbnail_url) {
                    this.thumbnail_url = thumbnail_url;
                }

                public String getImage_url() {
                    return image_url;
                }

                public void setImage_url(String image_url) {
                    this.image_url = image_url;
                }

                public String getEcond_category() {
                    return econd_category;
                }

                public void setEcond_category(String econd_category) {
                    this.econd_category = econd_category;
                }

                public String getImage_ize() {
                    return image_ize;
                }

                public void setImage_ize(String image_ize) {
                    this.image_ize = image_ize;
                }

                private String category;
                private String thumbnail_url;
                private String image_url;
                private String econd_category;
                private String image_ize;
            }
            //专业
            public class Major{
                public String getCollegeName() {
                    return CollegeName;
                }

                public void setCollegeName(String collegeName) {
                    CollegeName = collegeName;
                }

                public String getCollegeId() {
                    return CollegeId;
                }

                public void setCollegeId(String collegeId) {
                    CollegeId = collegeId;
                }

                public String getDepartName() {
                    return DepartName;
                }

                public void setDepartName(String departName) {
                    DepartName = departName;
                }

                public String getMajorName() {
                    return MajorName;
                }

                public void setMajorName(String majorName) {
                    MajorName = majorName;
                }

                public String getMajorCode() {
                    return MajorCode;
                }

                public void setMajorCode(String majorCode) {
                    MajorCode = majorCode;
                }

                public String getIBen() {
                    return IBen;
                }

                public void setIBen(String IBen) {
                    this.IBen = IBen;
                }

                public String getShowLabel() {
                    return ShowLabel;
                }

                public void setShowLabel(String showLabel) {
                    ShowLabel = showLabel;
                }

                public String getOpenTime() {
                    return OpenTime;
                }

                public void setOpenTime(String openTime) {
                    OpenTime = openTime;
                }

                public String getDecription() {
                    return Decription;
                }

                public void setDecription(String decription) {
                    Decription = decription;
                }

                public String getPicUrl() {
                    return PicUrl;
                }

                public void setPicUrl(String picUrl) {
                    PicUrl = picUrl;
                }

                public String getCreationTime() {
                    return CreationTime;
                }

                public void setCreationTime(String creationTime) {
                    CreationTime = creationTime;
                }

                public String getId() {
                    return Id;
                }

                public void setId(String id) {
                    Id = id;
                }

                private String CollegeName;
                private String CollegeId;
                private String DepartName;
                private String MajorName;
                private String MajorCode;
                private String IBen;
                private String ShowLabel;
                private String OpenTime;
                private String Decription;
                private String PicUrl;
                private String CreationTime;
                private String Id;

            }

            //大学研究
            public class UniversityResearch{
                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getSchool_id() {
                    return school_id;
                }

                public void setSchool_id(String school_id) {
                    this.school_id = school_id;
                }

                public String getMaster_points() {
                    return master_points;
                }

                public void setMaster_points(String master_points) {
                    this.master_points = master_points;
                }

                public String getDoctor_points() {
                    return doctor_points;
                }

                public void setDoctor_points(String doctor_points) {
                    this.doctor_points = doctor_points;
                }

                public String getLabs_count() {
                    return labs_count;
                }

                public void setLabs_count(String labs_count) {
                    this.labs_count = labs_count;
                }

                public String getAdvantage_majors_count() {
                    return advantage_majors_count;
                }

                public void setAdvantage_majors_count(String advantage_majors_count) {
                    this.advantage_majors_count = advantage_majors_count;
                }

                public String getFaculty() {
                    return faculty;
                }

                public void setFaculty(String faculty) {
                    this.faculty = faculty;
                }

                private String id;
                private String school_id;
                private String master_points;
                private String doctor_points;
                private String labs_count;
                private String advantage_majors_count;
                private String faculty;
            }
            //大学满意度
            public class UniversitySatisfaction{
                public String getId() {
                    return id;
                }
                public void setId(String id) {
                    this.id = id;
                }
                public String getName() {
                    return name;
                }
                public void setName(String name) {
                    this.name = name;
                }

                public String getSchool_id() {
                    return school_id;
                }

                public void setSchool_id(String school_id) {
                    this.school_id = school_id;
                }

                public String getEducation() {
                    return education;
                }

                public void setEducation(String education) {
                    this.education = education;
                }

                public List<Academy> getAcademy() {
                    return academy;
                }

                public void setAcademy(List<Academy> academy) {
                    this.academy = academy;
                }

                public List<Major> getMajor() {
                    return major;
                }

                public void setMajor(List<Major> major) {
                    this.major = major;
                }

                private String id;
                private String name;
                private String school_id;
                private String education;
                private List<Academy> academy;
                private List<Major>  major;

                public class Academy{
                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getAtifaction() {
                        return atifaction;
                    }

                    public void setAtifaction(String atifaction) {
                        this.atifaction = atifaction;
                    }

                    public String getVote_num() {
                        return vote_num;
                    }

                    public void setVote_num(String vote_num) {
                        this.vote_num = vote_num;
                    }



                    private String title;
                    private String atifaction;
                    private String vote_num;
                    private List<String> percentage;

                    public List<String> getPercentage() {
                        return percentage;
                    }

                    public void setPercentage(List<String> percentage) {
                        this.percentage = percentage;
                    }
                }
                public class Major{
                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }



                    private String title;

                    public List<Data> getData() {
                        return data;
                    }

                    public void setData(List<Data> data) {
                        this.data = data;
                    }

                    private List<Data> data;
                    public class Data{
                        public String getRank() {
                            return rank;
                        }

                        public void setRank(String rank) {
                            this.rank = rank;
                        }

                        public String getVote_num() {
                            return vote_num;
                        }

                        public void setVote_num(String vote_num) {
                            this.vote_num = vote_num;
                        }

                        private String rank;
                        private String vote_num;
                        private List<String> percentage;

                        public List<String> getPercentage() {
                            return percentage;
                        }

                        public void setPercentage(List<String> percentage) {
                            this.percentage = percentage;
                        }
                    }
                }

            }
            //专业推荐
            public class MajorRecommend{
                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public Data getData() {
                    return data;
                }

                public void setData(Data data) {
                    this.data = data;
                }

                private String type;
                private  Data data;
                public class Data{
                    private String title;
                    private String avg_con;
                    private String vote_num;

                    public List<String> getPercentage() {
                        return percentage;
                    }

                    public void setPercentage(List<String> percentage) {
                        this.percentage = percentage;
                    }

                    private List<String> percentage;

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getAvg_con() {
                        return avg_con;
                    }

                    public void setAvg_con(String avg_con) {
                        this.avg_con = avg_con;
                    }

                    public String getVote_num() {
                        return vote_num;
                    }

                    public void setVote_num(String vote_num) {
                        this.vote_num = vote_num;
                    }


                }
            }



        }

    }
}
