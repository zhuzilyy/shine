package com.qianyi.shine.ui.home.bean;

import com.qianyi.shine.ui.mine.bean.UniversityInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/5/16.
 */

public class UniversityListInfo {
    private List<SchoolInfo> PriorSchoolList;
    public List<SchoolInfo> getPriorSchoolList() {
        return PriorSchoolList;
    }

    public void setPriorSchoolList(List<SchoolInfo> priorSchoolList) {
        PriorSchoolList = priorSchoolList;
    }
}
