package com.qianyi.shine.ui.mine.bean;

/**
 * Created by Administrator on 2018/5/14.
 */

public class WXPayBean {
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

    public WXPayData getData() {
        return data;
    }

    public void setData(WXPayData data) {
        this.data = data;
    }

    private String code;
    private String info;
    private WXPayData data;

    public class WXPayData{
        public WXPayInfo getInfo() {
            return Info;
        }

        public void setInfo(WXPayInfo info) {
            Info = info;
        }

        private WXPayInfo Info;
        public class WXPayInfo{
            public String getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(String total_fee) {
                this.total_fee = total_fee;
            }

            public String getOut_trade_no() {
                return out_trade_no;
            }

            public void setOut_trade_no(String out_trade_no) {
                this.out_trade_no = out_trade_no;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getMch_id() {
                return mch_id;
            }

            public void setMch_id(String mch_id) {
                this.mch_id = mch_id;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getNotify_url() {
                return notify_url;
            }

            public void setNotify_url(String notify_url) {
                this.notify_url = notify_url;
            }

            private String total_fee;
            private String out_trade_no;
            private String appid;
            private String mch_id;
            private String key;
            private String notify_url;


        }

    }
}
