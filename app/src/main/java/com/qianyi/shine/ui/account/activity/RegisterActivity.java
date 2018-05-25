package com.qianyi.shine.ui.account.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiAccount;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.account.bean.RegisterBean;
import com.qianyi.shine.ui.account.view.ClearEditText;
import com.qianyi.shine.ui.account.view.MyCountDownTimer;
import com.qianyi.shine.utils.ToastUtils;

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
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        customLoadingDialog=new CustomLoadingDialog(this);
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
                jumpActivity(RegisterActivity.this, BindPhoneActivity.class);
                break;
            case R.id.iv_login_wechat:
                jumpActivity(RegisterActivity.this, BindPhoneActivity.class);
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
                    ToastUtils.showShortToast("手机号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(confrimCode)){
                    ToastUtils.showShortToast("验证码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(pwd)){
                    ToastUtils.showShortToast("密码不能为空");
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
    //获取短信验证码
    private void getConfirmCode(String phoneNumber) {
        customLoadingDialog.show();
        apiAccount.getConfirmCode(apiConstant.GET_COFIRM_CODE, phoneNumber, "1", new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
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
                Toast.makeText(RegisterActivity.this, "网络错误发送失败", Toast.LENGTH_SHORT).show();
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
}
