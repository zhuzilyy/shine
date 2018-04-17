package com.qianyi.shine.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.mine.activity.FocusActivity;
import com.qianyi.shine.ui.mine.activity.JoinUsActivity;
import com.qianyi.shine.ui.mine.activity.MessageActivity;
import com.qianyi.shine.ui.mine.activity.SettingActivity;
import com.qianyi.shine.ui.mine.activity.VipActivity;
import com.qianyi.shine.utils.Utils;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by NEUNB on 2018/3/19.
 */

public class MineFragment extends BaseFragment {
    private View view_mine;
    private PopupWindow pw_share;
    private View view_share;
    @BindView(R.id.ll_mine)
    LinearLayout ll_mine;
    @BindView(R.id.mine_head)
    RoundedImageView mine_head;
    private LinearLayout ll_wechat;
    private Tencent mTencent;
    private MyReceiver myReceiver;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_mine=inflater.inflate(R.layout.fragment_mine,null);
        return view_mine;
    }
    @Override
    protected void initViews() {
        view_share=LayoutInflater.from(getActivity()).inflate(R.layout.pw_share,null);
        ll_wechat=view_share.findViewById(R.id.ll_wechat);
        //初始化数据
        setValues();
        //注册广播
        myReceiver=new MyReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.action.updateInfo");
        getActivity().registerReceiver(myReceiver,intentFilter);
    }
    private void setValues() {
        LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(getActivity());
        String avatar = loginInfo.getAvatar();
        Glide.with(getActivity()).load(avatar).into(mine_head);
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        //微信分享
        ll_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareFriends();
                shareToQQ();
                pw_share.dismiss();
            }
        });
    }
    //分享到QQ
    private void shareToQQ() {
        // 新建Tencent实例用于调用分享方法
         mTencent = Tencent.createInstance("your APP ID",getActivity().getApplicationContext());
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);//分享的类型
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "然了个然CSDN博客");//分享标题
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,"不管是怎样的过程,最终目的还是那个理想的结果。");//要分享的内容摘要
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,"http://blog.csdn.net/sandyran");//内容地址
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"http://avatar.csdn.net/B/3/F/1_sandyran.jpg");//分享的图片URL
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "测试");//应用名称
        mTencent.shareToQQ(getActivity(), params, new ShareUiListener());

    }

    //分享到微信好友
    private void shareFriends() {
        IWXAPI mWxApi;
        mWxApi = WXAPIFactory.createWXAPI(getActivity(), apiConstant.APP_ID, false);
        // 将该app注册到微信
        mWxApi.registerApp(apiConstant.APP_ID);
        WXTextObject textObj = new WXTextObject();
        textObj.text = "dsfhsj";
        // 用WXTextObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        // 发送文本类型的消息时，title字段不起作用
        msg.title = "Will be ignored";
        msg.description = "hfahfhashfd";
        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text"); // transaction字段用于唯一标识一个请求
        req.message = msg;
        /**
         * 判断是否是朋友圈
         */
       // req.scene = isTimelineCb.isChecked() ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        // 调用api接口发送数据到微信
        mWxApi.sendReq(req);
    }

    public static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }


    @OnClick({R.id.rl_setting,R.id.rl_helpCenter,R.id.rl_vip,R.id.rl_focus,R.id.iv_message,R.id.iv_share})
    public void click(View view){
        switch (view.getId()){
            case R.id.rl_focus:
                startActivity(new Intent(getActivity(), FocusActivity.class));
                break;
            case R.id.rl_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.rl_helpCenter:
                //startActivity(new Intent(getActivity(), HelpCenterActivity.class));
                startActivity(new Intent(getActivity(), JoinUsActivity.class));
                break;
            case R.id.rl_vip:
                startActivity(new Intent(getActivity(), VipActivity.class));
                break;
            case R.id.iv_message:
                startActivity(new Intent(getActivity(), MessageActivity.class));
                break;
            case R.id.iv_share:
                shwoSharePw();
                break;
        }
    }
   //弹出分享pw
    private void shwoSharePw() {
        pw_share = new PopupWindow(getActivity());
        pw_share.setContentView(view_share);
        pw_share.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pw_share.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        pw_share.setTouchable(true);
        pw_share.setFocusable(true);
        pw_share.setBackgroundDrawable(new BitmapDrawable());
        pw_share.setAnimationStyle(R.style.AnimBottom);
        pw_share
                .showAtLocation(ll_mine, Gravity.BOTTOM, 0, 0);
        // 设置pw弹出时候的背景颜色的变化
        backgroundAlpha(0.5f);
        pw_share.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }
    /**
     * 设置添加屏幕的背景透明度
     *
     * @param
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);
    }
    class ShareUiListener implements IUiListener{

        @Override
        public void onComplete(Object o) {

        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != mTencent) {
            mTencent.onActivityResult(requestCode, resultCode, data);
        }
    }
    class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.action.updateInfo")){
                setValues();
            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(myReceiver);
    }
}
