package com.qianyi.shine.ui.college.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.fragment.entity.CollegeEntity;
import com.qianyi.shine.ui.college.adapter.RightProfessionAdapter;
import com.qianyi.shine.ui.college.entivity.ProfessionBean;

import java.util.ArrayList;
import java.util.List;

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
    private List<CollegeEntity> rightProfessList =  new ArrayList<>();




    @BindView(R.id.tv_introduce_profession) public TextView tv_introduce_profession;
    @BindView(R.id.tv_physique) public TextView tv_physique;
    @BindView(R.id.id_progress) public ProgressBar id_progress;
    @BindView(R.id.pro_nv) public TextView pro_nv;
    @BindView(R.id.pro_nan) public TextView pro_nan;


    public ProfessionBean.ProfessionData.ProfessionInfo.MajorInfo getMajorInfo() {
        return majorInfo;
    }

    public void setMajorInfo(ProfessionBean.ProfessionData.ProfessionInfo.MajorInfo majorInfo) {
        this.majorInfo = majorInfo;
        initMdata(majorInfo);
    }

    private void initMdata(ProfessionBean.ProfessionData.ProfessionInfo.MajorInfo majorInfo) {
        if(getMajorInfo()==null){
            Toast.makeText(mActivity, ""+getMajorInfo(), Toast.LENGTH_SHORT).show();
            return;
        }
        //专业介绍
        tv_introduce_profession.setText(getMajorInfo().getDescription());
        //体质要求
        if(TextUtils.isEmpty(getMajorInfo().getPhysique())){
            tv_physique.setText("无");
        }else {
            tv_physique.setText(getMajorInfo().getPhysique());
        }

        //男女比例
        if(getMajorInfo().getMale()!=null&&getMajorInfo().getMale()!=""){
            id_progress.setProgress(Integer.parseInt(getMajorInfo().getMale()));
            pro_nan.setText(getMajorInfo().getMale());
            pro_nv.setText(getMajorInfo().getFemalte());
        }

        //对口专业
        for (int i = 0; i <getMajorInfo().getMajor().size() ; i++) {
            CollegeEntity collegeEntity = new CollegeEntity(0,"",getMajorInfo().getMajor().get(i).getTitle(),getMajorInfo().getMajor().get(i).getDscr());
            rightProfessList.add(collegeEntity);

        }
        professionAdapter.notifyDataSetChanged();
    }

    public ProfessionBean.ProfessionData.ProfessionInfo.MajorInfo majorInfo;


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
        professionAdapter=new RightProfessionAdapter(getActivity(),rightProfessList);
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
