package com.qianyi.shine.application;
import android.app.Application;
import android.content.Context;


import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.xutils.x;

/**
 * Created by NEUNB on 2018/3/19.
 */

public class MyApplication extends Application {
    private static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        myApplication=this;
        //注册微信
        registToWX();
        initImageLoader(getApplicationContext());
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
      /*  mWxApi = WXAPIFactory.createWXAPI(this, apiConstant.APP_ID, false);
        // 将该app注册到微信
        mWxApi.registerApp(apiConstant.APP_ID);*/
    }
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        ImageLoader.getInstance().init(config.build());
    }
}
