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

    //获取大学列表
    public static void getCollegeList(String url,String order,String area,String level,String is_type,String school_type,String keyword, int page, RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("page",page+"");
        params.put("order",order);
        params.put("area",area);
        params.put("level",level);
        params.put("is_type",is_type);
        params.put("school_type",school_type);
        params.put("keyword",keyword);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    //获取专业列表
    public static void getMajorList(String url,String diploma_id, RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("diploma_id",diploma_id);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    //获取就业列表
    public static void getEmployerList(String url,String type, RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("type",type);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    //获取职业列表
    public static void getJobList(String url,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }

    //获取大学详情
    public static void getCollegeData(String url,String id,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("id",id);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }

}
