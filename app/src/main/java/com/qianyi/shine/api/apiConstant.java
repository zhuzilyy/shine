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
    //获取专业列表
    public static final String MAJOR_LIST=BASE_URL+"major_list";
    //看就业
    public static final String EMPLOYER_BENKE_LIST=BASE_URL+"article_list_jiuye";
    //搜职业
    public static final String JOB_LIST=BASE_URL+"job_list";
    //获取大学详情
    public static final String COLLEGE_DETAILS = BASE_URL+"university_info";
    //微信支付
    public static final String WXPAY=BASE_URL+"wxpay";
    //支付宝支付
    public static final String ALIPAY=BASE_URL+"alipay";
    //首页获取各个参数的数据
    public static final String SCORE_LEVEL=BASE_URL+"get_scoreline_info";
    //院校优先填报
    public static final String SCHOOL_PRiOR=BASE_URL+"prior_school";
    //推荐大学更多
    public static final String RECOMMEND_MORE_COLLEGE=BASE_URL+"recommend_university_more";
    //意愿设置
    public static final String WILLING_SETTING=BASE_URL+"insert_intentioninfo";
    //智能填报
    public static final String INTELLGENT_FILL=BASE_URL+"prior_noopsyche";
    //专业设置
    public static final String MAJOR_SETTING=BASE_URL+"university_majorinfo";
    //就业前景
    public static final String MAJOR_PROSPECTS=BASE_URL+"university_prospect";






    //微信参数
    public static  String APP_ID="wxf51a00546d18489f";
    public static  String APP_SECRET="ed6e15ab8d28ca6dd3b024f1ffedbec9";
    //apikey
    public static  String API_KEY="";
    //商户号
    public static  String MCH_ID = "1494262522";
    //订单号
    public static String OUT_TRADE_NO;
    //价格
    public static String TOTAL_FEE;
    //通知Url
    public static String NOTIFY_URL;
    //获取支付参数
    public static String VIP_CONFIG=BASE_URL+"vip_config";
    //获取个人资料
    public static String GETMEMBERINFO=  BASE_URL+"get_memberinfo";
    //专业详情
    public static String ZHUANYEDETAILS=  BASE_URL+"major_info";

    //关注专业
    public static String ATTENTIONMAJOR=  BASE_URL+"insert_collection_major";
    //我的关注测评报告
    public static String Evaluationeport= BASE_URL+"get_test_record";

}
