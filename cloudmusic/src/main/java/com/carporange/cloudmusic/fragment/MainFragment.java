package com.carporange.cloudmusic.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.carporange.cloudmusic.R;
import com.carporange.cloudmusic.adapter.CarpFragmentPagerAdapter;
import com.carporange.cloudmusic.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment implements View.OnClickListener{

    private ImageView mHomeImage;
    private OnHomeClickListener mOnHomeClickListener;
    private Activity mActivity;
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;

    public MainFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        mOnHomeClickListener = (OnHomeClickListener) mActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(mContentView == null) {
            mContentView = inflater.inflate(R.layout.fragment_main, container, false);
            initViews();
            setListeners();
        }
        return mContentView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnHomeClickListener = null;
    }

    private void initViews() {
        mHomeImage = findView(R.id.actionbar_home);
        mHomeImage.setOnClickListener(this);
        mViewPager = findView(R.id.viewPager_main);
        List<Fragment> list = new ArrayList<>();
        list.add(new DiscoverFragment());
        list.add(new MusicFragment());
        list.add(new FriendsFragment());
        FragmentPagerAdapter fpa = new CarpFragmentPagerAdapter(list, getChildFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(fpa);
        mRadioGroup = findView(R.id.radioGroup);
        mRadioGroup.check(R.id.rb_discover);
    }

    private void setListeners() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRadioGroup.check(R.id.rb_discover);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.rb_music);
                        break;
                    case 2:
                        mRadioGroup.check(R.id.rb_friends);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_discover:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_music:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_friends:
                        mViewPager.setCurrentItem(2);
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionbar_home:
                if(mOnHomeClickListener != null) {
                    mOnHomeClickListener.onHomeClick();
                }
                break;
        }
    }

    public interface OnHomeClickListener{
        void onHomeClick();
    }


}
