package com.qianyi.shine.ui.account.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.home.activity.PriorityProfessionalDetailsActivity;
import com.qianyi.shine.ui.mine.activity.VipActivity;
import com.qianyi.shine.utils.Utils;
import com.qianyi.shine.utils.WebviewUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by NEUNB on 2018/3/31.
 */

public class OccupationWebviewActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.wv_webview)
    WebView wv_webview;
    @BindView(R.id.pb_webview)
    ProgressBar pb_webview;
    @BindView(R.id.ll_openVip)
    LinearLayout ll_openVip;
    private WebSettings webSettings;
    private String isVip;
    private MyReceiver myReceiver;
    @Override
    protected void initViews() {
        LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(this);
        isVip=loginInfo.getIs_vip();
        if (isVip.equals("0")){
            ll_openVip.setVisibility(View.VISIBLE);
        }else if (isVip.equals("1")){
            ll_openVip.setVisibility(View.GONE);
        }
        BaseActivity.addActivity(this);
        Intent intent=getIntent();
        if (intent!=null){
            String title=intent.getStringExtra("title");
            String url=intent.getStringExtra("url");
            tv_title.setText(title);
            webSettings=wv_webview.getSettings();
            WebviewUtil.setWebview(wv_webview, webSettings);
            wv_webview.loadUrl(url);
        }

        //注册广播
        myReceiver= new MyReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.action.open.vip");
        registerReceiver(myReceiver,intentFilter);
    }

    @Override
    protected void initData() {
        // 加载webview
        loadingWebview();
    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_occupation_webview);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    private void loadingWebview() {
        wv_webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (pb_webview!=null){
                    pb_webview.setProgress(newProgress);
                    if(newProgress==100){
                        pb_webview.setVisibility(View.GONE);
                    }
                }
            }
        });
    }
    @OnClick({R.id.iv_back,R.id.btn_openVip})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_openVip:
                jumpActivity(OccupationWebviewActivity.this, VipActivity.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    //接收支付成功的广播
    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.action.open.vip")){
                ll_openVip.setVisibility(View.GONE);
            }
        }
    }
}
