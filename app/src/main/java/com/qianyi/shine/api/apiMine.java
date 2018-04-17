package com.qianyi.shine.api;

import android.text.TextUtils;

import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.utils.OkHttpManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/16.
 */

public class apiMine {
    /**
     *修改个人资料
     * String account, String nickname,
     */
    public static void UpdateInfo(String url,String id, String avatar_data,String sex,String nickname,String prov,String city,String country,String school, RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("id",id);
        if (!TextUtils.isEmpty(avatar_data)){
            params.put("avatar_data",avatar_data);
        }
        params.put("sex",sex);
        params.put("nickname",nickname);
        params.put("prov",prov);
        params.put("city",city);
        params.put("country",country);
        params.put("school",school);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
}
