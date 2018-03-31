package com.qianyi.shine.ui.college.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianyi.shine.R;

/**
 * Created by Administrator on 2018/3/31.
 */

public class collegeIntroductionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       View layoutRes= inflater.inflate(R.layout.fragment_college_introduction,null);
        return layoutRes;
    }
}
