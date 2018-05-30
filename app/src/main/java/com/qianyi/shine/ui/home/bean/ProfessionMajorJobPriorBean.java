package com.qianyi.shine.ui.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */

public class ProfessionMajorJobPriorBean {
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

    public ProfessionMajorJobPriorData getData() {
        return data;
    }

    public void setData(ProfessionMajorJobPriorData data) {
        this.data = data;
    }

    private String code;
    private String info;
    private ProfessionMajorJobPriorData data;

    public class ProfessionMajorJobPriorData{
        public ProfessionMajorJobPriorInfo getInfo() {
            return Info;
        }

        public void setInfo(ProfessionMajorJobPriorInfo info) {
            Info = info;
        }

        private ProfessionMajorJobPriorInfo Info;

        public class ProfessionMajorJobPriorInfo{

            public List<ProfessionMajorJobPriorList> getPriorSchoolList() {
                return PriorSchoolList;
            }

            public void setPriorSchoolList(List<ProfessionMajorJobPriorList> priorSchoolList) {
                PriorSchoolList = priorSchoolList;
            }

            private List<ProfessionMajorJobPriorList> PriorSchoolList;

            public class ProfessionMajorJobPriorList{

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

                public List<EnrollArr> getEnroll_arr() {
                    return enroll_arr;
                }

                public void setEnroll_arr(List<EnrollArr> enroll_arr) {
                    this.enroll_arr = enroll_arr;
                }

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
                private List<EnrollArr> enroll_arr;

                public class EnrollArr{
                    public String getZhuanye() {
                        return zhuanye;
                    }

                    public void setZhuanye(String zhuanye) {
                        this.zhuanye = zhuanye;
                    }

                    public Rate getRate() {
                        return rate;
                    }

                    public void setRate(Rate rate) {
                        this.rate = rate;
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

                    private String zhuanye;
                    private Rate rate;
                    private Record2017 record_2017;
                    private Record2016 record_2016;
                    private Record2015 record_2015;

                    public class Rate{
                        public String getRate() {
                            return rate;
                        }

                        public void setRate(String rate) {
                            this.rate = rate;
                        }

                        public String getPici() {
                            return pici;
                        }

                        public void setPici(String pici) {
                            this.pici = pici;
                        }

                        public String getDifen() {
                            return difen;
                        }

                        public void setDifen(String difen) {
                            this.difen = difen;
                        }

                        public String getYear() {
                            return year;
                        }

                        public void setYear(String year) {
                            this.year = year;
                        }

                        public String getRisk() {
                            return risk;
                        }

                        public void setRisk(String risk) {
                            this.risk = risk;
                        }

                        public String getZhuanye() {
                            return zhuanye;
                        }

                        public void setZhuanye(String zhuanye) {
                            this.zhuanye = zhuanye;
                        }

                        private String rate;
                        private String pici;
                        private String difen;
                        private String year;
                        private String risk;
                        private String zhuanye;

                    }
                    public class Record2017{
                        public String getZhuanye() {
                            return zhuanye;
                        }

                        public void setZhuanye(String zhuanye) {
                            this.zhuanye = zhuanye;
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

                        private String zhuanye;
                        private String difen;
                        private String gaofen;
                        private String renshu;
                        private String weici;

                    }
                    public class Record2016{
                        public String getZhuanye() {
                            return zhuanye;
                        }

                        public void setZhuanye(String zhuanye) {
                            this.zhuanye = zhuanye;
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

                        private String zhuanye;
                        private String difen;
                        private String gaofen;
                        private String renshu;
                        private String weici;

                    }
                    public class Record2015{
                        private String zhuanye;
                        private String difen;
                        private String gaofen;
                        private String renshu;
                        private String weici;

                        public String getZhuanye() {
                            return zhuanye;
                        }

                        public void setZhuanye(String zhuanye) {
                            this.zhuanye = zhuanye;
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
                    }

                }

            }

        }
    }
}
