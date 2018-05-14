package com.qianyi.shine.ui.mine.bean;

/**
 * Created by Administrator on 2018/5/14.
 */

public class AliBean {
    public AliData getData() {
        return data;
    }

    public void setData(AliData data) {
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

    private AliData data;
    private String info;
    private String code;
    public class AliData{
        public AliInfo getInfo() {
            return Info;
        }

        public void setInfo(AliInfo info) {
            Info = info;
        }

        private AliInfo Info;
        public class AliInfo{
            public String getOrderstr() {
                return orderstr;
            }

            public void setOrderstr(String orderstr) {
                this.orderstr = orderstr;
            }

            private String orderstr;
        }
    }
}
