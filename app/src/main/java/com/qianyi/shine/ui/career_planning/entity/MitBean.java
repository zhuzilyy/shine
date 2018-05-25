package com.qianyi.shine.ui.career_planning.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/20.
 */

public class MitBean {
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

    public MitData getData() {
        return data;
    }

    public void setData(MitData data) {
        this.data = data;
    }

    public String code;
    public String info;
    private MitData data;

    public class MitData {
        public MitInfo getInfo() {
            return Info;
        }

        public void setInfo(MitInfo info) {
            Info = info;
        }

        private MitInfo Info;

        public class MitInfo {
            public List<MitList> getQuestion_list() {
                return question_list;
            }

            public void setQuestion_list(List<MitList> question_list) {
                this.question_list = question_list;
            }

            public String getListtotal() {
                return listtotal;
            }

            public void setListtotal(String listtotal) {
                this.listtotal = listtotal;
            }

            private List<MitList> question_list;
            private String listtotal;

            public class MitList {
                public String getQuetion() {
                    return question;
                }

                public void setQuetion(String quetion) {
                    this.question = quetion;
                }

                public Keys getKey() {
                    return key;
                }

                public void setKey(Keys key) {
                    this.key = key;
                }

                private String question;
                private Keys key;

                public String getOrder() {
                    return order;
                }

                public void setOrder(String order) {
                    this.order = order;
                }

                private String order;

                public class Keys {
                    public String getA() {
                        return A;
                    }

                    public void setA(String a) {
                        A = a;
                    }

                    public String getB() {
                        return B;
                    }

                    public void setB(String b) {
                        B = b;
                    }

                    private String A;
                    private String B;
                }

            }

        }

    }

}
