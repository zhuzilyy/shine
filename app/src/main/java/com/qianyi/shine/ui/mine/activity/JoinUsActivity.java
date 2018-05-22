package com.qianyi.shine.ui.mine.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by NEUNB on 2018/4/3.
 */

public class JoinUsActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_desc)
    TextView tv_desc;
    private String memberId;
    private CustomLoadingDialog customLoadingDialog;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        tv_title.setText("加入我们");
        LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(this);
        memberId= loginInfo.getId();
        customLoadingDialog=new CustomLoadingDialog(this);
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_join_us);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.btn_confirm})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_confirm:
                String name = tv_name.getText().toString();
                String num = tv_num.getText().toString().trim();
                String desc = tv_desc.getText().toString();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(num)){
                    Toast.makeText(this, "请输入电话号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(desc)){
                    Toast.makeText(this, "请输入个人简介", Toast.LENGTH_SHORT).show();
                    return;
                }
                joinUs(name,num,desc);
                break;
        }
    }

    private void joinUs(String name,String num,String desc) {
        customLoadingDialog.show();
        apiHome.joinUs(apiConstant.JOIN_US, memberId, name, num, desc, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        customLoadingDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(s);
                            String code = jsonObject.getString("code");
                            String info = jsonObject.getString("info");
                            Toast.makeText(JoinUsActivity.this, info, Toast.LENGTH_SHORT).show();
                            if (code.equals("0")){
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                customLoadingDialog.dismiss();
                Toast.makeText(JoinUsActivity.this, "网络错误申请失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
