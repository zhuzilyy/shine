package com.qianyi.shine.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.ui.mine.activity.HelpCenterActivity;
import com.qianyi.shine.ui.mine.activity.SettingActivity;
import com.qianyi.shine.ui.mine.activity.VipActivity;

import butterknife.OnClick;


/**
 * Created by NEUNB on 2018/3/19.
 */

public class MineFragment extends BaseFragment {
    private View view_mine;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_mine=inflater.inflate(R.layout.fragment_mine,null);
        return view_mine;
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
    @OnClick({R.id.rl_setting,R.id.rl_helpCenter,R.id.rl_vip})
    public void click(View view){
        Intent intent=null;
        switch (view.getId()){
            case R.id.rl_setting:
                intent=new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_helpCenter:
                intent=new Intent(getActivity(), HelpCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_vip:
                intent=new Intent(getActivity(), VipActivity.class);
                startActivity(intent);
                break;
        }
    }
}
