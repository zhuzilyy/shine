package com.qianyi.shine.ui.account.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/12.
 */

public class LoginBean {
    public LoginData data;
    public String code;
    public String info;

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }

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

    public class  LoginData{
        public LoginInfo Info;

        public LoginInfo getInfo() {
            return Info;
        }

        public void setInfo(LoginInfo info) {
            Info = info;
        }

        public class LoginInfo implements Serializable{
             public String id;
             public String mobile;
             public String nickname;
             public String password;
             public String salt;
             public String avatar;
             public String sex;
             public String prov;
             public String city;
             public String country;
             public String school;
             public String checkinfo;
             public String isdelete;
             public String registerip;
             public String loginip;
             public String weixinid;
             public String qqid;
             public String weiboid;
             public String is_vip;
             public String vip_endtime;
             public MemberScoreInfo member_scoreinfo;

            public MemberScoreInfo getMember_scoreinfo() {
                return member_scoreinfo;
            }

            public void setMember_scoreinfo(MemberScoreInfo member_scoreinfo) {
                this.member_scoreinfo = member_scoreinfo;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getSalt() {
                return salt;
            }

            public void setSalt(String salt) {
                this.salt = salt;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getProv() {
                return prov;
            }

            public void setProv(String prov) {
                this.prov = prov;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getContry() {
                return country;
            }

            public void setContry(String contry) {
                this.country = contry;
            }

            public String getSchool() {
                return school;
            }

            public void setSchool(String school) {
                this.school = school;
            }

            public String getCheckinfo() {
                return checkinfo;
            }

            public void setCheckinfo(String checkinfo) {
                this.checkinfo = checkinfo;
            }

            public String getIsdelete() {
                return isdelete;
            }

            public void setIsdelete(String isdelete) {
                this.isdelete = isdelete;
            }

            public String getRegisterip() {
                return registerip;
            }

            public void setRegisterip(String registerip) {
                this.registerip = registerip;
            }

            public String getLoginip() {
                return loginip;
            }

            public void setLoginip(String loginip) {
                this.loginip = loginip;
            }

            public String getWeixinid() {
                return weixinid;
            }

            public void setWeixinid(String weixinid) {
                this.weixinid = weixinid;
            }

            public String getQqid() {
                return qqid;
            }

            public void setQqid(String qqid) {
                this.qqid = qqid;
            }

            public String getWeiboid() {
                return weiboid;
            }

            public void setWeiboid(String weiboid) {
                this.weiboid = weiboid;
            }

            public String getIs_vip() {
                return is_vip;
            }

            public void setIs_vip(String is_vip) {
                this.is_vip = is_vip;
            }

            public String getVip_endtime() {
                return vip_endtime;
            }

            public void setVip_endtime(String vip_endtime) {
                this.vip_endtime = vip_endtime;
            }

            public class MemberScoreInfo implements Serializable {
                public String id;
                public String member_id;
                public String prov;
                public String type;
                public String score;
                public String rank;
                public String create_time;

                public String getLevel() {
                    return level;
                }

                public void setLevel(String level) {
                    this.level = level;
                }

                public String level;
                public String intention_major;
                public String intention_job;
                public String intention_area;
                public String limit;






                public String getIntention_major() {
                    return intention_major;
                }

                public void setIntention_major(String intention_major) {
                    this.intention_major = intention_major;
                }

                public String getIntention_job() {
                    return intention_job;
                }

                public void setIntention_job(String intention_job) {
                    this.intention_job = intention_job;
                }

                public String getIntention_area() {
                    return intention_area;
                }

                public void setIntention_area(String intention_area) {
                    this.intention_area = intention_area;
                }

                public String getLimit() {
                    return limit;
                }

                public void setLimit(String limit) {
                    this.limit = limit;
                }




                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getMember_id() {
                    return member_id;
                }

                public void setMember_id(String member_id) {
                    this.member_id = member_id;
                }

                public String getProv() {
                    return prov;
                }

                public void setProv(String prov) {
                    this.prov = prov;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getScore() {
                    return score;
                }

                public void setScore(String score) {
                    this.score = score;
                }

                public String getRank() {
                    return rank;
                }

                public void setRank(String rank) {
                    this.rank = rank;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }
            }
             
    


         }
    }

}
