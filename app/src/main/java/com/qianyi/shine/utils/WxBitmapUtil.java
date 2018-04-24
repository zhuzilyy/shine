package com.qianyi.shine.utils;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Created by Administrator on 2018/4/24.
 */

public class WxBitmapUtil {
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
