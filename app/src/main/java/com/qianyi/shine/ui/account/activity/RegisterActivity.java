package com.qianyi.shine.ui.account.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qianyi.shine.MainActivity;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiAccount;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.account.bean.RegisterBean;
import com.qianyi.shine.ui.account.view.ClearEditText;
import com.qianyi.shine.ui.account.view.MyCountDownTimer;
import com.qianyi.shine.utils.ToastUtils;
import com.qianyi.shine.utils.Utils;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by NEUNB on 2018/3/29.
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_phoneNum)
    ClearEditText et_phoneNum;
    @BindView(R.id.et_confirmCode)
    ClearEditText et_confirmCode;
    @BindView(R.id.et_pwd)
    ClearEditText et_pwd;
    private MyCountDownTimer timer;
    private CustomLoadingDialog customLoadingDialog;
    private String openid, unionid, nickname, headimgurl;
    private int sex;
    private Tencent mTencent;
    private UserInfo mInfo;
    private String qqOpenId,qqNickname,qqHeadimgurl,qqSex;
    private MyReceiver myReceiver;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        customLoadingDialog=new CustomLoadingDialog(this);

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
        setContentView(R.layout.activity_register);

    }

    @Override
    protected void initListener() {

    }
    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.tv_login,R.id.iv_login_wechat,R.id.iv_login_weibo,R.id.iv_login_qq,R.id.btn_register,R.id.btn_getConfirmCode})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_login:
                jumpActivity(this,LoginActivity.class);
                finish();
                break;
            case R.id.iv_login_qq:
                loginQQ();
                break;
            case R.id.iv_login_wechat:
                loginWX();
                break;
            case R.id.iv_login_weibo:
                jumpActivity(RegisterActivity.this, BindPhoneActivity.class);
                break;
                //注册
            case R.id.btn_register:
                String phoneNum = et_phoneNum.getText().toString();
                String confrimCode=et_confirmCode.getText().toString();
                String pwd = et_pwd.getText().toString();
                if (TextUtils.isEmpty(phoneNum)){
                   // ToastUtils.showShortToast("手机号不能为空");
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(confrimCode)){
                   // ToastUtils.showShortToast("验证码不能为空");
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pwd)){
                  //  ToastUtils.showShortToast("密码不能为空");
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                register(phoneNum,confrimCode,pwd);
                break;
            case R.id.btn_getConfirmCode:
                String phoneNumber = et_phoneNum.getText().toString();
                if (!TextUtils.isEmpty(phoneNumber)) {
                    if (phoneNumber.matches("^[1][3469758][0-9]{9}$")) {
                        timer = new MyCountDownTimer(60000, 1000, (Button) view);
                        timer.start();
                        // 向服务器请求验证码
                        getConfirmCode(phoneNumber);
                    } else {
                        Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
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
    //获取短信验证码
    private void getConfirmCode(String phoneNumber) {
        customLoadingDialog.show();
        apiAccount.getConfirmCode(apiConstant.GET_COFIRM_CODE, phoneNumber, "1", new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                Log.i("kk",s);
                customLoadingDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(s);
                    String info = jsonObject.getString("info");
                    Toast.makeText(RegisterActivity.this, info, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                customLoadingDialog.dismiss();
                Log.i("tt",e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this, "网络错误发送失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
    //注册的方法
    private void register(String phoneNum, String confrimCode, String pwd) {
        final CustomLoadingDialog loadingDialog = new CustomLoadingDialog(RegisterActivity.this);
        loadingDialog.show();
       apiAccount.Register(apiConstant.REGISTER, phoneNum, pwd, confrimCode, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                Log.i("ss",s);
                loadingDialog.dismiss();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        RegisterBean registerBean=gson.fromJson(s, RegisterBean.class);
                        if(registerBean != null){
                            String code = registerBean.getCode();
                            if("0" .endsWith(code)){
                                Toast.makeText(RegisterActivity.this, registerBean.getInfo(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                finish();
                            }else {
                                Toast.makeText(RegisterActivity.this, registerBean.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, final Exception e) {
                Log.i("ss",e.getMessage());
                loadingDialog.dismiss();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void loginQQ() {
        mTencent.login(RegisterActivity.this, "all", new BaseUiListener());
    }
    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            //获取openid和token
            initOpenIdAndToken(response);
            //获取用户信息
            getUserInfo();
        }
        @Override
        public void onError(UiError uiError) {
            Toast.makeText(RegisterActivity.this, uiError.errorMessage, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {

        }
    }
    private void initOpenIdAndToken(Object object) {
        JSONObject jb = (JSONObject) object;
        try {
            qqOpenId = jb.getString("openid");  //openid用户唯一标识
            String access_token = jb.getString("access_token");
            String expires = jb.getString("expires_in");
            mTencent.setOpenId(qqOpenId);
            mTencent.setAccessToken(access_token, expires);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getUserInfo() {
        QQToken token = mTencent.getQQToken();
        mInfo = new UserInfo(RegisterActivity.this, token);
        mInfo.getUserInfo(new IUiListener() {
            @Override
            public void onComplete(Object object) {
                JSONObject jb = (JSONObject) object;
                String s = jb.toString();
                qqLogin(object);
            }
            @Override
            public void onError(UiError uiError) {
            }

            @Override
            public void onCancel() {
            }
        });
    }

    private void qqLogin(Object object) {
        JSONObject jb = (JSONObject) object;
        String s = jb.toString();
        Log.i("tag",s);
        try {
            qqNickname = jb.getString("nickname");
            qqHeadimgurl= jb.getString("figureurl_qq_2");  //头像图片的url
            qqSex= jb.getString("gender");  //头像图片的url
            Log.i("tag",qqOpenId);
            if (qqSex.equals("男")){
                qqSex="1";
            }else{
                qqSex="0";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        final CustomLoadingDialog loadingDialog = new CustomLoadingDialog(RegisterActivity.this);
        loadingDialog.show();
        apiAccount.qqLogin(apiConstant.QQ_LOGIN, qqOpenId, "", qqSex, qqHeadimgurl, qqNickname, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                Log.i("tag", s);
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
                                    Utils.saveUser(user, RegisterActivity.this);
                                    if (member_scoreinfo_status.equals("0")) {
                                        Intent intent = new Intent(RegisterActivity.this, GuessScoreActivity.class);
                                        intent.putExtra("tag", "setScore");
                                        startActivity(intent);
                                        finish();
                                    } else if (member_scoreinfo_status.equals("1")) {
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                } catch (Exception e) {
                                    Log.i("excaption_shine", e.getMessage());
                                }
                            } else {
                                Toast.makeText(RegisterActivity.this, "" + loginBean.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                Log.i("tag",e.getMessage());
                loadingDialog.dismiss();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUiListener());
        Tencent.handleResultData(data, new BaseUiListener());
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
                                    Utils.saveUser(user, RegisterActivity.this);
                                   /* Intent intent=new Intent(LoginActivity.this, GuessScoreActivity.class);
                                    intent.putExtra("tag","setScore");
                                    startActivity(intent);
                                    finish();*/
                                    if (member_scoreinfo_status.equals("0")) {
                                        Intent intent = new Intent(RegisterActivity.this, GuessScoreActivity.class);
                                        intent.putExtra("tag", "setScore");
                                        startActivity(intent);
                                        finish();
                                    } else if (member_scoreinfo_status.equals("1")) {
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                } catch (Exception e) {
                                    Log.i("excaption_shine", e.getMessage());
                                }
                            } else {
                                Toast.makeText(RegisterActivity.this, "" + loginBean.getInfo(), Toast.LENGTH_SHORT).show();
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
