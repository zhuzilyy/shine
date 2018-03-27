package com.qianyi.shine.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


import com.qianyi.shine.application.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * <p>
 * 版 权 ： 沈阳夜鱼科技有限公司
 * <p>
 * 作 者 ： Ywp
 * <p>
 * 版 本 ： 2.0
 * <p>
 * 创建日期 ：2017/7/15
 * <p>
 * 描 述 ：
 *
 * 修订历史 ：
 * <p>
 * ============================================================
 **/
public class PackageUtils {

    /**
     * 获取所有安装应用的packageName的安装集合
     *
     * @return
     */
    public static List<String> getAllPackageInfos() {
        List<String> appinfos = new ArrayList<String>();
        PackageManager packageManager = MyApplication.getApplication().getPackageManager();
        List<PackageInfo> packinfos = packageManager.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        for (PackageInfo info : packinfos) {
            String packname = info.packageName;
            appinfos.add(packname);
        }
        return appinfos;
    }

    public static boolean isContains(String packageName) {
        // 4.2.2有问题
        // List<String> lists = getAllPackageInfos();
        // for (String name : lists) {
        // if (name.equals(packageName))
        // return true;
        // }
        // return false;
        final PackageManager packageManager = MyApplication.getApplication().getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<String>();
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);
    }

}
