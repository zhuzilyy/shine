package com.qianyi.shine.ui.mine.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiPay;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.mine.bean.AliBean;
import com.qianyi.shine.ui.mine.bean.VipCongBean;
import com.qianyi.shine.ui.mine.bean.WXPayBean;
import com.qianyi.shine.utils.Utils;
import com.tencent.mm.sdk.modelpay.PayReq;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/31.
 */

public class VipActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rl_vip)
    RelativeLayout rl_vip;
    private PopupWindow pw_buyVip,pw_payMethod;
    private View view_buyVip,view_payMethod;
    private TextView tv_confirmOrder;
    //支付方式默认是微信：0   支付宝：1
    private int currentPayMethod = 0;
    private TextView tv_gotoPay;
    private PayReq req;
    private String mOrderstr;
    //
    private String vipMoney;
    private String vipLimit;
    private TextView tv_vipMoney;
    private TextView tv_totalMoney;
    private TextView tv_timeLimit;

    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        tv_title.setText("购买VIP");
        view_buyVip=LayoutInflater.from(this).inflate(R.layout.pw_buy_vip,null);
        view_payMethod=LayoutInflater.from(this).inflate(R.layout.pw_pay_method,null);
        tv_confirmOrder=view_buyVip.findViewById(R.id.tv_confirmOrder);

        final ImageView iv_wx = view_payMethod.findViewById(R.id.iv_payway_select_wx);
        final ImageView iv_zfb = view_payMethod.findViewById(R.id.iv_payway_select_zfb);

        tv_vipMoney = view_buyVip.findViewById(R.id.tv_vipMoney);
        tv_totalMoney=view_buyVip.findViewById(R.id.tv_totalMoney);
        tv_timeLimit=view_buyVip.findViewById(R.id.tv_timeLimit);
        tv_gotoPay=view_payMethod.findViewById(R.id.tv_gotoPay);
        tv_gotoPay.setOnClickListener(this);
        //选择方式
        //点击微信支付
        view_payMethod.findViewById(R.id.re_payway_wx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_wx.setImageResource(R.mipmap.pay_checked);
                iv_zfb.setImageResource(R.mipmap.pay_nochecked);
                currentPayMethod=0;
            }
        });
        //点击支付宝支付
        view_payMethod.findViewById(R.id.re_payway_zfb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_wx.setImageResource(R.mipmap.pay_nochecked);
                iv_zfb.setImageResource(R.mipmap.pay_checked);
                currentPayMethod=1;
            }
        });

    }
    @Override
    protected void initData() {

    }

    /***
     * 获取支付宝订单
     * @param id
     */
    private void getZFBInfo(String id) {
        apiPay.alipay(apiConstant.ALIPAY, id, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                Gson gson = new Gson();
                AliBean aliBean = gson.fromJson(s, AliBean.class);
                if(aliBean!=null){
                    AliBean.AliData aliData = aliBean.getData();
                    if(aliData!=null){
                        AliBean.AliData.AliInfo aliInfo = aliData.getInfo();
                        if(aliInfo!=null){
                            mOrderstr = aliInfo.getOrderstr();
                        }
                    }
                }
            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {

            }
        });
    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_vip);
    }

    @Override
    protected void initListener() {

    }
    //选择支付方式
    private void selectPayMethod() {
        pw_payMethod = new PopupWindow(this);
        pw_payMethod.setContentView(view_payMethod);
        pw_payMethod.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pw_payMethod.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        pw_payMethod.setTouchable(true);
        pw_payMethod.setFocusable(true);
        pw_payMethod.setBackgroundDrawable(new BitmapDrawable());
        pw_payMethod.setAnimationStyle(R.style.AnimBottom);
        pw_payMethod.showAtLocation(rl_vip, Gravity.BOTTOM, 0, 0);
        // 设置pw弹出时候的背景颜色的变化
        backgroundAlpha(0.5f);
        pw_payMethod.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });





    }
    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.tv_activeVip,R.id.tv_openVip})
    public void click(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_activeVip:
                intent=new Intent(VipActivity.this,ActiveVipActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_openVip:
                getVipConf();
                break;
        }
    }

    /***
     * 获取支付参数
     */
    private void getVipConf() {
        final CustomLoadingDialog loadingDialog = new CustomLoadingDialog(VipActivity.this);
        loadingDialog.show();
        apiPay.getPayConf(apiConstant.VIP_CONFIG, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                loadingDialog.dismiss();
                Gson gson = new Gson();
                VipCongBean vipCongBean = gson.fromJson(s, VipCongBean.class);
                if(vipCongBean!=null){
                    VipCongBean.VipCongData vipCongData = vipCongBean.getData();
                    if(vipCongData!=null){
                        VipCongBean.VipCongData.VipCongInfo vipCongInfo = vipCongData.getInfo();
                        if(vipCongInfo!=null){
                            vipMoney = vipCongInfo.getMoney();
                            vipLimit = vipCongInfo.getLimit();
                            if(!TextUtils.isEmpty(vipMoney)){
                                showPopWindow(vipMoney);
                            }
                        }
                    }
                }

            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                loadingDialog.dismiss();
            }
        });


    }

    //出现提示的pw
    private void showPopWindow(String vipMoney) {
        pw_buyVip = new PopupWindow(this);
        pw_buyVip.setContentView(view_buyVip);
        pw_buyVip.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pw_buyVip.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        tv_vipMoney.setText("￥"+vipMoney);
        tv_totalMoney.setText("￥"+vipMoney);
        tv_timeLimit.setText("有效期为"+vipLimit+"年");
        pw_buyVip.setTouchable(true);
        pw_buyVip.setFocusable(true);
        pw_buyVip.setBackgroundDrawable(new BitmapDrawable());
        pw_buyVip.setAnimationStyle(R.style.AnimBottom);
        pw_buyVip.showAtLocation(rl_vip, Gravity.BOTTOM, 0, 0);
        // 设置pw弹出时候的背景颜色的变化
        backgroundAlpha(0.5f);
        pw_buyVip.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
        tv_confirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pw_buyVip.dismiss();
                selectPayMethod();
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        this.getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tv_gotoPay:
                if(0==currentPayMethod){
                    //微信支付
                    wxPay();
                    pw_payMethod.dismiss();
                }else if(1==currentPayMethod){
                    //支付宝支付
                    zfbPay();
                    pw_payMethod.dismiss();
                }
            break;

            default:
            break;


        }
    }

    /***
     * 支付宝支付
     */
    private void zfbPay() {
        final CustomLoadingDialog loadingDialog = new CustomLoadingDialog(VipActivity.this);
        loadingDialog.show();
        LoginBean.LoginData.LoginInfo user = Utils.readUser(VipActivity.this);
        if(user!=null){
            if(!TextUtils.isEmpty(user.getId())){
                apiPay.alipay(apiConstant.ALIPAY, user.getId(), new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(Call call, Response response, String s) {
                        loadingDialog.dismiss();
                        Gson gson = new Gson();
                        AliBean aliBean = gson.fromJson(s, AliBean.class);
                        if(aliBean!=null){
                            AliBean.AliData aliData = aliBean.getData();
                            if(aliData!=null){
                                AliBean.AliData.AliInfo aliInfo = aliData.getInfo();
                                if(aliInfo!=null){
                                    mOrderstr = aliInfo.getOrderstr();
                                    if(!TextUtils.isEmpty(mOrderstr)){
                                        alipay2(mOrderstr);
                                    }
                                }
                            }
                        }
                    }
                    @Override
                    public void onEror(Call call, int statusCode, Exception e) {
                        loadingDialog.dismiss();
                        Log.i("sss",e.getMessage());
                    }
                });
            }
        }






    }

    private void alipay2(final String mOrderstr) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(VipActivity.this);
                if(alipay!=null){
                    if(!TextUtils.isEmpty(mOrderstr)){
                        final Map<String,String> result = alipay.payV2(VipActivity.this.mOrderstr,true);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String result_=result.get("result");
                                Log.i("","");
                                if(!TextUtils.isEmpty(result_)){
                                    try {
                                        JSONObject jsonObject=new JSONObject(result_);
                                        JSONObject jsonObject1=jsonObject.getJSONObject("alipay_trade_app_pay_response");
                                        String code_= jsonObject1.getString("code");
                                        if("10000".equals(code_)){
                                            //支付成功
                                           Log.i("kkk","支付成功");
                                        }else {
                                            //支付失败
                                            Log.i("kkk","支付失败");
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                }
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /***
     * 微信支付
     */
    private void wxPay() {

        LoginBean.LoginData.LoginInfo user = Utils.readUser(VipActivity.this);
        if(user==null){
            return;
        }


        final CustomLoadingDialog loadingDialog = new CustomLoadingDialog(VipActivity.this);
        loadingDialog.show();
        apiPay.wxpay(apiConstant.WXPAY, user.getId(), new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                loadingDialog.dismiss();
                Log.i("s",s);
                Gson gson = new Gson();
                WXPayBean wxPayBean = gson.fromJson(s, WXPayBean.class);
                if(wxPayBean!=null){
                    String code = wxPayBean.getCode();
                    if(!TextUtils.isEmpty(code)){
                        WXPayBean.WXPayData wxPayData = wxPayBean.getData();
                        if(wxPayData!=null){
                            WXPayBean.WXPayData.WXPayInfo wxPayInfo = wxPayData.getInfo();
                            if(wxPayInfo!=null){
                                //调起微信支付
                                startWXPayPage(wxPayInfo);
                            }
                        }
                    }
                }
            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                loadingDialog.dismiss();
                Log.i("s",e.getMessage());
            }
        });



    }

    /****
     * 调起微信支付
     * @param payInfo
     */
    private void startWXPayPage(final WXPayBean.WXPayData.WXPayInfo payInfo) {

        apiConstant.APP_ID=payInfo.getAppid();
        apiConstant.MCH_ID=payInfo.getMch_id();
        apiConstant.NOTIFY_URL=payInfo.getNotify_url();
        apiConstant.API_KEY=payInfo.getKey();
        double totalfeeDouble=Double.parseDouble(payInfo.getTotal_fee())*100;
        int totalfeeInt= (int) totalfeeDouble;
        apiConstant.TOTAL_FEE=totalfeeInt+"";
        apiConstant.OUT_TRADE_NO=payInfo.getOut_trade_no();
        if(!TextUtils.isEmpty(apiConstant.TOTAL_FEE)&&!TextUtils.isEmpty(apiConstant.OUT_TRADE_NO)){
            WxPayActivity payActivity=new WxPayActivity(VipActivity.this);
        }
    }
}
