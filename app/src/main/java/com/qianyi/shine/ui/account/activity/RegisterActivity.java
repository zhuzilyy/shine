package com.qianyi.shine.ui.account.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.qianyi.shine.R;
import com.qianyi.shine.api.apiAccount;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.ui.account.view.ClearEditText;
import com.qianyi.shine.utils.ToastUtils;

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
    @Override
    protected void initViews() {

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
    @OnClick({R.id.tv_login,R.id.iv_login_wechat,R.id.iv_login_weibo,R.id.iv_login_qq,R.id.btn_register})
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
        }
    }
    //注册的方法
    private void register(String phoneNum, String confrimCode, String pwd) {
      /* apiAccount.Register(apiConstant.REGISTER, phoneNum, pwd, confrimCode, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this, "3333333333"+s, Toast.LENGTH_SHORT).show();
                        Log.i("tag",s);
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, final Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this, "3333333333"+e, Toast.LENGTH_SHORT).show();
                        Log.i("tag",e.getMessage()+"======3333=====");
                    }
                });
            }
        });*/
        RequestParams params=new RequestParams(apiConstant.REGISTER);
        params.addParameter("mobile",phoneNum);
        params.addParameter("password",pwd);
        params.addParameter("smscode",confrimCode);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("tag",result);
                Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}
