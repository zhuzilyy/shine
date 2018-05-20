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
    public static void refresh(String url,int page,String member_id, RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("page",page+"");
        params.put("member_id",member_id);
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
    //获取首页的分数数据
    public static void getScoreLevel(String url,String member_id,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("member_id",member_id);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    //院校优先填报
    public static void schoolPrior(String url,String member_id,int page,String order,String area,String school_type,String rate_type,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("member_id",member_id);
        params.put("page",page+"");
        params.put("order",order);
        params.put("area",area);
        params.put("school_type",school_type);
        params.put("rate_type",rate_type);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    //推荐大学更多
    public static void recommendMoreCollege(String url,String member_id,int page,String order,String area,String school_type,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("member_id",member_id);
        params.put("page",page+"");
        params.put("order",order);
        params.put("area",area);
        params.put("school_type",school_type);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    //意愿设置
    public static void willingSet(String url,String member_id,String intention_area,String intention_major,String intention_job,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("member_id",member_id);
        params.put("intention_area",intention_area);
        params.put("intention_major",intention_major);
        params.put("intention_job",intention_job);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    //智能填报
    public static void intellgentFill(String url,int page,String member_id,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("member_id",member_id);
        params.put("page",page+"");
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    //专业设置
    public static void majorSetting(String url,String id,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("id",id);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    //就业前景
    public static void majorProspects(String url,String wmzyId,String id,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("wmzy_school_id",wmzyId);
        params.put("id",id);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    //就业前景
    public static void occupationDetail(String url,String name,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("name",name);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    //关注大学
    public static void focusCollege(String url,String member_id,String school_id,String school_name,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("member_id",member_id);
        params.put("school_id",school_id);
        params.put("school_name",school_name);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    //关注职业
    public static void focusOccupation(String url,String member_id,String job_id,String job_name,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("member_id",member_id);
        params.put("job_id",job_id);
        params.put("job_name",job_name);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    //获取我关注的大学
    public static void focusCollegeList(String url,String member_id,int page,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("member_id",member_id);
        params.put("page",page+"");
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    //获取我关注的职业
    public static void focusJobList(String url,String member_id,int page,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("member_id",member_id);
        params.put("page",page+"");
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    //获取我关注的专业
    public static void focusMajorList(String url,String member_id,int page,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("member_id",member_id);
        params.put("page",page+"");
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    //获取我关注的专业
    public static void messageList(String url,String member_id,int page,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("member_id",member_id);
        params.put("page",page+"");
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    //加入我们
    public static void joinUs(String url,String member_id,String  name,String  phone,String  intro,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("member_id",member_id);
        params.put("name",name);
        params.put("phone",phone);
        params.put("intro",intro);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
}
