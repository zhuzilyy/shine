package com.qianyi.shine.ui.account.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qianyi.shine.MainActivity;
import com.qianyi.shine.R;
import com.qianyi.shine.ui.account.activity.LoginActivity;
import com.qianyi.shine.ui.account.activity.WelcomeActiity;
import com.qianyi.shine.ui.account.bean.LoginBean;
import com.qianyi.shine.utils.Utils;

import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */

public class MyPagerAdapter extends PagerAdapter {
    private List<View> imageViews;
    private Context context;
    public MyPagerAdapter(Context context, List<View> imageViews) {
        this.context=context;
        this.imageViews = imageViews;
    }
    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView(imageViews.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }
    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        //将xml布局转换为view对象
        container.addView(imageViews.get(position));
       /* imageViews.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        View view = imageViews.get(2);
        view.findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //自动登录
                LoginBean.LoginData.LoginInfo user= Utils.readUser(context);
                if( user != null){
                    if(!TextUtils.isEmpty(user.getId())){
                        //登录过，进入主界面
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }
                }else{
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }
                ((WelcomeActiity)context).finish();
            }
        });

        return imageViews.get(position);
    }
}
