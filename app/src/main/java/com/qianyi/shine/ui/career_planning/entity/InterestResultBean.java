package com.qianyi.shine.ui.career_planning.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/25.
 */

public class InterestResultBean {
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

    public InterestResultData getData() {
        return data;
    }

    public void setData(InterestResultData data) {
        this.data = data;
    }

    private String code;
    private String info;
    private InterestResultData data;

    public class InterestResultData{
        public InterestResultInfo getInfo() {
            return Info;
        }

        public void setInfo(InterestResultInfo info) {
            Info = info;
        }

        private InterestResultInfo Info;

        public class InterestResultInfo{
            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getDecription() {
                return decription;
            }

            public void setDecription(String decription) {
                this.decription = decription;
            }

            public mColor getColor() {
                return color;
            }

            public void setColor(mColor color) {
                this.color = color;
            }



            public ZYFW getZyfw() {
                return zyfw;
            }

            public void setZyfw(ZYFW zyfw) {
                this.zyfw = zyfw;
            }

            public JTZY getJtzy() {
                return jtzy;
            }

            public void setJtzy(JTZY jtzy) {
                this.jtzy = jtzy;
            }

            private String type;
            private String decription;
            private mColor color;

            public Intereting getInteresting() {
                return interesting;
            }

            public void setInteresting(Intereting interesting) {
                this.interesting = interesting;
            }

            private Intereting interesting;
            private ZYFW zyfw;
            private JTZY jtzy;

            public class mColor{
                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                private String name;
                private String value;

            }

            public class Intereting {
                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<String> getValue() {
                    return value;
                }

                public void setValue(List<String> value) {
                    this.value = value;
                }

                private String name;
                private List<String> value;

            }

            public class ZYFW{
                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                private String name;
                private String value;

            }
            public class JTZY{
                private String name;
                private String value;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }


        }

    }
}
