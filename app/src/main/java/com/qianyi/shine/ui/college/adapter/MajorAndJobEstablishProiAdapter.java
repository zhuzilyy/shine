package com.qianyi.shine.ui.college.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qianyi.shine.R;
import com.qianyi.shine.ui.home.activity.PriorityCollegeDetailsActivity;
import com.qianyi.shine.ui.home.activity.PriorityProfessionalDetailsActivity;
import com.qianyi.shine.ui.home.bean.ProfessionMajorJobPriorBean;
import com.qianyi.shine.ui.home.view.MyListView;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */

public class MajorAndJobEstablishProiAdapter extends BaseQuickAdapter<ProfessionMajorJobPriorBean.ProfessionMajorJobPriorData.ProfessionMajorJobPriorInfo.ProfessionMajorJobPriorList, BaseViewHolder> {
    private Context mContext;
    private EstablishProiAdapter2 adapter2;

    public MajorAndJobEstablishProiAdapter(Context MContext) {
        super(R.layout.priority_major_and_job_profession_item);
        this.mContext =MContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ProfessionMajorJobPriorBean.ProfessionMajorJobPriorData.ProfessionMajorJobPriorInfo.ProfessionMajorJobPriorList item) {
        MyListView lv=helper.getView(R.id.lv_major_job);
        final List<ProfessionMajorJobPriorBean.ProfessionMajorJobPriorData.ProfessionMajorJobPriorInfo.ProfessionMajorJobPriorList.EnrollArr> enroll_arr = item.getEnroll_arr();
        MajorAndJobAdapter adapter =new MajorAndJobAdapter(mContext,enroll_arr);
        lv.setAdapter(adapter);


        TextView tv_collegeName=helper.getView(R.id.tv_collegeName);
        TextView tv_score = helper.getView(R.id.tv_score);
        TextView tv_luqu = helper.getView(R.id.tv_luqu);


        tv_collegeName.setText(item.getName());
        tv_score.setText(item.getLevel());
        tv_luqu.setText("");

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, PriorityCollegeDetailsActivity.class);
                intent.putExtra("collegeName",item.getName());
                intent.putExtra("luqulv",enroll_arr.get(position).getRate().getRate());
                intent.putExtra("collegeid",item.getId());

                mContext.startActivity(intent);
            }
        });





    }



}
