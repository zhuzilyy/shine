package com.qianyi.shine.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;


/**
 * Created by NEUNB on 2018/3/19.
 */

public class HomeFragment extends BaseFragment {
    private View view_home;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_home=inflater.inflate(R.layout.fragment_home,null);
        return view_home;
    }

    @Override
    protected void initViews() {





        //副经理说金粉世家解放军是否积分设计费
        /***
         * 就方式来积分说了
         */
        add(5,3);//jvksjoijoijfohfoihfiuf
        /**
         *
         */

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    public int add(int a,int b){
        return a+b;
    }
}
