package com.qianyi.shine.ui.home.bean;

/**
 * Created by Administrator on 2018/5/23.
 */

public class ProfessionPriorBean {

    public class ProfessionPriorData{

        public class ProfessionPriorInfo{

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
                private RecruitStudents recruit_students;

                public class RecruitStudents{
                    private String major_name;
                    private String cost;
                    private String plannum;
                    private String learnyear;
                   // private m2017  2017;

                    public class m2017{
                        private String id;
                        private String school_name;
                        private String difen;
                        private String gaofen;
                        private String renshu;
                        private String weici;
                        private String rate;
                        private String risk;

                    }



                }



            }
        }

    }

}
