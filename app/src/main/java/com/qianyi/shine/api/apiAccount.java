package com.qianyi.shine.api;


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
}
