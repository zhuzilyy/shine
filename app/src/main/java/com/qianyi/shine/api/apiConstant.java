package com.qianyi.shine.api;

/**
 * Created by Administrator on 2018/4/10.
 */

public class apiConstant {
    public static final String BASE_URL="http://39.104.109.53/api/";
    //注册
    public static final String REGISTER=BASE_URL+"register";
    //登录
    public static final String LOGIN=BASE_URL+"login";
    //启程
    public static final String LAUNCH=BASE_URL+"insert_memberscoreinfo";
    //首页
    public static final String HOME=BASE_URL+"default_index";
    //修改个人信息
    public static final String UPDATE_INFO=BASE_URL+"update_baseinfo";
    //发送短信验证码
    public static final String GETSMSCODE=BASE_URL+"sendsmscode";
    //重置密码
    public static final String CHANGEPWD=BASE_URL+"reset_password";
    //高考头条更多
    public static final String ARTICLEMORE=BASE_URL+"article_list_more";
    //获取性格测试的数据
    public static final String GETMBTDATA=BASE_URL+"getMBTItest";
    //获取性格测试结果
    public static final String GETMBTRESULT=BASE_URL+"getMBTIanalyze";
    //查找大学列表
    public static final String FIND_COLLEGE=BASE_URL+"university_list";
    //获取兴趣测试数据
    public static final String GETHLDINTERESTDDATA=BASE_URL+"getHLDtest";
    //获取兴趣测试结果
    public static final String GETHLDRESULT=BASE_URL+"getHLDanalyze";

















    //微信参数
    public static final String APP_ID="wxf51a00546d18489f";
    public static final String APP_SECRET="ed6e15ab8d28ca6dd3b024f1ffedbec9";

}
