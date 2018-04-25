package com.qianyi.shine.ui.home.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.home.adapter.CollegeAdapter;
import com.qianyi.shine.ui.home.view.MyGridView;
import com.qianyi.shine.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/1.
 */

public class FindCollegeActivity extends BaseActivity {
    @BindView(R.id.gridview)
    MyGridView gridview;
    @BindView(R.id.et_searchCollege)
    EditText et_searchCollege;
    private CollegeAdapter collegeAdapter;
    private Intent intent;
    private String provice,subProvice;
    private String[] collegeTypes={"综合","理工","财经","农林","医药","师范","体育","政法","艺术","民族","军事","语言"};
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        intent = getIntent();
        if (intent!=null){
            provice=intent.getStringExtra("provice");
            if (!TextUtils.isEmpty(provice)){
                if (provice.contains("新疆")||provice.contains("广西")||provice.contains("宁夏")){
                    subProvice=provice.substring(0,2);
                }else if(provice.contains("黑龙江")||provice.contains("内蒙古")){
                    subProvice=provice.substring(0,3);
                }else{
                    subProvice=provice.substring(0,2);
                }
            }
        }
    }
    @Override
    protected void initData() {
        collegeAdapter=new CollegeAdapter(this,collegeTypes);
        gridview.setAdapter(collegeAdapter);

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_find_college);
    }

    @Override
    protected void initListener() {
        et_searchCollege.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String keyWords=et_searchCollege.getText().toString().trim();
                if (!TextUtils.isEmpty(keyWords)){
                    jumpActivity(FindCollegeActivity.this,CollegeListActivity.class);
                }
                Intent intent=new Intent(FindCollegeActivity.this,CollegeListActivity.class);
                putValue(intent,"","","","","",keyWords);
                startActivity(intent);
                return false;
            }
        });
        //条目点击事件
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(FindCollegeActivity.this,CollegeListActivity.class);
                putValue(intent,"","","","",collegeTypes[i],"");
                startActivity(intent);
            }
        });
    }
    @Override
    protected void setStatusBarColor() {

    }
    @OnClick({R.id.iv_back,R.id.ll_allCollege,R.id.ll_collegeRank,R.id.ll_benSheng,R.id.ll_beiShangGaung,R.id.ll_quanGuo,
            R.id.ll_yiBen,R.id.ll_erBen,R.id.ll_sanBen,R.id.ll_zhuanke,R.id.ll_985,R.id.ll_211,R.id.ll_zhuoYue,R.id.ll_shengZhongDian})
    public void click(View view){
        Intent intent=null;
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            //全部大学
            case R.id.ll_allCollege:
                intent=new Intent(FindCollegeActivity.this,CollegeListActivity.class);
                putValue(intent,"","","","","","");
                startActivity(intent);
                break;
            //大学排名
            case R.id.ll_collegeRank:
              /*  intent=new Intent(FindCollegeActivity.this,CollegeListActivity.class);
                putValue(intent,"",subProvice,"","","","");
                startActivity(intent);*/
                break;
            //本省
            case R.id.ll_benSheng:
                intent=new Intent(FindCollegeActivity.this,CollegeListActivity.class);
                if (!TextUtils.isEmpty(subProvice)){
                    putValue(intent,"",subProvice,"","","","");
                }else{
                    putValue(intent,"","","","","","");
                }
                startActivity(intent);
                break;
            //北上广
            case R.id.ll_beiShangGaung:
                intent=new Intent(FindCollegeActivity.this,CollegeListActivity.class);
                putValue(intent,"","北上广","","","","");
                startActivity(intent);
                break;
            //全国
            case R.id.ll_quanGuo:
                intent=new Intent(FindCollegeActivity.this,CollegeListActivity.class);
                putValue(intent,"","","","","","");
                startActivity(intent);
                break;
            //一本
            case R.id.ll_yiBen:
                intent=new Intent(FindCollegeActivity.this,CollegeListActivity.class);
                putValue(intent,"","","本科","","","");
                startActivity(intent);
                break;
            //二本
            case R.id.ll_erBen:
                intent=new Intent(FindCollegeActivity.this,CollegeListActivity.class);
                putValue(intent,"","","本科","","","");
                startActivity(intent);
                break;
            //三本
            case R.id.ll_sanBen:
                intent=new Intent(FindCollegeActivity.this,CollegeListActivity.class);
                putValue(intent,"","","本科","","","");
                startActivity(intent);
                break;
            //专科
            case R.id.ll_zhuanke:
                intent=new Intent(FindCollegeActivity.this,CollegeListActivity.class);
                putValue(intent,"","","专科","","","");
                startActivity(intent);
                break;
            //985
            case R.id.ll_985:
                intent=new Intent(FindCollegeActivity.this,CollegeListActivity.class);
                putValue(intent,"","","","985","","");
                startActivity(intent);
                break;
            //211
            case R.id.ll_211:
                intent=new Intent(FindCollegeActivity.this,CollegeListActivity.class);
                putValue(intent,"","","","211","","");
                startActivity(intent);
                break;
            //卓越
            case R.id.ll_zhuoYue:
                //startActivity(new Intent(FindCollegeActivity.this,CollegeListActivity.class));
                break;
            //省重点
            case R.id.ll_shengZhongDian:
                //startActivity(new Intent(FindCollegeActivity.this,CollegeListActivity.class));
                break;
        }
    }
    private void putValue(Intent intent,String order,String area,String level,String is_type,String school_type,String keyword) {
       intent.putExtra("order",order);
       intent.putExtra("area",area);
       intent.putExtra("level",level);
       intent.putExtra("is_type",is_type);
       intent.putExtra("school_type",school_type);
       intent.putExtra("keyword",keyword);
    }
}
