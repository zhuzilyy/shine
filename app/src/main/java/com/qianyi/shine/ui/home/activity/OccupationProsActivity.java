package com.qianyi.shine.ui.home.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.ui.home.adapter.OccupationProsAdapter;
import com.qianyi.shine.ui.home.view.MyListView;
import com.qianyi.shine.ui.mine.activity.VipActivity;
import com.qianyi.shine.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/27.
 */

public class OccupationProsActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.lv_occupation)
    ListView lv_occupation;
    @BindView(R.id.ll_openVip)
    LinearLayout ll_openVip;
    private Intent intent;
    private ArrayList<String> marginListYear,marginListSalary,marginAllListYear,marginAllListSalary;
    private OccupationProsAdapter occupationProsAdapter,allOccupationProsAdapter;
    private View view_footer,view_header;
    private MyListView lv_allOccupationPros;
    private MyReceiver myReceiver;
    @Override
    protected void initViews() {
        tv_title.setText("就业前景");
        intent=getIntent();
        if (intent!=null){
            marginListYear=intent.getStringArrayListExtra("marginListYear");
            marginListSalary=intent.getStringArrayListExtra("marginListSalary");
            marginAllListYear=intent.getStringArrayListExtra("marginAllListYear");
            marginAllListSalary=intent.getStringArrayListExtra("marginAllListSalary");
        }
        view_footer=LayoutInflater.from(this).inflate(R.layout.footer_occupation_pros,null);
        view_header=LayoutInflater.from(this).inflate(R.layout.header_occupation_pros,null);
        lv_allOccupationPros=view_footer.findViewById(R.id.lv_occupation);

        //注册广播
        myReceiver= new MyReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.action.open.vip");
        registerReceiver(myReceiver,intentFilter);

        LoginBean.LoginData.LoginInfo loginInfo = Utils.readUser(this);
        String isVip=loginInfo.getIs_vip();
        if (isVip.equals("0")){
            ll_openVip.setVisibility(View.VISIBLE);
            lv_occupation.setVisibility(View.GONE);
        }else if(isVip.equals("1")){
            ll_openVip.setVisibility(View.GONE);
            lv_occupation.setVisibility(View.VISIBLE);
        }
    }
    @Override
    protected void initData() {
        occupationProsAdapter=new OccupationProsAdapter(marginListYear,marginListSalary,this);
        lv_occupation.setAdapter(occupationProsAdapter);

        allOccupationProsAdapter=new OccupationProsAdapter(marginAllListYear,marginAllListSalary,this);
        lv_allOccupationPros.setAdapter(allOccupationProsAdapter);
        lv_occupation.addFooterView(view_footer);
        lv_occupation.addHeaderView(view_header);

    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_occupation_pros);
    }
    @Override
    protected void initListener() {

    }
    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.btn_openVip})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_openVip:
                jumpActivity(OccupationProsActivity.this, VipActivity.class);
                break;
        }
    }
    //接收支付成功的广播
    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("com.action.open.vip")){
                ll_openVip.setVisibility(View.GONE);
                lv_occupation.setVisibility(View.VISIBLE);
            }
        }
    }
}
