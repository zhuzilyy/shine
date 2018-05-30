package com.qianyi.shine.ui.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/30.
 */

public class PrefessionBean {
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

    public PrefessionData getData() {
        return data;
    }

    public void setData(PrefessionData data) {
        this.data = data;
    }

    private String code;
    private String info;
    private PrefessionData data;


    public class PrefessionData{
        private PrefessionInfo Info;

        public PrefessionInfo getInfo() {
            return Info;
        }

        public void setInfo(PrefessionInfo info) {
            Info = info;
        }

        public class PrefessionInfo{
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

            public Scoreinfo getScoreinfo() {
                return scoreinfo;
            }

            public void setScoreinfo(Scoreinfo scoreinfo) {
                this.scoreinfo = scoreinfo;
            }

            private String id;
            private String school_name;
            private Scoreinfo scoreinfo;

            public List<EnrollArr> getEnroll_arr() {
                return enroll_arr;
            }

            public void setEnroll_arr(List<EnrollArr> enroll_arr) {
                this.enroll_arr = enroll_arr;
            }

            private List<EnrollArr> enroll_arr;

            public class Scoreinfo{
                public Record2017 getRecord_2017() {
                    return record_2017;
                }

                public void setRecord_2017(Record2017 record_2017) {
                    this.record_2017 = record_2017;
                }

                public Record2017 getRecord_2016() {
                    return record_2016;
                }

                public void setRecord_2016(Record2017 record_2016) {
                    this.record_2016 = record_2016;
                }

                public Record2017 getRecord_2015() {
                    return record_2015;
                }

                public void setRecord_2015(Record2017 record_2015) {
                    this.record_2015 = record_2015;
                }

                private Record2017 record_2017;
                private Record2017 record_2016;
                private Record2017 record_2015;

                public class Record2017{
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

                    public String getH() {
                        return h;
                    }

                    public void setH(String h) {
                        this.h = h;
                    }

                    public String getWeici() {
                        return weici;
                    }

                    public void setWeici(String weici) {
                        this.weici = weici;
                    }

                    private String difen;
                    private String gaofen;
                    private String renshu;
                    private String h;
                    private String weici;

                }
                public class Record2016{
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

                    public String getH() {
                        return h;
                    }

                    public void setH(String h) {
                        this.h = h;
                    }

                    public String getWeici() {
                        return weici;
                    }

                    public void setWeici(String weici) {
                        this.weici = weici;
                    }

                    private String difen;
                    private String gaofen;
                    private String renshu;
                    private String h;
                    private String weici;

                }
                public class Record2015{
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

                    public String getH() {
                        return h;
                    }

                    public void setH(String h) {
                        this.h = h;
                    }

                    public String getWeici() {
                        return weici;
                    }

                    public void setWeici(String weici) {
                        this.weici = weici;
                    }

                    private String difen;
                    private String gaofen;
                    private String renshu;
                    private String h;
                    private String weici;

                }



            }

            public class EnrollArr{
                public String getZhuanye() {
                    return zhuanye;
                }

                public void setZhuanye(String zhuanye) {
                    this.zhuanye = zhuanye;
                }

                public String getPici() {
                    return pici;
                }

                public void setPici(String pici) {
                    this.pici = pici;
                }

                public Record_2017 getRecord_2017() {
                    return record_2017;
                }

                public void setRecord_2017(Record_2017 record_2017) {
                    this.record_2017 = record_2017;
                }

                public Record_2017 getRecord_2016() {
                    return record_2016;
                }

                public void setRecord_2016(Record_2017 record_2016) {
                    this.record_2016 = record_2016;
                }

                public Record_2017 getRecord_2015() {
                    return record_2015;
                }

                public void setRecord_2015(Record_2017 record_2015) {
                    this.record_2015 = record_2015;
                }

                private String zhuanye;
                private String pici;
                private Record_2017 record_2017;
                private Record_2017 record_2016;
                private Record_2017 record_2015;

                public class Record_2017{
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

                    public String getPici() {
                        return pici;
                    }

                    public void setPici(String pici) {
                        this.pici = pici;
                    }

                    private String zhuanye;
                    private String difen;
                    private String gaofen;
                    private String renshu;
                    private String weici;
                    private String pici;

                }
                public class Record_2016{
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

                    public String getPici() {
                        return pici;
                    }

                    public void setPici(String pici) {
                        this.pici = pici;
                    }

                    private String zhuanye;
                    private String difen;
                    private String gaofen;
                    private String renshu;
                    private String weici;
                    private String pici;

                }

                public class Record_2015{
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

                    public String getPici() {
                        return pici;
                    }

                    public void setPici(String pici) {
                        this.pici = pici;
                    }

                    private String zhuanye;
                    private String difen;
                    private String gaofen;
                    private String renshu;
                    private String weici;
                    private String pici;

                }


            }


        }



    }

}
