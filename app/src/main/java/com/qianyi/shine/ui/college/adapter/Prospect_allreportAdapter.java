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
import com.qianyi.shine.ui.home.bean.MajorListInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4.
 */

public class Prospect_allreportAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context mContext;
    private List<MajorListInfo> datas;//数据
    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    private Prospect_allreportAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(Prospect_allreportAdapter.OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    //适配器初始化
    public Prospect_allreportAdapter(Context context, List<MajorListInfo> datas) {
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
            ).inflate(R.layout.lay_suitableme_item, parent,
                    false);//这个布局就是一个imageview用来显示图片
            Prospect_allreportAdapter.MyViewHolder holder = new Prospect_allreportAdapter.MyViewHolder(view);

            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //将数据与item视图进行绑定，如果是MyViewHolder就加载网络图片，如果是MyViewHolder2就显示页数
        if (holder instanceof Prospect_allreportAdapter.MyViewHolder) {
            ((MyViewHolder) holder).tv_name.setText(datas.get(position).getMajor_name());
            ((MyViewHolder) holder).tv_salary.setText("毕业5年后的月薪:￥"+datas.get(position).getSalary5());
            ((MyViewHolder) holder).tv_zhineng.setText("职业方向介绍:"+datas.get(position).getZhineng());
            // Picasso.with(mContext).load(datas.get(position).getUrl()).into(((MyViewHolder) holder).iv);//加载网络图片
          /*  if(mOnItemClickListener!=null){
                ((Prospect_allreportAdapter.MyViewHolder) holder).pic_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //把条目的位置回调回去
                        mOnItemClickListener.onItemClick(position);
                    }
                });
            }*/

        }

    }

    @Override
    public int getItemCount() {
        return 7;//获取数据的个数
    }
    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name,tv_salary,tv_zhineng;
        public MyViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_salary = view.findViewById(R.id.tv_salary);
            tv_zhineng = view.findViewById(R.id.tv_zhineng);
        }
    }
}
