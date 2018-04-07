package com.qianyi.shine.ui.mine.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/31.
 */

public class VipActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rl_vip)
    RelativeLayout rl_vip;
    private PopupWindow pw_buyVip,pw_payMethod;
    private View view_buyVip,view_payMethod;
    private TextView tv_confirmOrder;
    @Override
    protected void initViews() {
        tv_title.setText("购买VIP");
        view_buyVip=LayoutInflater.from(this).inflate(R.layout.pw_buy_vip,null);
        view_payMethod=LayoutInflater.from(this).inflate(R.layout.pw_pay_method,null);
        tv_confirmOrder=view_buyVip.findViewById(R.id.tv_confirmOrder);
    }
    @Override
    protected void initData() {

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
        pw_payMethod
                .showAtLocation(rl_vip, Gravity.BOTTOM, 0, 0);
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
                showPopWindow();
                break;
        }
    }
    //出现提示的pw
    private void showPopWindow() {
        pw_buyVip = new PopupWindow(this);
        pw_buyVip.setContentView(view_buyVip);
        pw_buyVip.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pw_buyVip.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
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
}
