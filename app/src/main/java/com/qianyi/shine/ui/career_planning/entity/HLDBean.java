package com.qianyi.shine.ui.career_planning.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24.
 */

public class HLDBean {
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

    public HLDData getData() {
        return data;
    }

    public void setData(HLDData data) {
        this.data = data;
    }

    private String info;
    private String code;
    private HLDData data;

    public class HLDData{
        public HLDInfo getInfo() {
            return Info;
        }

        public void setInfo(HLDInfo info) {
            Info = info;
        }

        private HLDInfo Info;
        public class HLDInfo{
            public String getListtotal() {
                return listtotal;
            }

            public void setListtotal(String listtotal) {
                this.listtotal = listtotal;
            }

            private String listtotal;
            public List<HLDQuestionLIST> getQuestion_list() {
                return question_list;
            }

            public void setQuestion_list(List<HLDQuestionLIST> question_list) {
                this.question_list = question_list;
            }

            private List<HLDQuestionLIST> question_list;

            public class HLDQuestionLIST{
                public String getOrder() {
                    return order;
                }

                public void setOrder(String order) {
                    this.order = order;
                }

                public String getQuestion() {
                    return question;
                }

                public void setQuestion(String question) {
                    this.question = question;
                }

                private String order;
                private String question;
            }
        }

    }
}
