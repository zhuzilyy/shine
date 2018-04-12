package com.qianyi.shine.ui.account.bean;

/**
 * Created by Administrator on 2018/4/12.
 */

public class RegisterBean {
    private RegisterData data;
    private String info;
    private String code;

    public RegisterData getData() {
        return data;
    }

    public void setData(RegisterData data) {
        this.data = data;
    }

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

    private class RegisterData{
        private RegisterInfo Info;

        public RegisterInfo getInfo() {
            return Info;
        }

        public void setInfo(RegisterInfo info) {
            Info = info;
        }

        private class RegisterInfo {
            private String id;
            private String mobile;
            private String nickname;
            private String password;
            private String salt;
            private String avatar;
            private String sex;
            private String prov;
            private String city;
            private String country;
            private String school;
            private String checkinfo;
            private String isdelete;
            private String registerip;
            private String loginip;
            private String lastlogintime;
            private String create_time;
            private String update_time;
            private String weixinid;
            private String qqid;
            private String weiboid;
            private String is_vip;
            private String vip_endtime;

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

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
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

            public String getLastlogintime() {
                return lastlogintime;
            }

            public void setLastlogintime(String lastlogintime) {
                this.lastlogintime = lastlogintime;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
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

            private class memberScoreInfo{

            }




        }
    }
}
