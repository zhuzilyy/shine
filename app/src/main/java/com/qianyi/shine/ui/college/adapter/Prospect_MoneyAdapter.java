package com.qianyi.shine.ui.college.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.fragment.entity.CollegeEntity;
import com.qianyi.shine.ui.home.bean.SalaryMajorInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class Prospect_MoneyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context mContext;
    private List<SalaryMajorInfo> datas;//数据

    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);


    }

    private Prospect_MoneyAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(Prospect_MoneyAdapter.OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    //适配器初始化
    public Prospect_MoneyAdapter(Context context, List<SalaryMajorInfo> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        //判断item类别，是图还是显示页数（图片有URL）
        if (1==1) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据item类别加载不同ViewHolder
        if (viewType == 0) {
            View view = LayoutInflater.from(mContext
            ).inflate(R.layout.lay_hightmoney_item, parent,
                    false);//这个布局就是一个imageview用来显示图片
            Prospect_MoneyAdapter.MyViewHolder holder = new Prospect_MoneyAdapter.MyViewHolder(view);
            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //将数据与item视图进行绑定，如果是MyViewHolder就加载网络图片，如果是MyViewHolder2就显示页数
        if (holder instanceof Prospect_MoneyAdapter.MyViewHolder) {
            ((MyViewHolder) holder).tv_index.setText((position+1)+"");
            ((MyViewHolder) holder).tv_name.setText((datas.get(position).getMajor_name()));
            ((MyViewHolder) holder).tv_salary.setText(datas.get(position).getSalary5());
            // Picasso.with(mContext).load(datas.get(position).getUrl()).into(((MyViewHolder) holder).iv);//加载网络图片
            if(mOnItemClickListener!=null){
                ((Prospect_MoneyAdapter.MyViewHolder) holder).pic_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //把条目的位置回调回去
                        mOnItemClickListener.onItemClick(position);
                    }
                });
            }

        }

    }

    @Override
    public int getItemCount() {
        return this.datas.size();//获取数据的个数
    }

    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView pic_item;
        private TextView tv_name,tv_index,tv_salary;
        public MyViewHolder(View view) {
            super(view);
            tv_name=view.findViewById(R.id.tv_name);
            tv_index=view.findViewById(R.id.tv_index);
            tv_salary=view.findViewById(R.id.tv_salary);
        }
    }
}
