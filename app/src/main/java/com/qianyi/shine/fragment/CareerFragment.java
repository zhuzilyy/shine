package com.qianyi.shine.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.ui.account.activity.WebviewActivity;
import com.qianyi.shine.ui.career_planning.activity.SuitableForMyProfessionActivity;
import com.qianyi.shine.ui.home.activity.CareerAndMajorActivity;
import com.qianyi.shine.ui.home.activity.EmploymentActivity;
import com.qianyi.shine.ui.home.activity.FindMajorActivity;

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
    @BindView(R.id.re_Professionalselection)
    public RelativeLayout re_Professionalselection;
    @BindView(R.id.re_professional_employment)
    public RelativeLayout re_professional_employment;
    @BindView(R.id.re_benke10)
    public RelativeLayout re_benke10;
    @BindView(R.id.re_zhuanke_10)
    public RelativeLayout re_zhuanke_10;

    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_careerPlaning = inflater.inflate(R.layout.fragment_career_planing, null);
        ButterKnife.bind(this, view_careerPlaning);
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

    @OnClick({R.id.goText_tv,R.id.re_Professionalselection,R.id.re_professional_employment,R.id.re_benke10,R.id.re_zhuanke_10})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goText_tv:
                //去测试
                getActivity().startActivity(new Intent(getActivity(), SuitableForMyProfessionActivity.class));
                break;
            case R.id.re_Professionalselection:
                //按职业选专业
                getActivity().startActivity(new Intent(getActivity(), CareerAndMajorActivity.class));
                break;
            case R.id.re_professional_employment:
                //看职业就业
                getActivity().startActivity(new Intent(getActivity(), EmploymentActivity.class));
                break;
            case R.id.re_benke10:
                //本科毕业生从事的10个高薪工作
                Intent intent = new Intent(getActivity(), WebviewActivity.class);
                intent.putExtra("title", "本科毕业生从事的10个高薪工作");
                intent.putExtra("url", "http://www.baidu.com");
                startActivity(intent);
                break;
            case R.id.re_zhuanke_10:
                //专科科毕业生从事的10个高薪工作
                Intent intent_02 = new Intent(getActivity(), WebviewActivity.class);
                intent_02.putExtra("title", "专科科毕业生从事的10个高薪工作");
                intent_02.putExtra("url", "http://www.baidu.com");
                startActivity(intent_02);
                break;

            default:
                break;


        }

    }
}
