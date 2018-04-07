package com.qianyi.shine.ui.home.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;

/**
 * Created by Administrator on 2018/4/6.
 */

public class MajorBenkeFragment extends BaseFragment {
    private View view_benke;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_benke=inflater.inflate(R.layout.fragment_major_benke,null);
        return view_benke;
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
