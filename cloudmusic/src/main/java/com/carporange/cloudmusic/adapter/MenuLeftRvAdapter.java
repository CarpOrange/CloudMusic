package com.carporange.cloudmusic.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.carporange.cloudmusic.R;
import com.carporange.cloudmusic.util.ToolImage;
import com.carporange.cloudmusic.widget.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Liyuchen on 2016/6/12.
 * email:987424501@qq.com
 * phone:18298376275
 */
public class MenuLeftRvAdapter extends RecyclerView.Adapter<MenuLeftRvAdapter.BaseViewHolder> {

    public static final int ITEM_TYPE_AVATAR = 0;
    public static final int ITEM_TYPE_DIVIDER = 1;
    public static final int ITEM_TYPE_IMAGE_TEXT = 2;
    public static final int ITEM_TYPE_IMAGE_TEXT_SWITCH = 3;

    private List<BaseMenuItem> mList;
    private OnSwitchListener mSwitchListner;

    public interface OnSwitchListener{
        void onSwitch(boolean isChecked);
    }

    public void setOnSwitchListener(OnSwitchListener onSwitchListener) {
        this.mSwitchListner = onSwitchListener;
    }

    public MenuLeftRvAdapter(List<BaseMenuItem> menuList) {
        this.mList = menuList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = null;
        View view = null;
        switch (viewType) {
            case ITEM_TYPE_AVATAR:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_avatar, parent, false);
                viewHolder = new AvatarViewHolder(view);
                break;
            case ITEM_TYPE_DIVIDER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_divider, parent, false);
                viewHolder = new BaseViewHolder(view);
                break;
            case ITEM_TYPE_IMAGE_TEXT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_left_option, parent, false);
                viewHolder = new ImageTextViewHolder(view);
                break;
            case ITEM_TYPE_IMAGE_TEXT_SWITCH:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_switch_menu_left_option, parent, false);
                viewHolder = new ImageTextSwitchViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        BaseMenuItem menuItem = mList.get(position);
        switch (menuItem.getType()) {
            case ITEM_TYPE_DIVIDER:
                break;
            case ITEM_TYPE_AVATAR:
                AvatarViewHolder viewHolder = (AvatarViewHolder) holder;
                AvatarMenuItem avatarMenuItem = (AvatarMenuItem) menuItem;
                viewHolder.tv_name.setText(avatarMenuItem.getName());
//                ImageLoader.getInstance().displayImage(avatarMenuItem.getAvatarPath()
//                        , viewHolder.civ_avatar, ToolImage.getFadeOptions(R.drawable.avatar_default,
//                        R.drawable.avatar_default, R.drawable.avatar_default));
                break;
            case ITEM_TYPE_IMAGE_TEXT:
                ImageTextViewHolder viewHolder1 = (ImageTextViewHolder) holder;
                ImageTextMenuItem menuItem1 = (ImageTextMenuItem) menuItem;
                viewHolder1.tv_content.setText(menuItem1.getContent());
                break;
            case ITEM_TYPE_IMAGE_TEXT_SWITCH:
                ImageTextSwitchViewHolder viewHolder2 = (ImageTextSwitchViewHolder) holder;
                final ImageTextSwitchMenuItem menuItem2 = (ImageTextSwitchMenuItem) menuItem;
                viewHolder2.tv_content.setText(menuItem2.getContent());
                viewHolder2.switchCompat.setChecked(menuItem2.isChecked());
                if(mSwitchListner != null) {
                    viewHolder2.switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            menuItem2.setIsChecked(isChecked);
                            mSwitchListner.onSwitch(isChecked);
                        }
                    });
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class AvatarViewHolder extends BaseViewHolder {
        public CircleImageView civ_avatar;
        public TextView tv_name;

        public AvatarViewHolder(View itemView) {
            super(itemView);
            civ_avatar = (CircleImageView) itemView.findViewById(R.id.civ_avatar);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    public class ImageTextViewHolder extends BaseViewHolder {
        public TextView tv_content;
        public ImageView iv_content;

        public ImageTextViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            iv_content = (ImageView) itemView.findViewById(R.id.iv_content);
        }
    }

    public class ImageTextSwitchViewHolder extends ImageTextViewHolder {
        public SwitchCompat switchCompat;
        public ImageTextSwitchViewHolder(View itemView) {
            super(itemView);
            switchCompat = (SwitchCompat) itemView.findViewById(R.id.switch_compat);
        }
    }

    public static class MenuItemFactory {
        public static BaseMenuItem createDividerrMenu() {
            return new BaseMenuItem();
        }
        public static AvatarMenuItem createAvatarMenu() {
            return new AvatarMenuItem();
        }
        public static ImageTextMenuItem createImageTextMenu(String content) {
            return new ImageTextMenuItem(content);
        }
        public static ImageTextSwitchMenuItem createImageTextSwitchMenu(String content, boolean isChecked) {
            return new ImageTextSwitchMenuItem(content, isChecked);
        }
    }

    public static class BaseMenuItem{

        protected BaseMenuItem() {

        }

        public int getType() {
            return ITEM_TYPE_DIVIDER;
        }
    }

    public static class AvatarMenuItem extends BaseMenuItem {
        private String name;
        private String avatarPath;
        private Bitmap avatarBitmap;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatarPath() {
            return avatarPath;
        }

        public void setAvatarPath(String avatarPath) {
            this.avatarPath = avatarPath;
        }

        public Bitmap getAvatarBitmap() {
            return avatarBitmap;
        }

        public void setAvatarBitmap(Bitmap avatarBitmap) {
            this.avatarBitmap = avatarBitmap;
        }

        @Override
        public int getType() {
            return ITEM_TYPE_AVATAR;
        }
    }

    public static class ImageTextMenuItem extends BaseMenuItem {
        protected String content;

        protected  ImageTextMenuItem(String content) {
            this.content = content;
        }
        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
        public int getType() {
            return ITEM_TYPE_IMAGE_TEXT;
        }
    }

    public static class ImageTextSwitchMenuItem extends ImageTextMenuItem {
        private boolean isChecked;
        protected  ImageTextSwitchMenuItem(String content, boolean isChecked) {
            super(content);
            this.isChecked = isChecked;
        }
        public boolean isChecked() {
            return isChecked;
        }

        public void setIsChecked(boolean isChecked) {
            this.isChecked = isChecked;
        }
        public int getType() {
            return ITEM_TYPE_IMAGE_TEXT_SWITCH;
        }
    }
}
