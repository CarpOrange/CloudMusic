package com.carporange.cloudmusic.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.carporange.cloudmusic.R;
import com.carporange.cloudmusic.fragment.MainFragment;
import com.carporange.cloudmusic.ui.base.BaseActivity;
import com.carporange.cloudmusic.util.DensityUtil;

public class MainActivity extends BaseActivity implements MainFragment.OnHomeClickListener{

    private DrawerLayout mDrawerLayout;
    private View mFragmentLeft;
    private Fragment mMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initViews() {
        setContentView(R.layout.activity_main);
        mFragmentLeft = findViewById(R.id.fragment_left);
        ViewGroup.LayoutParams layoutParams = mFragmentLeft.getLayoutParams();
        layoutParams.width = DensityUtil.getDisplayWidth(this) * 4 / 5;
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        FragmentManager fm = getSupportFragmentManager();
        if(mMainFragment == null) {
            mMainFragment = new MainFragment();
        }
        fm.beginTransaction().replace(R.id.framelayout, mMainFragment).commit();
    }

    @Override
    public void initActionBar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar ab = getSupportActionBar();
//        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        ab.setHomeAsUpIndicator(R.drawable.actionbar_menu);
//        ab.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onHomeClick() {
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }
}
