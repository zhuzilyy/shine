package com.qianyi.shine.application;
import android.app.Application;

import com.qianyi.shine.api.apiConstant;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.xutils.x;

/**
 * Created by NEUNB on 2018/3/19.
 */

public class MyApplication extends Application {
    private static MyApplication myApplication;
    public static IWXAPI mWxApi;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        myApplication=this;
        //注册微信
        registToWX();
    }
    /**
     * 全程作用域
     * @return
     */
    public static MyApplication getApplication(){
        return myApplication;
    }
    /***
     * 注册微信
     */
    private void registToWX() {
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        mWxApi = WXAPIFactory.createWXAPI(this, apiConstant.APP_ID, true);
       // mWxApi = WXAPIFactory.createWXAPI(getApplicationContext(),null);
        // 将该app注册到微信
        mWxApi.registerApp(apiConstant.APP_ID);
    }
}
