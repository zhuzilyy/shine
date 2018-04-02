package com.qianyi.shine.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.ui.career_planning.activity.SuitableForMyProfessionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by NEUNB on 2018/3/19.
 */

public class CareerFragment extends BaseFragment implements View.OnClickListener {
    private View view_careerPlaning;
    @BindView(R.id.goText_tv)
    public TextView goText_tv;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_careerPlaning=inflater.inflate(R.layout.fragment_career_planing,null);
        ButterKnife.bind(this,view_careerPlaning);
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
    @OnClick({R.id.goText_tv})
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.goText_tv:
                getActivity().startActivity(new Intent(getActivity(), SuitableForMyProfessionActivity.class));
            break;

            default:
            break;


        }

    }
}
