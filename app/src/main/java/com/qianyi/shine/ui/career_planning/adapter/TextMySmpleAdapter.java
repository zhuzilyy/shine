package com.qianyi.shine.ui.career_planning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qianyi.shine.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24.
 */

public class TextMySmpleAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> stringList;

    public TextMySmpleAdapter(Context mContext, List<String> stringList) {
        this.mContext = mContext;
        this.stringList = stringList;
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
        textView.setText((position+1)+". "+stringList.get(position));
        return convertView;
    }
}
