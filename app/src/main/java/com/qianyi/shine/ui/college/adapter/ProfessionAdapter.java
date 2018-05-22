package com.qianyi.shine.ui.college.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyi.shine.R;
import com.qianyi.shine.fragment.entity.TestEntity;
import com.qianyi.shine.ui.career_planning.entity.SuitableForMeEntity;
import com.qianyi.shine.ui.home.bean.CollegeMajorInfo;
import com.qianyi.shine.ui.home.bean.UniversityMajorInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */

public class ProfessionAdapter extends BaseQuickAdapter<UniversityMajorInfo, BaseViewHolder> {
    public ProfessionAdapter(int layoutResId, @Nullable List<UniversityMajorInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UniversityMajorInfo item) {
        helper.setText(R.id.tv_majorName,item.getMajor_name());
        if (item.getSalary5().contains("信息统计中")){
            helper.setText(R.id.tv_salary,"毕业5年后的月薪￥:---");
        }else{
            helper.setText(R.id.tv_salary,"毕业5年后的月薪￥:"+item.getSalary5());
        }
        if (item.getZhineng().contains("信息统计中")){
            helper.setText(R.id.tv_zhineng,"职业方向介绍:---");
        }else{
            helper.setText(R.id.tv_zhineng,"职业方向介绍:"+item.getZhineng());
        }

    }
}
