package com.qianyi.shine.api;

import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.utils.OkHttpManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/14.
 */

public class apiPay {
    /**
     * 获取支付参数
     * */
    public static void getPayConf(String url,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    /**
     * 微信支付
     * */
    public static void wxpay(String url,String member_id,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("member_id",member_id);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    /**
     * 支付宝支付
     * */
    public static void alipay(String url,String member_id,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("member_id",member_id);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
}
