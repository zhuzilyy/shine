package com.qianyi.shine.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.google.gson.Gson;
import com.qianyi.shine.api.apiAccount;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.account.activity.GuessScoreActivity;
import com.qianyi.shine.ui.account.activity.LoginActivity;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.utils.Utils;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import okhttp3.Call;
import okhttp3.Response;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
	private IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.pay_result);
		api = WXAPIFactory.createWXAPI(this, apiConstant.APP_ID);
		api.handleIntent(getIntent(), this);
		BaseActivity.addActivity(this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
	}
	@Override
	public void onReq(BaseReq req) {

	}
	@Override
	public void onResp(BaseResp resp) {
		if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
			//֧支付成功
			Toast.makeText(this, "xxx,chengggong百元", Toast.LENGTH_SHORT).show();
			LoginBean.LoginData.LoginInfo user = Utils.readUser(WXPayEntryActivity.this);
			if(user!=null){
				apiAccount.Login(apiConstant.LOGIN, user.getMobile(), user.getPassword(), new RequestCallBack<String>() {
					@Override
					public void onSuccess(Call call, Response response, final String s) {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Gson gson = new Gson();
								LoginBean loginBean=gson.fromJson(s, LoginBean.class);
								if(loginBean != null){
									String code = loginBean.getCode();
									if("0" .equals(code)){
										LoginBean.LoginData.LoginInfo user = loginBean.getData().getInfo();
										try {
											//清除当前用户信息
											Utils.clearSharedUser(WXPayEntryActivity.this);
											//存储当前用户
											Utils.saveUser(user,WXPayEntryActivity.this);
											finish();
										}catch (Exception e){
											Log.i("excaption_shine",e.getMessage());
										}
									}else {
										Toast.makeText(WXPayEntryActivity.this, ""+loginBean.getInfo(), Toast.LENGTH_SHORT).show();
									}
								}
							}
						});
					}
					@Override
					public void onEror(Call call, int statusCode, Exception e) {
						Log.i("","123"+e.getMessage());
					}
				});
			}
		}else{
			finish();
		}
	}
}