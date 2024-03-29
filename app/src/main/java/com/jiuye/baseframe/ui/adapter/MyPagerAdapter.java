package com.jiuye.baseframe.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * @author : GuoQiang
 * e-mail : 849199845@qq.com
 * time   : 2018/12/10  10:48
 * desc   :
 * version: 1.0
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentList;
    private String[] mTitles;
    public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }
    public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentList, String[] title) {
        super(fm);
        this.fragmentList = fragmentList;
        this.mTitles=title;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles != null && position < mTitles.length) {
            return mTitles[position];
        } else {
            return "";
        }
    }

    @Override
    public int getCount() {
        return fragmentList==null?0:fragmentList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
