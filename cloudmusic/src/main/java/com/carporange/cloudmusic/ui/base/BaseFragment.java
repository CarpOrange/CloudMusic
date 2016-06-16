package com.carporange.cloudmusic.ui.base;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Liyuchen on 2016/6/15.
 * email:987424501@qq.com
 * phone:18298376275
 */
public class BaseFragment extends Fragment {
    protected View mContentView;

    protected  <T> T findView(int viewId) {
        View view = mContentView.findViewById(viewId);
        if(view != null) {
            return ((T)view);
        }
        return null;
    }
}
