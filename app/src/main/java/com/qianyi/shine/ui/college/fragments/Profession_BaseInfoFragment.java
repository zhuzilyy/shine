package com.qianyi.shine.ui.college.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.ui.college.adapter.RightProfessionAdapter;

import butterknife.BindView;
import me.gujun.android.taggroup.TagGroup;

/**
 * Created by Administrator on 2018/4/3.
 */

public class Profession_BaseInfoFragment extends BaseFragment {
    @BindView(R.id.tag_profession)
    public TagGroup tag_profession;
    String[] tags={"aaaa","金佛山见覅","方法","返回见覅文件覅","莲富大厦金粉世家哦i","发送"};
    //对口职业
    @BindView(R.id.rightprofession_rv)
    public RecyclerView rightprofession_rv;
    private RightProfessionAdapter professionAdapter;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {

        return inflater.inflate(R.layout.fragment_profession_baseinfo,null);
    }

    @Override
    protected void initViews() {
        tag_profession.setTags(tags);
        tag_profession.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                Toast.makeText(getActivity(), tag+"", Toast.LENGTH_SHORT).show();
            }
        });

        //对口职业
        rightprofession_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        professionAdapter=new RightProfessionAdapter(getActivity(),null);
        rightprofession_rv.setAdapter(professionAdapter);
        //点击事件有问题

//        professionAdapter.setOnItemClickListener(new RightProfessionAdapter.OnRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Toast.makeText(mActivity, "经济学", Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
