package com.qianyi.shine.ui.home.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiAccount;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.account.bean.AreaBean;
import com.qianyi.shine.ui.account.bean.AreaDataInfo;
import com.qianyi.shine.ui.account.bean.AreaInfo;
import com.qianyi.shine.ui.home.adapter.SelectAreaAdapter;
import com.qianyi.shine.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/8.
 */

public class SelectCollegeAreaActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.gv_area)
    GridView gv_area;
    @BindView(R.id.no_internet_rl)
    RelativeLayout no_internet_rl;
    @BindView(R.id.reload)
    TextView reload;
    private SelectAreaAdapter selectAreaAdapter;
   /* private String[] areas={"全国","北京市","天津市","上海市","重庆市","河北省","山西省","辽宁省","吉林省","黑龙江省",
            "江苏省","浙江省","安徽省","福建省","江西省","山东省","河南省","湖北省","湖南省","广东省","海南省","四川省","贵州省",
            "云南省","陕西省","甘肃省","青海省","内蒙古","广西","宁夏","新疆",};*/
   private CustomLoadingDialog customLoadingDialog;
   private List<AreaInfo> infoList;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        tv_title.setText("选择省份");
        customLoadingDialog=new CustomLoadingDialog(this);
        infoList=new ArrayList<>();
        selectAreaAdapter=new SelectAreaAdapter(infoList,SelectCollegeAreaActivity.this);
        gv_area.setAdapter(selectAreaAdapter);
        gv_area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String tag = infoList.get(i).getTag();
                if (tag.equals("0")){
                    Toast.makeText(SelectCollegeAreaActivity.this, "该地区暂未开放", Toast.LENGTH_SHORT).show();
                    return;
                }
                String area = infoList.get(i).getArea();
                Intent intent=new Intent();
                intent.putExtra("area",area);
                setResult(1,intent);
                finish();
            }
        });
    }
    @Override
    protected void initData() {
        if (Utils.hasInternet()){
            customLoadingDialog.show();
            getData();
            gv_area.setVisibility(View.VISIBLE);
            no_internet_rl.setVisibility(View.GONE);
        }else{
            gv_area.setVisibility(View.GONE);
            no_internet_rl.setVisibility(View.VISIBLE);
        }
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }
    //获取数据
    private void getData() {
        apiAccount.getOpenArea(apiConstant.OPEN_AREA, new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        customLoadingDialog.dismiss();
                        Gson gson=new Gson();
                        AreaBean areaBean = gson.fromJson(s, AreaBean.class);
                        List<AreaInfo> open_area = areaBean.getData().getInfo().getOpen_area();
                        infoList.addAll(open_area);
                        selectAreaAdapter.notifyDataSetChanged();
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                customLoadingDialog.dismiss();
                gv_area.setVisibility(View.GONE);
                no_internet_rl.setVisibility(View.VISIBLE);
            }
        });
    }
    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_select_college_area);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
