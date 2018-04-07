package com.qianyi.shine.ui.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.ui.college.adapter.OccupationRightProfessionAdapter;
import com.qianyi.shine.ui.college.adapter.RightProfessionAdapter;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/7.
 */

public class OccupationBaseInfoFragment extends BaseFragment {
    //对口职业
    @BindView(R.id.rightprofession_rv)
    public RecyclerView rightprofession_rv;
    private OccupationRightProfessionAdapter adapter;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_occupation_baseinfo,null);
    }

    @Override
    protected void initViews() {
        //对口职业
        rightprofession_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new OccupationRightProfessionAdapter(getActivity(),null);
        rightprofession_rv.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
