package com.qianyi.shine.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2018/4/11.
 */

public class WXShareManager {
    private final String APP_ID = "wx8zq97f092818osua";
    private IWXAPI api;
    //private ShareResultListener listener;
    private final String TRANSACTION_TEXT = "text";
    private final String TRANSACTION_IMAGE = "image";
    private final String TRANSACTION_WEBPAGE = "webpage";
    private final String TRANSACTION_MUSIC = "music";
    private final String TRANSACTION_VIDEO = "video";
    private final String TRANSACTION_FILE = "file";
    private final int THUMBNAIL_SIZE = 150;
    //分享类型
    public enum ShareType {
        FRIENDS, FRIENDSCIRCLE, FAVOURITE
    }
    private WXShareManager() {
        //no instance
    }
    public static class InstanceHolder {
        private static WXShareManager INSTANCE = new WXShareManager();
    }
    public static WXShareManager get() {
        return InstanceHolder.INSTANCE;
    }
    /** * 初始化微信API 推荐在自定义的Application的onCreate方法中调用 * @param appContext */
    public void init(Context appContext){
        api = WXAPIFactory.createWXAPI(appContext, APP_ID, true);
        api.registerApp(APP_ID);
    }
   /* public boolean shareText(@NonNull String shareText, @NonNull String title, @NonNull String description, @NonNull ShareType type, @Nullable ShareResultListener listener) {
        this.listener = listener;
        WXTextObject obj = new WXTextObject(shareText);
        WXMediaMessage msg = buildMediaMesage(obj, title, description);
        BaseReq req = buildSendReq(msg, buildTransaction(TRANSACTION_TEXT), getWxShareType(type));
        return api.sendReq(req);
    }
    public boolean shareImage(@NonNull Bitmap bitmap, @NonNull String title, @NonNull String description, @NonNull ShareType type, @Nullable ShareResultListener listener) {
        this.listener = listener; WXMediaMessage.IMediaObject obj = new WXImageObject(bitmap);
        WXMediaMessage msg = buildMediaMesage(obj, title, description); msg.setThumbImage(bitmap);
        BaseReq req = buildSendReq(msg, buildTransaction(TRANSACTION_IMAGE), getWxShareType(type));
        return api.sendReq(req);
    }

    public boolean shareImage(@NonNull final String pathOrUrl, @NonNull Bitmap thumbnail, @NonNull String title, @NonNull String description, @NonNull ShareType type, ShareResultListener listener) {
        this.listener = listener;*/
        //分享url图片 if(pathOrUrl.contains("://"))
   { // obj.imageUrl = pathOrUrl; //不能编译??? Observable.<byte[]>create(subscriber -> { subscriber.onNext(Util.getHtmlByteArray(pathOrUrl)); }).subscribeOn(Schedulers.io()) .observeOn(AndroidSchedulers.mainThread()) .subscribe(data -> { shareImage(data, title, description, type, listener); }); return true; }


   }}









