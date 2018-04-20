package com.qianyi.shine.ui.home.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.ui.account.activity.WebviewActivity;
import com.qianyi.shine.ui.home.adapter.EmploymentAdapter;

import butterknife.BindView;

/**
 * Created by NEUNB on 2018/4/2.
 */

public class ZhuankeFragment extends BaseFragment {
    @BindView(R.id.listView)
    ListView listView;
    private View view_zhuanKe;
    private EmploymentAdapter employmentAdapter;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_zhuanKe=inflater.inflate(R.layout.fragment_zhuanke,null);
        return view_zhuanKe;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        employmentAdapter=new EmploymentAdapter(getActivity());
        listView.setAdapter(employmentAdapter);
    }

    @Override
    protected void initListener() {
        //点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), WebviewActivity.class);
                intent.putExtra("title","详情");
                intent.putExtra("url","https://home.firefoxchina.cn/");
                startActivity(intent);
            }
        });
    }
}
