package com.qianyi.shine.api;

import android.util.Log;

import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.utils.OkHttpManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by NEUNB on 2018/3/19.
 */

public class apiAccount {
    /**
     * 登录请求方法
     * @param account_mobile
     * @param password
     * @param callback
     * String account, String nickname,
     */
    public static void Login(String url,String account_mobile, String password, RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("account_mobile",account_mobile);
        params.put("password",password);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    /*注册*/
    public static void Register(String url,String account_mobile, String password, String smscode,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("mobile",account_mobile);
        params.put("password",password);
        params.put("smscode",smscode);
        OkHttpManager.getInstance().postRequest(url,params,callback);
        Log.i("tag","3333333333333333");
    }
}
