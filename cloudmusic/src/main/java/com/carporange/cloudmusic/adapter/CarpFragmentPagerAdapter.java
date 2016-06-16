package com.carporange.cloudmusic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Liyuchen on 2016/6/15.
 * email:987424501@qq.com
 * phone:18298376275
 */
public class CarpFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;

    public CarpFragmentPagerAdapter(List<Fragment> list, FragmentManager fm) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
