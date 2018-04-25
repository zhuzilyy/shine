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
    private String[] collegeTypes;
    public CollegeAdapter(Context context,String[] collegeTypes) {
        this.context = context;
        this.collegeTypes = collegeTypes;
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
