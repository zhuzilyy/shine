package com.qianyi.shine.ui.home.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.ui.home.adapter.HomeSearchProfessionAdapter;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/10.
 */

public class SearchHomeProfessionFragment extends BaseFragment {
    private HomeSearchProfessionAdapter professionAdapter;
    @BindView(R.id.mList)
    public ListView mList;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_search_homeprofession,null);
    }

    @Override
    protected void initViews() {
        professionAdapter = new HomeSearchProfessionAdapter(getActivity());
        mList.setAdapter(professionAdapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
