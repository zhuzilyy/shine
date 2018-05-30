package com.qianyi.shine.ui.home.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.ui.account.activity.OccupationWebviewActivity;
import com.qianyi.shine.ui.account.activity.WebviewActivity;
import com.qianyi.shine.ui.home.adapter.EmploymentAdapter;
import com.qianyi.shine.ui.home.bean.ArticleInfo;
import com.qianyi.shine.ui.home.bean.EmploymentBean;
import com.qianyi.shine.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by NEUNB on 2018/4/2.
 */

public class BenkeFragment extends BaseFragment {
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.no_internet_rl)
    RelativeLayout no_internet_rl;
    @BindView(R.id.no_data_rl)
    RelativeLayout no_data_rl;
    private View view_benKe;
    private EmploymentAdapter employmentAdapter;
    private CustomLoadingDialog customLoadingDialog;
    private  List<ArticleInfo> articleList;
    private Intent intent;
    private String tag,type;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_benKe=inflater.inflate(R.layout.fragment_benke,null);
        return view_benKe;
    }

    @Override
    protected void initViews() {
        customLoadingDialog=new CustomLoadingDialog(getActivity());
        intent=getActivity().getIntent();
        if (intent!=null){
            tag=intent.getStringExtra("tag");
        }
        if (tag.equals("homePage")){
            type="2";
        }else if (tag.equals("career")){
            type="6";
        }
    }
    @Override
    protected void initData() {
        getData();
    }

    private void getData() {
        if (Utils.hasInternet()){
            customLoadingDialog.show();
            apiHome.getEmployerList(apiConstant.EMPLOYER_BENKE_LIST, type, new RequestCallBack<String>() {
                @Override
                public void onSuccess(Call call, Response response, final String s) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            customLoadingDialog.dismiss();
                            Gson gson=new Gson();
                            EmploymentBean employmentBean = gson.fromJson(s, EmploymentBean.class);
                            articleList= employmentBean.getData().getInfo().getArticleList();
                            if (articleList!=null && articleList.size()>0){
                                employmentAdapter=new EmploymentAdapter(getActivity(),articleList);
                                listView.setAdapter(employmentAdapter);
                                listView.setVisibility(View.VISIBLE);
                                no_internet_rl.setVisibility(View.GONE);
                                no_data_rl.setVisibility(View.GONE);
                            }else{
                                listView.setVisibility(View.GONE);
                                no_internet_rl.setVisibility(View.GONE);
                                no_data_rl.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }
                @Override
                public void onEror(Call call, int statusCode, Exception e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            customLoadingDialog.dismiss();
                            listView.setVisibility(View.GONE);
                            no_internet_rl.setVisibility(View.VISIBLE);
                            no_data_rl.setVisibility(View.GONE);
                        }
                    });
                }
            });
        }else{
            listView.setVisibility(View.GONE);
            no_internet_rl.setVisibility(View.VISIBLE);
            no_data_rl.setVisibility(View.GONE);
        }
    }
    @Override
    protected void initListener() {
        //点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), OccupationWebviewActivity.class);
                intent.putExtra("title","详情");
                intent.putExtra("url",articleList.get(i).getWeburl());
                startActivity(intent);
            }
        });
    }
    @OnClick({R.id.reload})
    public void click(View view){
        switch (view.getId()){
            case R.id.reload:
                getData();
                break;
        }
    }
}
