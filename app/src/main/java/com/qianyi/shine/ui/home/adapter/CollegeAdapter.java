package com.qianyi.shine.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qianyi.shine.R;

/**
 * Created by NEUNB on 2018/4/2.
 */

public class CollegeAdapter extends BaseAdapter {
    private Context context;
    private String[] collegeTypes={"综合","理工","财经","农林","医药","师范","体育","政法","艺术","民族","军事","语言"};
    public CollegeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return collegeTypes.length;
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
        view=LayoutInflater.from(context).inflate(R.layout.item_college_type,null);
        TextView tv_collegeType=view.findViewById(R.id.tv_collegeType);
        tv_collegeType.setText(collegeTypes[i]);
        return view;
    }
}
