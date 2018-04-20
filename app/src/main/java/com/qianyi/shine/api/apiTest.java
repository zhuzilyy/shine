package com.qianyi.shine.api;

import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.utils.OkHttpManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/20.
 */

public class apiTest {
    public static void getTestData(String url,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
}
