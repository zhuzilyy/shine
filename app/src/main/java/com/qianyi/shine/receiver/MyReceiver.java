package com.qianyi.shine.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2018/5/22.
 */

public class MyReceiver extends BroadcastReceiver {
    private String keyWord;
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("com.action.search")){
            keyWord=intent.getStringExtra("keyWord");
        }
    }
    public String getKeyWord(){
        return keyWord;
    }
}
