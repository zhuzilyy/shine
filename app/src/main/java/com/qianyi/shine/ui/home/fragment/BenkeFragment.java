package com.qianyi.shine.ui.home.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.ui.home.adapter.EmploymentAdapter;

import butterknife.BindView;

/**
 * Created by NEUNB on 2018/4/2.
 */

public class BenkeFragment extends BaseFragment {
    @BindView(R.id.listView)
    ListView listView;
    private View view_benKe;
    private EmploymentAdapter employmentAdapter;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_benKe=inflater.inflate(R.layout.fragment_benke,null);
        return view_benKe;
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

    }
}
