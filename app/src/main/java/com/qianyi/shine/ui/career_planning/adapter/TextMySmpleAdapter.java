package com.qianyi.shine.ui.career_planning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.ui.career_planning.activity.InterestResultActivity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24.
 */

public class TextMySmpleAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> stringList;
    private int type;

    public TextMySmpleAdapter(Context mContext, List<String> stringList,int type) {
        this.mContext = mContext;
        this.stringList = stringList;
        this.type = type;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.lay_item_tv,null);
        TextView textView = convertView.findViewById(R.id.item_tv);
        if(0 == type){
            textView.setText(stringList.get(position));
        }else {
            textView.setText((position+1)+". "+stringList.get(position));
        }

        return convertView;
    }
}
