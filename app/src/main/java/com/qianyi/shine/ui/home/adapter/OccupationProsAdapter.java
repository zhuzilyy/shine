package com.qianyi.shine.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.ui.home.bean.SalaryMarginInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/27.
 */

public class OccupationProsAdapter extends BaseAdapter {
    private ArrayList<String> yearList,salaryList;
    private Context context;
    public OccupationProsAdapter(ArrayList<String> yearList, ArrayList<String> salaryList, Context context) {
        this.yearList = yearList;
        this.salaryList = salaryList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return yearList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view==null){
            view=LayoutInflater.from(context).inflate(R.layout.item_occupation_pros,null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tv_year.setText("毕业"+yearList.get(i)+"年");
        viewHolder.tv_salary.setText("薪资"+salaryList.get(i)+"元");
        return view;
    }
    class ViewHolder{
        @BindView(R.id.tv_year)
        TextView tv_year;
        @BindView(R.id.tv_salary)
        TextView tv_salary;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
