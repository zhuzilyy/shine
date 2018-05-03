package com.qianyi.shine.ui.home.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kanade.treeadapter.Node;
import com.kanade.treeadapter.TreeAdapter;
import com.kanade.treeadapter.TreeItemClickListener;
import com.qianyi.shine.R;
import com.qianyi.shine.api.apiConstant;
import com.qianyi.shine.api.apiHome;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.callbcak.RequestCallBack;
import com.qianyi.shine.dialog.CustomLoadingDialog;
import com.qianyi.shine.fragment.entity.FirstLevel;
import com.qianyi.shine.fragment.entity.MajorBean;
import com.qianyi.shine.fragment.entity.SecondLevel;
import com.qianyi.shine.fragment.entity.ThirdLevel;
import com.qianyi.shine.ui.home.activity.PriorityProfessionalDetailsActivity;
import com.qianyi.shine.ui.home.bean.Major;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/6.
 */

public class MajorBenkeFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private View view_benke;
    private List<Major> majorList;
    private Intent intent;
    private String tag;
    private CustomLoadingDialog customLoadingDialog;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_benke=inflater.inflate(R.layout.fragment_major_benke,null);
        return view_benke;
    }
    @Override
    protected void initViews() {
        //majorList=new ArrayList<>();
        majorList=new ArrayList<>();
        intent=getActivity().getIntent();
        if (intent!=null){
            tag=intent.getStringExtra("tag");
        }
        customLoadingDialog=new CustomLoadingDialog(getActivity());
    }
    @Override
    protected void initData() {
        apiHome.getMajorList(apiConstant.MAJOR_LIST, "7", new RequestCallBack<String>() {
            @Override
            public void onSuccess(Call call, Response response, final String s) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("tag","111"+s);
                        customLoadingDialog.dismiss();
                        Gson gson=new Gson();
                        MajorBean majorBean = gson.fromJson(s, MajorBean.class);
                        List<FirstLevel> firstLevelList = majorBean.getData().getInfo().getMajorList();
                        Log.i("tag",firstLevelList.size()+"======firstLevelList=========");
                        //第一层数据
                        for (int i = 0; i < firstLevelList.size(); i++) {
                            String major_cate = firstLevelList.get(i).getMajor_cate();
                            Major firstLevel=new Major("1","0",major_cate);
                            majorList.add(firstLevel);
                            //Log.i("tag","111"+major_cate);
                            Toast.makeText(mActivity, ""+major_cate, Toast.LENGTH_SHORT).show();
                            List<SecondLevel> secondList = firstLevelList.get(i).getMajor_second_list();
                            for (int j = 0; j <secondList.size() ; j++) {
                                //第二层数据
                                String secondLevelName = secondList.get(j).getMajor_second_cate();
                                String secondLevleId = secondList.get(j).getMajor_second_cate_id();
                                Major secondLevel=new Major(secondLevleId,i+"",secondLevelName);
                                majorList.add(secondLevel);
                                Log.i("tag","222"+secondLevelName);
                                Toast.makeText(mActivity, ""+secondLevelName, Toast.LENGTH_SHORT).show();
                                List<ThirdLevel> thirdList = secondList.get(j).getMajor_list();
                                for (int k = 0; k <thirdList.size() ; k++) {
                                    String name = thirdList.get(k).getName();
                                    String id = thirdList.get(k).getId();
                                    Major thirdLevel=new Major(id,secondLevleId,name);
                                    majorList.add(thirdLevel);
                                    Log.i("tag","333"+name);
                                }
                            }
                        }
                    }
                });
            }
            @Override
            public void onEror(Call call, int statusCode, Exception e) {
                customLoadingDialog.dismiss();
            }
        });
       /* Major A=new Major(1,0,"经济学");
        Major B=new Major(2,0,"经济学");
        Major C=new Major(3,0,"经济学");
        Major D=new Major(4,0,"经济学");
        Major E=new Major(5,0,"经济学");
        list.add(A);
        list.add(B);
        list.add(C);
        list.add(D);
        list.add(E);
        //第二层数据
        for (int i = 6; i <7 ; i++) {
            Major secondMajor=new Major(i,1,"工程经济学",1);
            list.add(secondMajor);
        }
        //第三层数据
        for (int i = 26; i <30 ; i++) {
            Major thirdMajor=new Major(i,6,"土木工程经济学");
            list.add(thirdMajor);
        }
        //第二层数据
        for (int i = 10; i <14 ; i++) {
            Major secondMajor=new Major(i,2,"工程经济学");
            list.add(secondMajor);
        }
        //第二层数据
        for (int i = 14; i <18 ; i++) {
            Major secondMajor=new Major(i,3,"工程经济学");
            list.add(secondMajor);
        }
        //第二层数据
        for (int i = 18; i <22 ; i++) {
            Major secondMajor=new Major(i,4,"工程经济学");
            list.add(secondMajor);
        }
        //第二层数据
        for (int i = 22; i <26 ; i++) {
            Major secondMajor=new Major(i,5,"工程经济学");
            list.add(secondMajor);
        }*/
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final TreeAdapter<Major> adapter = new TreeAdapter<>(getActivity());
        adapter.setNodes(majorList);
        adapter.setListener(new TreeItemClickListener() {
            @Override
            public void OnClick(Node node) {
               /* if (node.getId() == 3) {
                    adapter.addChildrenById(3, childs);
                }*/
                int level = node.getLevel();
                if (level==2){
                    //startActivity(new Intent(getActivity(), ProfessionalActivity.class));
                    //意愿设置里面的专业设置
                    if (!TextUtils.isEmpty(tag)){
                        Intent intent=null;
                        if (tag.equals("willingSetting")){
                            intent=new Intent();
                            intent.setAction("com.action.setwilling");
                            intent.putExtra("majorName",node.getName());
                            getActivity().sendBroadcast(intent);
                         //专业优先跳转的界面  或是  查专业跳转过来的界面
                        }else if(tag.equals("majorPriority") ||tag.equals("searchMajor")){
                            intent=new Intent(getActivity(), PriorityProfessionalDetailsActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void initListener() {

    }
}
