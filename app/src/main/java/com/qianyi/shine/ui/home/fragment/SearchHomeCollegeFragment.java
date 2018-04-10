package com.qianyi.shine.ui.home.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.ui.home.adapter.HomeSearchCollegeAdapter;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/10.
 */

public class SearchHomeCollegeFragment extends BaseFragment {
    private HomeSearchCollegeAdapter collegeAdapter;
    @BindView(R.id.mList)
    public ListView mList;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_search_homecollege,null);
    }

    @Override
    protected void initViews() {
        collegeAdapter = new HomeSearchCollegeAdapter(getActivity());
        mList.setAdapter(collegeAdapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
