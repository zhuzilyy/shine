package com.qianyi.shine.ui.account.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.qianyi.shine.R;
import com.qianyi.shine.api.apiAccount;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.application.MyApplication;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.account.view.ClearEditText;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by NEUNB on 2018/3/29.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_userName)
    ClearEditText et_userName;
    @BindView(R.id.et_pwd)
    ClearEditText et_pwd;
    CustomLoadingDialog customLoadingDialog;
    public static IWXAPI mWxApi;
    @Override
    protected void initViews() {
        customLoadingDialog=new CustomLoadingDialog(this);
        //customLoadingDialog.show();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_login);
    }
    @Override
    protected void initListener() {


    }

    @Override
    protected void setStatusBarColor() {
    }

    @OnClick({R.id.btn_login,R.id.tv_register,R.id.iv_login_qq,R.id.iv_login_weibo,R.id.iv_login_wechat,R.id.tv_findPwd})
    public void click(View view){
        switch (view.getId()){
            case R.id.btn_login:
                String userName=et_userName.getText().toString();
                String pwd=et_pwd.getText().toString();
                /*if (isParamsEmpty(userName,pwd)){
                    login(userName,pwd);
                }*/
                jumpActivity(LoginActivity.this, GuessScoreActivity.class);
                finish();
                break;
            case R.id.tv_register:
                jumpActivity(LoginActivity.this, RegisterActivity.class);
                finish();
                break;
            case R.id.iv_login_qq:
                jumpActivity(LoginActivity.this, BindPhoneActivity.class);
                break;
            case R.id.iv_login_wechat:
                loginWX();
                break;
            case R.id.iv_login_weibo:
                jumpActivity(LoginActivity.this, BindPhoneActivity.class);
                break;
            case R.id.tv_findPwd:
                jumpActivity(LoginActivity.this, FindPwdActiviy.class);
                break;

        }
    }
    //登录的方法
    private void login(String userName, String pwd) {
        apiAccount.Login("", userName, pwd, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {

            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {

            }
        });
    }

    //验证参数是不是非空
    private boolean isParamsEmpty(String userName, String pwd) {
        if (TextUtils.isEmpty(userName)){
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return  false;
        }
        if (TextUtils.isEmpty(pwd)){
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return  false;
        }
        return true;
    }

    private void loginWX() {
        if (!MyApplication.mWxApi.isWXAppInstalled()) {
            Toast.makeText(this, "您还没安装微信客户端", Toast.LENGTH_SHORT).show();
            return;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "shine_wx_login";
        MyApplication.mWxApi.sendReq(req);

    }
}
