package com.qianyi.shine.ui.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseActivity;
import com.qianyi.shine.ui.account.activity.WebviewActivity;
import com.qianyi.shine.ui.mine.adapter.MessageAdapter;
import com.qianyi.shine.ui.mine.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by NEUNB on 2018/4/4.
 */

public class MessageActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rv_message)
    public RecyclerView rv_message;
    @BindView(R.id.swipeLayout)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private MessageAdapter messageAdapter;
    List<MessageBean> infoList;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;
    @Override
    protected void initViews() {
        BaseActivity.addActivity(this);
        tv_title.setText("消息");
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        rv_message.setLayoutManager(new LinearLayoutManager(this));
        infoList=new ArrayList<>();
        //上拉加载
        initAdapter();
        //下拉刷新
        initRefreshLayout();
        mSwipeRefreshLayout.setRefreshing(true);
        refresh();
    }

    @Override
    protected void initData() {
        for (int i = 0; i <10 ; i++) {
            MessageBean messageBean=new MessageBean();
            infoList.add(messageBean);
        }
    }

    @Override
    protected void getResLayout() {
        setContentView(R.layout.activity_message);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setStatusBarColor() {

    }
    private void initAdapter() {
        messageAdapter = new MessageAdapter(R.layout.item_message, infoList);
        messageAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        });
        messageAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        rv_message.setAdapter(messageAdapter);

        rv_message.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                //Toast.makeText(MessageActivity.this, Integer.toString(position), Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MessageActivity.this, WebviewActivity.class);
                intent.putExtra("title","消息详情");
                intent.putExtra("url","http://fanyi.youdao.com/");
                startActivity(intent);
            }
        });
    }
    //刷新
    private void refresh() {
        mNextRequestPage = 1;
        messageAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        apiHome.refresh("http://www.baidu.com", mNextRequestPage, new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                Log.i("ppp", "131" + s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(true, infoList);
                        messageAdapter.setEnableLoadMore(true);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                Log.i("ppp", "132" + e);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(true, infoList);
                        messageAdapter.setEnableLoadMore(true);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }
    //加载
    private void loadMore() {

        apiHome.loadMore("http://www.baidu.com", mNextRequestPage, new com.qianyi.shine.callbcak.RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(false, infoList);
                    }
                });

            }

            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        messageAdapter.loadMoreFail();
                        Toast.makeText(MessageActivity.this, "网络错误", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }
    private void setData(boolean isRefresh, List data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            messageAdapter.setNewData(data);
        } else {
            if (size > 0) {
                messageAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            messageAdapter.loadMoreEnd(isRefresh);
            Toast.makeText(MessageActivity.this, "第一页如果不够一页就不显示没有更多数据布局", Toast.LENGTH_SHORT).show();
        } else {
            messageAdapter.loadMoreComplete();
        }
    }
    @OnClick({R.id.iv_back})
    public void click(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
