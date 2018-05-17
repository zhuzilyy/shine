package com.qianyi.shine.ui.mine.bean;

/**
 * Created by Administrator on 2018/5/16.
 */

public class VipCongBean {
    public VipCongData getData() {
        return data;
    }

    public void setData(VipCongData data) {
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

    private VipCongData data;
    private String code;
    private String info;

    public class VipCongData{
        public VipCongInfo getInfo() {
            return Info;
        }

        public void setInfo(VipCongInfo info) {
            Info = info;
        }

        private VipCongInfo Info;

        public class VipCongInfo{
            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getLimit() {
                return limit;
            }

            public void setLimit(String limit) {
                this.limit = limit;
            }

            private String money;
            private String limit;
        }

    }
}
