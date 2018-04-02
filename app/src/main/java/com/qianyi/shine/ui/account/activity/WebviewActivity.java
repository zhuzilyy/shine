package com.qianyi.shine.ui.account.activity;

import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.utils.WebviewUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by NEUNB on 2018/3/31.
 */

public class WebviewActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.wv_webview)
    WebView wv_webview;
    @BindView(R.id.pb_webview)
    ProgressBar pb_webview;
    private WebSettings webSettings;
    @Override
    protected void initViews() {
        tv_title.setText("用户须知");
        webSettings=wv_webview.getSettings();
        WebviewUtil.setWebview(wv_webview, webSettings);
        wv_webview.loadUrl("https://www.baidu.com/?tn=78040160_26_pg&ch=1");
    }

    @Override
    protected void initData() {
        // 加载webview
        loadingWebview();
    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_webview);
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
    @OnClick({R.id.iv_back})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
