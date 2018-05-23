package com.qianyi.shine.ui.home.activity;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.account.view.MEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/23.
 */

public class SearchActivity extends BaseActivity{
    @BindView(R.id.ed_search)
    public MEditText ed_search;
    @Override
    protected void initViews() {
        final EditText editText = ed_search.getEditext();
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(!TextUtils.isEmpty(v.getText())){
                    boolean isFirst=false;
                    String keyWord=v.getText().toString().trim();

                    //查询数据，显示内容

                }
                return false;
            }
        });
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_search);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    @OnClick(R.id.iv_back)
    public void click(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
