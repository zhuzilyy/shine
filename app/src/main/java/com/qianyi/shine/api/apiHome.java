package com.qianyi.shine.api;

import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.utils.OkHttpManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/29.
 */

public class apiHome {
    /**
     * 首页刷新
     * @param url
     * @param page
     * @param callback
     */
    public static void refresh(String url,int page, RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("page",page+"");
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }

    /**
     * 首页加载更多
     * @param url
     * @param page
     * @param callback
     */
    public static void loadMore(String url,int page, RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("page",page+"");
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
}
