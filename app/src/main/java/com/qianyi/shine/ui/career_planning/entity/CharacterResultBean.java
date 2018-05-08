package com.qianyi.shine.ui.career_planning.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24.
 */

public class CharacterResultBean {
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

    public CharacterResultData getData() {
        return data;
    }

    public void setData(CharacterResultData data) {
        this.data = data;
    }

    private String info;
    private String code;
    private CharacterResultData data;

    public class CharacterResultData{
        public CharacterResultInfo getInfo() {
            return Info;
        }

        public void setInfo(CharacterResultInfo info) {
            Info = info;
        }

        private CharacterResultInfo Info;
        public class CharacterResultInfo{
            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }



            public ZZGX getZzgx() {
                return zzgx;
            }

            public void setZzgx(ZZGX zzgx) {
                this.zzgx = zzgx;
            }






            public GZHJ getGzhj() {
                return gzhj;
            }

            public void setGzhj(GZHJ gzhj) {
                this.gzhj = gzhj;
            }

            public QZQD getQzqd() {
                return qzqd;
            }

            public void setQzqd(QZQD qzqd) {
                this.qzqd = qzqd;
            }

            public FZJY getFzjy() {
                return fzjy;
            }

            public void setFzjy(FZJY fzjy) {
                this.fzjy = fzjy;
            }

            private String type;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            private String description;
            private ZZGX zzgx;

            public LDMS getLdms() {
                return ldms;
            }

            public void setLdms(LDMS ldms) {
                this.ldms = ldms;
            }

            private LDMS ldms;

            public XXM getXxms() {
                return xxms;
            }

            public void setXxms(XXM xxms) {
                this.xxms = xxms;
            }

            private XXM xxms;

            public JJM getJjms() {
                return jjms;
            }

            public void setJjms(JJM jjms) {
                this.jjms = jjms;
            }

            private JJM jjms;
            private GZHJ gzhj;
            private QZQD qzqd;
            private FZJY fzjy;



            //对组织的贡献
            public class ZZGX{
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
            //领导模式
            public class LDMS{
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
            //学习模式
            public class XXM{
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

                public Extend getExtend() {
                    return extend;
                }

                public void setExtend(Extend extend) {
                    this.extend = extend;
                }

                private String name;
                private List<String> value;
                private Extend extend;

                public class Extend{
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
            }

            //解决问题模式
            public class JJM{
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
            //工作环境倾向性
            public class GZHJ{
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
            //潜在的缺点
            public class QZQD{
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
            //发展建议
            public class FZJY{
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





        }

    }

}
