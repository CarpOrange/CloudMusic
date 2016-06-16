package com.carporange.cloudmusic.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carporange.cloudmusic.R;
import com.carporange.cloudmusic.adapter.MenuLeftRvAdapter;
import com.carporange.cloudmusic.util.SpUtil;

import java.util.ArrayList;
import java.util.List;

public class MenuLeftFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private View mContentView;

    public MenuLeftFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_menu_left, container, false);
        initViews();
        return mContentView;
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) mContentView.findViewById(R.id.recycler_view);
        List<MenuLeftRvAdapter.BaseMenuItem> menuItemList = new ArrayList<>();
        MenuLeftRvAdapter.AvatarMenuItem avatarMenuItem = MenuLeftRvAdapter.MenuItemFactory.createAvatarMenu();
        avatarMenuItem.setName("李昱辰");
        avatarMenuItem.setAvatarPath("http://www.45dgdfg.com");
        menuItemList.add(avatarMenuItem);
        menuItemList.add(MenuLeftRvAdapter.MenuItemFactory.createImageTextMenu("我的消息"));
        menuItemList.add(MenuLeftRvAdapter.MenuItemFactory.createImageTextMenu("积分商城"));
        menuItemList.add(MenuLeftRvAdapter.MenuItemFactory.createImageTextMenu("付费音乐包"));
        menuItemList.add(MenuLeftRvAdapter.MenuItemFactory.createImageTextMenu("在线听歌免流量"));
        menuItemList.add(MenuLeftRvAdapter.MenuItemFactory.createDividerrMenu());
        menuItemList.add(MenuLeftRvAdapter.MenuItemFactory.createImageTextMenu("听歌识曲"));
        menuItemList.add(MenuLeftRvAdapter.MenuItemFactory.createImageTextMenu("主题换肤"));
        menuItemList.add(MenuLeftRvAdapter.MenuItemFactory.createImageTextSwitchMenu("夜间模式", SpUtil.getBoolean("nightMode", false)));
        menuItemList.add(MenuLeftRvAdapter.MenuItemFactory.createImageTextMenu("定时停止播放"));
        menuItemList.add(MenuLeftRvAdapter.MenuItemFactory.createImageTextMenu("音乐闹钟"));
        menuItemList.add(MenuLeftRvAdapter.MenuItemFactory.createImageTextMenu("驾驶模式"));
        menuItemList.add(MenuLeftRvAdapter.MenuItemFactory.createImageTextMenu("我的音乐云盘"));
        MenuLeftRvAdapter adapter = new MenuLeftRvAdapter(menuItemList);
        adapter.setOnSwitchListener(new MenuLeftRvAdapter.OnSwitchListener() {
            @Override
            public void onSwitch(boolean isChecked) {
                SpUtil.put("nightMode", isChecked);
                getActivity().recreate();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);
    }

}
