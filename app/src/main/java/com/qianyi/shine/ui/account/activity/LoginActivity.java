package com.qianyi.shine.ui.account.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qianyi.shine.MainActivity;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiAccount;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.application.MyApplication;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.account.view.ClearEditText;
import com.qianyi.shine.utils.Utils;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

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
    private MyReceiver myReceiver;
    private String openid, unionid, nickname, headimgurl;
    private int sex;
    private Tencent mTencent;

    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        customLoadingDialog = new CustomLoadingDialog(this);
        //customLoadingDialog.show();

        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.action.wechat");
        registerReceiver(myReceiver, intentFilter);

        mTencent = Tencent.createInstance("101475945", getApplicationContext());
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
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    @Override
    protected void setStatusBarColor() {
    }

    @OnClick({R.id.btn_login, R.id.tv_register, R.id.iv_login_qq, R.id.iv_login_weibo, R.id.iv_login_wechat, R.id.tv_findPwd})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String userName = et_userName.getText().toString();
                String pwd = et_pwd.getText().toString();
                if (isParamsEmpty(userName, pwd)) {
                    login(userName, pwd);
                }
                break;
            case R.id.tv_register:
                jumpActivity(LoginActivity.this, RegisterActivity.class);
                finish();
                break;
            case R.id.iv_login_qq:
                //jumpActivity(LoginActivity.this, BindPhoneActivity.class);
                loginQQ();
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

    private void loginQQ() {
        mTencent.login(LoginActivity.this, "all", new BaseUiListener());
    }
    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
            try {
                //获得的数据是JSON格式的，获得你想获得的内容
                //如果你不知道你能获得什么，看一下下面的LOG
                Log.v("----TAG--", "-------------"+response.toString());
                String openidString = ((JSONObject) response).getString("openid");
                mTencent.setOpenId(openidString);
                //saveUser("44","text","text",1);
                mTencent.setAccessToken(((JSONObject) response).getString("access_token"),((JSONObject) response).getString("expires_in"));
                Log.v("TAG", "-------------"+openidString);
                //access_token= ((JSONObject) response).getString("access_token");
                //expires_in = ((JSONObject) response).getString("expires_in");
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        @Override
        public void onError(UiError uiError) {
            Toast.makeText(LoginActivity.this, uiError.errorMessage, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUiListener());
        Tencent.handleResultData(data, new BaseUiListener());
    }


    //登录的方法

        private void login(String userName, String pwd) {
            final CustomLoadingDialog loadingDialog = new CustomLoadingDialog(LoginActivity.this);
            loadingDialog.show();
            apiAccount.Login(apiConstant.LOGIN, userName, pwd, new RequestCallBack<String>() {
                @Override
                public void onSuccess(Call call, Response response, final String s) {
                    loadingDialog.dismiss();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                            LoginBean loginBean = gson.fromJson(s, LoginBean.class);
                            if (loginBean != null) {
                                String code = loginBean.getCode();
                                if ("0".equals(code)) {
                                    LoginBean.LoginData.LoginInfo user = loginBean.getData().getInfo();
                                    String member_scoreinfo_status = loginBean.getData().getInfo().getMember_scoreinfo_status();
                                    try {
                                        //存储当前用户
                                        Utils.saveUser(user, LoginActivity.this);
                                        if (member_scoreinfo_status.equals("0")) {
                                            Intent intent = new Intent(LoginActivity.this, GuessScoreActivity.class);
                                            intent.putExtra("tag", "setScore");
                                            startActivity(intent);
                                            finish();
                                        } else if (member_scoreinfo_status.equals("1")) {
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    } catch (Exception e) {
                                        Log.i("excaption_shine", e.getMessage());
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "" + loginBean.getInfo(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }

                @Override
                public void onEror(Call call, int statusCode, Exception e) {
                    Log.i("", "123" + e.getMessage());
                    loadingDialog.dismiss();
                }
            });
        }

        //验证参数是不是非空
        private boolean isParamsEmpty(String userName, String pwd) {
            if (TextUtils.isEmpty(userName)) {
                Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(pwd)) {
                Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }

        private void loginWX() {
            IWXAPI mWxApi = WXAPIFactory.createWXAPI(this, apiConstant.APP_ID, false);
            // 将该app注册到微信
            mWxApi.registerApp(apiConstant.APP_ID);
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "diandi_wx_login";
            mWxApi.sendReq(req);
        }

        class MyReceiver extends BroadcastReceiver {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals("com.action.wechat")) {
                    openid = intent.getStringExtra("openid");
                    unionid = intent.getStringExtra("unionid");
                    nickname = intent.getStringExtra("nickname");
                    headimgurl = intent.getStringExtra("headimgurl");
                    sex = intent.getIntExtra("sex", 0);
                    wechatLogin();
                }
            }
        }

        private void wechatLogin() {
            customLoadingDialog.show();
            apiAccount.wechatLogin(apiConstant.WECHAT_LOGIN, openid, unionid, sex, headimgurl, nickname, new RequestCallBack<String>() {
                @Override
                public void onSuccess(Call call, Response response, final String s) {
                    Log.i("tag", s);
                    customLoadingDialog.dismiss();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                            LoginBean loginBean = gson.fromJson(s, LoginBean.class);
                            if (loginBean != null) {
                                String code = loginBean.getCode();
                                if ("0".equals(code)) {
                                    LoginBean.LoginData.LoginInfo user = loginBean.getData().getInfo();
                                    String member_scoreinfo_status = loginBean.getData().getInfo().getMember_scoreinfo_status();
                                    try {
                                        //存储当前用户
                                        Utils.saveUser(user, LoginActivity.this);
                                   /* Intent intent=new Intent(LoginActivity.this, GuessScoreActivity.class);
                                    intent.putExtra("tag","setScore");
                                    startActivity(intent);
                                    finish();*/
                                        if (member_scoreinfo_status.equals("0")) {
                                            Intent intent = new Intent(LoginActivity.this, GuessScoreActivity.class);
                                            intent.putExtra("tag", "setScore");
                                            startActivity(intent);
                                            finish();
                                        } else if (member_scoreinfo_status.equals("1")) {
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    } catch (Exception e) {
                                        Log.i("excaption_shine", e.getMessage());
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "" + loginBean.getInfo(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }

                @Override
                public void onEror(Call call, int statusCode, Exception e) {
                    customLoadingDialog.dismiss();
                }
            });
        }
    }


