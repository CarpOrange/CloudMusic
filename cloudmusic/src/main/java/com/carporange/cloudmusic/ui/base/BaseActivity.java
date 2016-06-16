package com.carporange.cloudmusic.ui.base;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.carporange.cloudmusic.R;
import com.carporange.cloudmusic.util.SpUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by Liyuchen on 2016/6/12.
 * email:987424501@qq.com
 * phone:18298376275
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isNightMode = SpUtil.getBoolean("nightMode", false);
        setTheme(isNightMode ? R.style.NightTheme : R.style.DayTheme);
        initActionBar();
        initViews();
        initWindow();
    }

    public abstract void initActionBar();
    public abstract void initViews();
    public void getData(){

    }
    public void setListeners() {

    }

    @TargetApi(19)
    private void initWindow(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

}
