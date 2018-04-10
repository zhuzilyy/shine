package com.qianyi.shine.application;
import android.app.Application;

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
    }
    /**
     * 全程作用域
     * @return
     */
    public static MyApplication getApplication(){
        return myApplication;
    }
}
