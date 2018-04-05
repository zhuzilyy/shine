package com.qianyi.shine.ui.college.fragments;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.fragment.adapter.GridAdapter;
import com.qianyi.shine.fragment.entity.CollegeEntity;
import com.qianyi.shine.ui.account.activity.WebviewActivity;
import com.qianyi.shine.ui.college.adapter.PicCollegeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/31.
 */

public class collegeIntroductionFragment extends BaseFragment implements View.OnClickListener{
    @BindView(R.id.PicCollege_rv)
    public RecyclerView PicCollege_rv;
    private PicCollegeAdapter adapter;
    //空数据
    private List<CollegeEntity> listCollege=new ArrayList<>();
    //食宿条件
    @BindView(R.id.Accommodation_re)
    public RelativeLayout Accommodation_re;
    //奖学金设置
    @BindView(R.id.Scholarship_re)
    public RelativeLayout Scholarship_re;

    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        View layoutRes= inflater.inflate(R.layout.fragment_college_introduction,null);
        return layoutRes;
    }

    @Override
    protected void initViews() {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false);
        PicCollege_rv.setLayoutManager(gridLayoutManager);
        PicCollege_rv.setFocusable(false);
        adapter=new PicCollegeAdapter(getActivity(),listCollege);
        PicCollege_rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new PicCollegeAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(mActivity, "pp"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
    @OnClick({R.id.Accommodation_re,R.id.Scholarship_re})
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Accommodation_re:
                Intent intent_Accommodation = new Intent(getActivity(), WebviewActivity.class);
                intent_Accommodation.putExtra("title","住宿条件");
                intent_Accommodation.putExtra("url","http://www.baidu.com");
                getActivity().startActivity(intent_Accommodation);
                break;
            case R.id.Scholarship_re:
                Intent intent_Scholarship = new Intent(getActivity(), WebviewActivity.class);
                intent_Scholarship.putExtra("title","奖学金设置");
                intent_Scholarship.putExtra("url","http://www.baidu.com");
                getActivity().startActivity(intent_Scholarship);
                break;
            default:
                break;
        }
    }
}
