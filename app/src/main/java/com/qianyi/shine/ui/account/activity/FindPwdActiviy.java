package com.qianyi.shine.ui.account.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiAccount;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.account.view.ClearEditText;
import com.qianyi.shine.utils.CodeTimerTask;
import com.qianyi.shine.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by NEUNB on 2018/3/31.
 */

public class FindPwdActiviy extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_findsmscode)
    TextView tv_findsmscode;
    @BindView(R.id.btn_changePwd)
    Button btn_changePwd;
    @BindView(R.id.et_confirmCode)
    ClearEditText et_confirmCode;
    @BindView(R.id.et_phone)
    ClearEditText et_phone;
    @BindView(R.id.et_pwd)
    ClearEditText et_pwd;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        tv_title.setText("找回密码");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_find_pwd);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.tv_findsmscode,R.id.btn_changePwd})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_findsmscode:
                //获取验证码
                String phoneStr = et_phone.getText().toString().trim();
                if(TextUtils.isEmpty(phoneStr)){
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                getSmsCode(phoneStr);
                break;
            case R.id.btn_changePwd:
                String phoneNumber = et_phone.getText().toString().trim();
                String codeNumber = et_confirmCode.getText().toString().trim();
                String pwdNunber = et_pwd.getText().toString().trim();
                if(TextUtils.isEmpty(phoneNumber)){
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(codeNumber)){
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pwdNunber)){
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                changePwd(phoneNumber,codeNumber,pwdNunber);



                break;
        }
    }

    /****
     * 更换密码
     * @param phoneNumber
     * @param codeNumber
     * @param pwdNunber
     */
    private void changePwd(String phoneNumber, String codeNumber, String pwdNunber) {
        final CustomLoadingDialog loadingDialog = new CustomLoadingDialog(FindPwdActiviy.this);
        loadingDialog.show();
        apiAccount.changePwd(apiConstant.CHANGEPWD, phoneNumber, codeNumber, pwdNunber, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                loadingDialog.dismiss();
                Log.i("ssss",s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String code = jsonObject.getString("code");
                    if("0".equals(code)){
                        Toast.makeText(FindPwdActiviy.this, jsonObject.getString("info"), Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(FindPwdActiviy.this, jsonObject.getString("info"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                Log.i("ssss",e.getMessage());
                loadingDialog.dismiss();
            }
        });

    }

    /***
     * 获取短信验证码
     * @param phoneStr
     */
    private void getSmsCode(String phoneStr) {
        final CustomLoadingDialog loadingDialog = new CustomLoadingDialog(FindPwdActiviy.this);
        loadingDialog.show();
        apiAccount.getSmsCode(apiConstant.GETSMSCODE, phoneStr, "2", new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                loadingDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(s);
                    String code = jsonObject.getString("code");
                    if("0".equals(code)){
                        CodeTimerTask.getInstence().starrTimer(tv_findsmscode);
                        Toast.makeText(FindPwdActiviy.this, jsonObject.getString("info"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                loadingDialog.dismiss();
            }
        });

    }
}
