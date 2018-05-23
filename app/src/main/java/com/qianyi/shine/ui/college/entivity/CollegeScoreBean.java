package com.qianyi.shine.ui.college.entivity;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class CollegeScoreBean {
    private String info;
    private String code;
    private CollegeScoreData data;

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

    public CollegeScoreData getData() {
        return data;
    }

    public void setData(CollegeScoreData data) {
        this.data = data;
    }

    public class CollegeScoreData{
        private CollegeScoreInfo Info;

        public CollegeScoreInfo getInfo() {
            return Info;
        }

        public void setInfo(CollegeScoreInfo info) {
            Info = info;
        }

        public class CollegeScoreInfo{
            private AllRecord all_record;
            private List<MajorRecord> major_record;

            public AllRecord getAll_record() {
                return all_record;
            }

            public void setAll_record(AllRecord all_record) {
                this.all_record = all_record;
            }

            public List<MajorRecord> getMajor_record() {
                return major_record;
            }

            public void setMajor_record(List<MajorRecord> major_record) {
                this.major_record = major_record;
            }

            public class AllRecord{
                private Record2017 record_2017;
                private Record2016 record_2016;
                private Record2015 record_2015;

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
                    private String difen;
                    private String gaofen;
                    private String renshu;
                    private String h;
                    private String weici;

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

                    public String getWeci() {
                        return weici;
                    }

                    public void setWeci(String weci) {
                        this.weici = weci;
                    }
                }
                public class Record2016{
                    private String difen;
                    private String gaofen;
                    private String renshu;
                    private String h;
                    private String weici;

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

                    public String getWeci() {
                        return weici;
                    }

                    public void setWeci(String weci) {
                        this.weici = weci;
                    }
                }
                public class Record2015{
                    private String difen;
                    private String gaofen;
                    private String renshu;
                    private String h;
                    private String weici;

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

                    public String getWeci() {
                        return weici;
                    }

                    public void setWeci(String weci) {
                        this.weici = weci;
                    }
                }


            }

            public class MajorRecord{

                private String major_name;
                private Record_2017 record_2017;
                private Record_2016 record_2016;
                private Record_2015 record_2015;

                public String getMajor_name() {
                    return major_name;
                }

                public void setMajor_name(String major_name) {
                    this.major_name = major_name;
                }

                public Record_2017 getRecord_2017() {
                    return record_2017;
                }

                public void setRecord_2017(Record_2017 record_2017) {
                    this.record_2017 = record_2017;
                }

                public Record_2016 getRecord_2016() {
                    return record_2016;
                }

                public void setRecord_2016(Record_2016 record_2016) {
                    this.record_2016 = record_2016;
                }

                public Record_2015 getRecord_2015() {
                    return record_2015;
                }

                public void setRecord_2015(Record_2015 record_2015) {
                    this.record_2015 = record_2015;
                }

                public class Record_2017{
                    private String major_name;
                    private String reshu;
                    private String difen;
                    private String gaofen;
                    private String weici;

                    public String getMajor_name() {
                        return major_name;
                    }

                    public void setMajor_name(String major_name) {
                        this.major_name = major_name;
                    }

                    public String getReshu() {
                        return reshu;
                    }

                    public void setReshu(String reshu) {
                        this.reshu = reshu;
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

                    public String getWeici() {
                        return weici;
                    }

                    public void setWeici(String weici) {
                        this.weici = weici;
                    }
                }
                public class Record_2015{
                    private String major_name;
                    private String reshu;
                    private String difen;
                    private String gaofen;
                    private String weici;

                    public String getMajor_name() {
                        return major_name;
                    }

                    public void setMajor_name(String major_name) {
                        this.major_name = major_name;
                    }

                    public String getReshu() {
                        return reshu;
                    }

                    public void setReshu(String reshu) {
                        this.reshu = reshu;
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

                    public String getWeici() {
                        return weici;
                    }

                    public void setWeici(String weici) {
                        this.weici = weici;
                    }
                }
                public class Record_2016{
                    private String major_name;
                    private String reshu;
                    private String difen;
                    private String gaofen;
                    private String weici;

                    public String getMajor_name() {
                        return major_name;
                    }

                    public void setMajor_name(String major_name) {
                        this.major_name = major_name;
                    }

                    public String getReshu() {
                        return reshu;
                    }

                    public void setReshu(String reshu) {
                        this.reshu = reshu;
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
