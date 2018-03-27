package com.qianyi.shine.utils;

import android.content.Context;
import android.widget.Toast;

import com.qianyi.shine.application.MyApplication;


public class ToastUtils {
	private static Toast toast;
	public static void show(Context context,String text){
		Toast.makeText(context,text, Toast.LENGTH_LONG).show();
	}
	public static void showToast(String info) {
		if (toast == null) {
			toast = Toast.makeText(MyApplication.getApplication(), info, Toast.LENGTH_SHORT);
		}
		toast.setText(info);
		toast.show();
	}
}
