package com.qianyi.shine.ui.college.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qianyi.shine.R;
import com.qianyi.shine.ui.college.entivity.CollegeScoreBean;
import com.qianyi.shine.ui.home.bean.PrefessionBean;

/**
 * Created by Administrator on 2018/4/4.
 */

public class ScoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private Context mContext;
    private PrefessionBean.PrefessionData.PrefessionInfo allRecord;//数据

    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);


    }

    private ScoreAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(ScoreAdapter.OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    //适配器初始化
    public ScoreAdapter(Context context, PrefessionBean.PrefessionData.PrefessionInfo allRecord) {
        mContext = context;
        this.allRecord = allRecord;
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
            ).inflate(R.layout.lay_score_item, parent,
                    false);//这个布局就是一个imageview用来显示图片
            ScoreAdapter.MyViewHolder holder = new ScoreAdapter.MyViewHolder(view);



            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //将数据与item视图进行绑定，如果是MyViewHolder就加载网络图片，如果是MyViewHolder2就显示页数
        if (holder instanceof ScoreAdapter.MyViewHolder) {

            if(0==position){
                //2017
                ((MyViewHolder) holder).tv_nian.setText("2017");
                ((MyViewHolder) holder).tv_renshu.setText(allRecord.getScoreinfo().getRecord_2017().getRenshu());
                ((MyViewHolder) holder).tv_gao.setText(allRecord.getScoreinfo().getRecord_2017().getGaofen());
                ((MyViewHolder) holder).tv_di.setText(allRecord.getScoreinfo().getRecord_2017().getDifen());
                ((MyViewHolder) holder).tv_xiancha.setText(allRecord.getScoreinfo().getRecord_2017().getH());
                ((MyViewHolder) holder).tv_zuidiweici.setText(allRecord.getScoreinfo().getRecord_2017().getWeici());

            }else if(1==position){
                //2016
                ((MyViewHolder) holder).tv_nian.setText("2016");
                ((MyViewHolder) holder).tv_renshu.setText(allRecord.getScoreinfo().getRecord_2016().getRenshu());
                ((MyViewHolder) holder).tv_gao.setText(allRecord.getScoreinfo().getRecord_2016().getGaofen());
                ((MyViewHolder) holder).tv_di.setText(allRecord.getScoreinfo().getRecord_2016().getDifen());
                ((MyViewHolder) holder).tv_xiancha.setText(allRecord.getScoreinfo().getRecord_2016().getH());
                ((MyViewHolder) holder).tv_zuidiweici.setText(allRecord.getScoreinfo().getRecord_2016().getWeici());

            }else if(2==position){
                //2015
                ((MyViewHolder) holder).tv_nian.setText("2015");
                ((MyViewHolder) holder).tv_renshu.setText(allRecord.getScoreinfo().getRecord_2015().getRenshu());
                ((MyViewHolder) holder).tv_gao.setText(allRecord.getScoreinfo().getRecord_2015().getGaofen());
                ((MyViewHolder) holder).tv_di.setText(allRecord.getScoreinfo().getRecord_2015().getDifen());
                ((MyViewHolder) holder).tv_xiancha.setText(allRecord.getScoreinfo().getRecord_2015().getH());
                ((MyViewHolder) holder).tv_zuidiweici.setText(allRecord.getScoreinfo().getRecord_2015().getWeici());

            }


            // Picasso.with(mContext).load(datas.get(position).getUrl()).into(((MyViewHolder) holder).iv);//加载网络图片
            if(mOnItemClickListener!=null){

            }

        }

    }

    @Override
    public int getItemCount() {
        return 3;//获取数据的个数
    }





    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_nian;
        private TextView tv_renshu;
        private TextView tv_gao;
        private TextView tv_di;
        private TextView tv_xiancha;
        private TextView tv_zuidiweici;



        public MyViewHolder(View view) {
            super(view);
            tv_nian = view.findViewById(R.id.tv_nian);
            tv_renshu = view.findViewById(R.id.tv_rennshu);
            tv_gao = view.findViewById(R.id.tv_gao);
            tv_di = view.findViewById(R.id.tv_di);
            tv_xiancha = view.findViewById(R.id.tv_xiancha);
            tv_zuidiweici = view.findViewById(R.id.tv_zuidiweici);
        }
    }
}
