package com.qianyi.shine.ui.home.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.account.view.MEditText;
import com.qianyi.shine.ui.college.adapter.MyPageAdapter;
import com.qianyi.shine.ui.college.fragments.Profession_BaseInfoFragment;
import com.qianyi.shine.ui.college.fragments.Profession_EstablishmentFragment;
import com.qianyi.shine.ui.college.fragments.Profession_ProspectsFragment;
import com.qianyi.shine.ui.home.fragment.SearchHomeCollegeFragment;
import com.qianyi.shine.ui.home.fragment.SearchHomeProfessionFragment;
import com.qianyi.shine.utils.SPUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/10.
 */

public class HomeSearchActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.ed_search)
    public MEditText ed_search;
    @BindView(R.id.tablayout)
    public TabLayout tab;
    @BindView(R.id.view_pager)
    public ViewPager viewPager;
    public MyPageAdapter myPageAdapter;
    @BindView(R.id.back)
    public ImageView back;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        myPageAdapter = new MyPageAdapter(getSupportFragmentManager());
        ArrayList<Fragment> datas = new ArrayList<Fragment>();
        datas.add(new SearchHomeCollegeFragment());
        datas.add(new SearchHomeProfessionFragment());
        myPageAdapter.setData(datas);

        ArrayList<String> titles = new ArrayList<String>();
        titles.add("大学");
        titles.add("专业");
        myPageAdapter.setTitles(titles);
        viewPager.setAdapter(myPageAdapter);
        // 将ViewPager与TabLayout相关联
        tab.setupWithViewPager(viewPager);
        final EditText editText = ed_search.getEditext();
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if(!TextUtils.isEmpty(v.getText())){
                        String keyWord=v.getText().toString().trim();
                        SPUtils.put(HomeSearchActivity.this,"keyWord",keyWord);
                        tab.setVisibility(View.VISIBLE);
                        viewPager.setVisibility(View.VISIBLE);
                        SearchHomeCollegeFragment searchHomeCollegeFragment=new SearchHomeCollegeFragment();
                        searchHomeCollegeFragment.setKeyWord(keyWord);
                        //查询数据，显示内容
                        Intent intent=new Intent();
                        sendBroadcast(intent);
                        intent.putExtra("keyWord",v.getText().toString().trim());
                        intent.setAction("com.action.search");
                    }
                return false;
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(editText.getText().toString().trim())){
                    tab.setVisibility(View.GONE);
                    viewPager.setVisibility(View.GONE);
                }
            }
        });



    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_home_search);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }

    @OnClick({R.id.back})
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back:
                finish();
                break;
            default:
                break;
        }

    }
}
