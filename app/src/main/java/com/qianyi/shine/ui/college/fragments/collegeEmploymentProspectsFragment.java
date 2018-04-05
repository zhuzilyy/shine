package com.qianyi.shine.ui.college.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.fragment.entity.CollegeEntity;
import com.qianyi.shine.ui.college.adapter.Prospect_MoneyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/31.
 */

public class collegeEmploymentProspectsFragment extends BaseFragment {
    @BindView(R.id.rv_money)
    public RecyclerView rv_money;
    private Prospect_MoneyAdapter moneyAdapter;
    private List<CollegeEntity> list;


    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        View layoutRes= inflater.inflate(R.layout.fragment_college_employmentprospects,null);
        return layoutRes;
    }

    @Override
    protected void initViews() {

        list=new ArrayList<>();
        list.add(new CollegeEntity(R.mipmap.icon01,"","111111",""));
        list.add(new CollegeEntity(R.mipmap.icon01,"","111111",""));
        list.add(new CollegeEntity(R.mipmap.icon01,"","111111",""));
        list.add(new CollegeEntity(R.mipmap.icon01,"","111111",""));
        list.add(new CollegeEntity(R.mipmap.icon01,"","111111",""));
        list.add(new CollegeEntity(R.mipmap.icon01,"","111111",""));
        list.add(new CollegeEntity(R.mipmap.icon01,"","111111",""));
        list.add(new CollegeEntity(R.mipmap.icon01,"","111111",""));
        list.add(new CollegeEntity(R.mipmap.icon01,"","111111",""));

        rv_money.setNestedScrollingEnabled(false);
        rv_money.setLayoutManager(new LinearLayoutManager(getActivity()));
        moneyAdapter=new Prospect_MoneyAdapter(getActivity(),list);
        rv_money.setAdapter(moneyAdapter);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
