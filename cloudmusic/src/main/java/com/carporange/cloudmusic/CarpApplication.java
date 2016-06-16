package com.carporange.cloudmusic;

import android.app.Application;

import com.carporange.cloudmusic.util.ToolImage;

/**
 * Created by Liyuchen on 2016/6/14.
 * email:987424501@qq.com
 * phone:18298376275
 */
public class CarpApplication extends Application{
    private static CarpApplication application;
    public static CarpApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        ToolImage.initImageLoader(this);
    }
}
