package com.qianyi.shine.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;


/**
 * Created by NEUNB on 2018/3/19.
 */

public class CareerFragment extends BaseFragment {
    private View view_careerPlaning;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_careerPlaning=inflater.inflate(R.layout.fragment_career_planing,null);
        return view_careerPlaning;
    }
    @Override
    protected void initViews() {



    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
