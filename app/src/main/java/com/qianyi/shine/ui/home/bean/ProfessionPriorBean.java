package com.qianyi.shine.ui.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class ProfessionPriorBean {
    private ProfessionPriorData data;

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

    private String code;
    private String info;

    public ProfessionPriorData getData() {
        return data;
    }

    public void setData(ProfessionPriorData data) {
        this.data = data;
    }

    public class ProfessionPriorData{
        private ProfessionPriorInfo Info;

        public ProfessionPriorInfo getInfo() {
            return Info;
        }

        public void setInfo(ProfessionPriorInfo info) {
            Info = info;
        }

        public class ProfessionPriorInfo{
            private List<ProfessionInfoList> InfoList;

            public List<ProfessionInfoList> getInfoList() {
                return InfoList;
            }

            public void setInfoList(List<ProfessionInfoList> infoList) {
                InfoList = infoList;
            }

            public class ProfessionInfoList{
                private String id;
                private String name;
                private String area;
                private String level;
                private String is_985;
                private String is_211;
                private String is_yan;
                private String rank;
                private String school_type;
                private String logo;
                private String attrtext;
                private RecruitStudents recruit_students;

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

                public String getSchool_type() {
                    return school_type;
                }

                public void setSchool_type(String school_type) {
                    this.school_type = school_type;
                }

                public String getLogo() {
                    return logo;
                }

                public void setLogo(String logo) {
                    this.logo = logo;
                }

                public String getAttrtext() {
                    return attrtext;
                }

                public void setAttrtext(String attrtext) {
                    this.attrtext = attrtext;
                }

                public RecruitStudents getRecruit_students() {
                    return recruit_students;
                }

                public void setRecruit_students(RecruitStudents recruit_students) {
                    this.recruit_students = recruit_students;
                }

                public class RecruitStudents{
                    private String major_name;
                    private String cost;
                    private String plannum;
                    private String learnyear;
                    private Record2017 record_2017;
                    private Record2016 record_2016;
                    private Record2015 record_2015;

                    public String getMajor_name() {
                        return major_name;
                    }

                    public void setMajor_name(String major_name) {
                        this.major_name = major_name;
                    }

                    public String getCost() {
                        return cost;
                    }

                    public void setCost(String cost) {
                        this.cost = cost;
                    }

                    public String getPlannum() {
                        return plannum;
                    }

                    public void setPlannum(String plannum) {
                        this.plannum = plannum;
                    }

                    public String getLearnyear() {
                        return learnyear;
                    }

                    public void setLearnyear(String learnyear) {
                        this.learnyear = learnyear;
                    }

                    public Record2017 getRecord_2017() {
                        return record_2017;
                    }

                    public void setRecord_2017(Record2017 record_2017) {
                        this.record_2017 = record_2017;
                    }

                    public Record2016 getRecord_2016() {
                        return record_2016;
                    }

                    public void setRecord_2016(Record2016 record_2016) {
                        this.record_2016 = record_2016;
                    }

                    public Record2015 getRecord_2015() {
                        return record_2015;
                    }

                    public void setRecord_2015(Record2015 record_2015) {
                        this.record_2015 = record_2015;
                    }

                    public class Record2017{
                        private String id;
                        private String school_name;
                        private String difen;
                        private String gaofen;
                        private String renshu;
                        private String weici;
                        private String rate;
                        private String risk;

                        public String getId() {
                            return id;
                        }

                        public void setId(String id) {
                            this.id = id;
                        }

                        public String getSchool_name() {
                            return school_name;
                        }

                        public void setSchool_name(String school_name) {
                            this.school_name = school_name;
                        }

                        public String getDifen() {
                            return difen;
                        }

                        public void setDifen(String difen) {
                            this.difen = difen;
                        }

                        public String getGaofen() {
                            return gaofen;
                        }

                        public void setGaofen(String gaofen) {
                            this.gaofen = gaofen;
                        }

                        public String getRenshu() {
                            return renshu;
                        }

                        public void setRenshu(String renshu) {
                            this.renshu = renshu;
                        }

                        public String getWeici() {
                            return weici;
                        }

                        public void setWeici(String weici) {
                            this.weici = weici;
                        }

                        public String getRate() {
                            return rate;
                        }

                        public void setRate(String rate) {
                            this.rate = rate;
                        }

                        public String getRisk() {
                            return risk;
                        }

                        public void setRisk(String risk) {
                            this.risk = risk;
                        }
                    }


                    public class Record2016{
                        private String id;
                        private String school_name;
                        private String difen;
                        private String gaofen;
                        private String renshu;
                        private String weici;
                        private String rate;
                        private String risk;

                        public String getId() {
                            return id;
                        }

                        public void setId(String id) {
                            this.id = id;
                        }

                        public String getSchool_name() {
                            return school_name;
                        }

                        public void setSchool_name(String school_name) {
                            this.school_name = school_name;
                        }

                        public String getDifen() {
                            return difen;
                        }

                        public void setDifen(String difen) {
                            this.difen = difen;
                        }

                        public String getGaofen() {
                            return gaofen;
                        }

                        public void setGaofen(String gaofen) {
                            this.gaofen = gaofen;
                        }

                        public String getRenshu() {
                            return renshu;
                        }

                        public void setRenshu(String renshu) {
                            this.renshu = renshu;
                        }

                        public String getWeici() {
                            return weici;
                        }

                        public void setWeici(String weici) {
                            this.weici = weici;
                        }

                        public String getRate() {
                            return rate;
                        }

                        public void setRate(String rate) {
                            this.rate = rate;
                        }

                        public String getRisk() {
                            return risk;
                        }

                        public void setRisk(String risk) {
                            this.risk = risk;
                        }
                    }

                    public class Record2015{
                        private String id;
                        private String school_name;
                        private String difen;
                        private String gaofen;
                        private String renshu;
                        private String weici;
                        private String rate;
                        private String risk;

                        public String getId() {
                            return id;
                        }

                        public void setId(String id) {
                            this.id = id;
                        }

                        public String getSchool_name() {
                            return school_name;
                        }

                        public void setSchool_name(String school_name) {
                            this.school_name = school_name;
                        }

                        public String getDifen() {
                            return difen;
                        }

                        public void setDifen(String difen) {
                            this.difen = difen;
                        }

                        public String getGaofen() {
                            return gaofen;
                        }

                        public void setGaofen(String gaofen) {
                            this.gaofen = gaofen;
                        }

                        public String getRenshu() {
                            return renshu;
                        }

                        public void setRenshu(String renshu) {
                            this.renshu = renshu;
                        }

                        public String getWeici() {
                            return weici;
                        }

                        public void setWeici(String weici) {
                            this.weici = weici;
                        }

                        public String getRate() {
                            return rate;
                        }

                        public void setRate(String rate) {
                            this.rate = rate;
                        }

                        public String getRisk() {
                            return risk;
                        }

                        public void setRisk(String risk) {
                            this.risk = risk;
                        }
                    }



                }



            }
        }

    }

}
