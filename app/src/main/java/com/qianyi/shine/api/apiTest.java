package com.qianyi.shine.api;

import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.utils.OkHttpManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/20.
 */

public class apiTest {
    //获取mit测试题数据
    public static void getMIT_TestData(String url, RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }

    //获取mit测试结果
    public static void getMITResult(String url,String id,String keyString, RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("id",id);
        params.put("keystring",keyString);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }




}
