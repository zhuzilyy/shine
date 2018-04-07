package com.qianyi.shine.ui.home.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kanade.treeadapter.Node;
import com.kanade.treeadapter.TreeAdapter;
import com.kanade.treeadapter.TreeItemClickListener;
import com.qianyi.shine.R;
import com.qianyi.shine.base.BaseFragment;
import com.qianyi.shine.ui.college.activity.ProfessionalActivity;
import com.qianyi.shine.ui.home.bean.Major;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/6.
 */

public class MajorZhuanKeFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private List<Major> list;
    private View view_zhuanke;
    @Override
    protected View getResLayout(LayoutInflater inflater, ViewGroup container) {
        view_zhuanke=inflater.inflate(R.layout.fragment_major_zhuanke,null);
        return view_zhuanke;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        list=new ArrayList<>();
        Major A=new Major(1,0,"教育学");
        Major B=new Major(2,0,"教育学");
        Major C=new Major(3,0,"教育学");
        Major D=new Major(4,0,"教育学");
        Major E=new Major(5,0,"教育学");
        list.add(A);
        list.add(B);
        list.add(C);
        list.add(D);
        list.add(E);
        //第二层数据
        for (int i = 6; i <7 ; i++) {
            Major secondMajor=new Major(i,1,"小学教育",1);
            list.add(secondMajor);
        }
        //第三层数据
        for (int i = 26; i <30 ; i++) {
            Major thirdMajor=new Major(i,6,"小学数学教育");
            list.add(thirdMajor);
        }
        //第二层数据
        for (int i = 10; i <14 ; i++) {
            Major secondMajor=new Major(i,2,"小学教育");
            list.add(secondMajor);
        }
        //第二层数据
        for (int i = 14; i <18 ; i++) {
            Major secondMajor=new Major(i,3,"小学教育");
            list.add(secondMajor);
        }
        //第二层数据
        for (int i = 18; i <22 ; i++) {
            Major secondMajor=new Major(i,4,"小学教育");
            list.add(secondMajor);
        }
        //第二层数据
        for (int i = 22; i <26 ; i++) {
            Major secondMajor=new Major(i,5,"小学教育");
            list.add(secondMajor);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final TreeAdapter<Major> adapter = new TreeAdapter<>(getActivity());
        adapter.setNodes(list);
        adapter.setListener(new TreeItemClickListener() {
            @Override
            public void OnClick(Node node) {
               /* if (node.getId() == 3) {
                    adapter.addChildrenById(3, childs);
                }*/
                int level = node.getLevel();
                if (level==2){
                    startActivity(new Intent(getActivity(), ProfessionalActivity.class));
                }
               // Toast.makeText(getActivity(), node.getId()+"=======getId======", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

}
