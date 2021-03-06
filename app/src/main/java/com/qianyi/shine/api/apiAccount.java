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
        params.put("mobile",account_mobile);
        params.put("password",password);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    /**
     * 注册
     * */
    public static void Register(String url,String account_mobile, String password, String smscode,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("mobile",account_mobile);
        params.put("password",password);
        params.put("smscode",smscode);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    /***
     * 启程
     */
    public static void Launch(String url,String member_id, String type, String prov,String score,String rank,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("member_id",member_id);
        params.put("type",type);
        params.put("prov",prov);
        params.put("score",score);
        params.put("rank",rank);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    /***
     * 获取短信验证码
     */
    public static void getSmsCode(String url,String phoneStr,String type,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("mobile",phoneStr);
        params.put("type",type);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    /***
     * 更改密码
     */
    public static void changePwd(String url,String phoneStr,String codeStr,String pwdStr,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("mobile",phoneStr);
        params.put("smscode",codeStr);
        params.put("password",pwdStr);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }

    /***
     * 更新用户信息
     */
    public static void updateUserInfo(String url,String memberId,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("member_id",memberId);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    /***
     * 获取开放的地区
     */
    public static void getOpenArea(String url,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    /***
     * 微信登录
     */
    public static void wechatLogin(String url,String openid,String unionid,int sex,String headimgurl,String nickname,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("openid",openid);
        params.put("unionid",unionid);
        params.put("sex",sex+"");
        params.put("headimgurl",headimgurl);
        params.put("nickname",nickname);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    /***
     * 发送验证码
     */
    public static void getConfirmCode(String url,String mobile,String type,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("mobile",mobile);
        params.put("type",type);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }
    /***
     * qq登录
     */
    public static void qqLogin(String url,String openid,String unionid,String sex,String headimgurl,String nickname,RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("openid",openid);
        params.put("unionid",unionid);
        params.put("sex",sex);
        params.put("headimgurl",headimgurl);
        params.put("nickname",nickname);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }























































    /***
     * 微信登录
     */
    public static void weixin_login(String url,String openid,String unionid,String sex,String headimgurl,String nickname, RequestCallBack<String> callback){
        Map<String,String> params=new HashMap<>();
        params.put("openid",openid);
        params.put("unionid",unionid);
        params.put("sex",sex);
        params.put("headimgurl",headimgurl);
        params.put("nickname",nickname);
        OkHttpManager.getInstance().postRequest(url,params,callback);
    }







}
