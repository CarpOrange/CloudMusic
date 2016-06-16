package com.carporange.cloudmusic.util;

/**
 * Created by Administrator on 2015/11/2.
 */

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;

/**
 * 图片加载工具类
 * @version 1.0
 *
 */
public abstract class ToolImage {

    /**
     * 初始化ImageLoader
     */
    public static void initImageLoader(Context context) {
        //缓存文件的目录
        File cacheDir = CacheUtil.getDiskCacheDir(context, "images");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3) //线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //将保存的时候的URI名称用MD5 加密
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024) // 内存缓存的最大值
                .diskCacheSize(50 * 1024 * 1024)  // 50 Mb sd卡(本地)缓存的最大值
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                        // 由原先的discCache -> diskCache
                .diskCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();
        //全局初始化此配置
        ImageLoader.getInstance().init(config);

    }

    /**
     * 获取渐现显示选项
     * @return
     */
    public static DisplayImageOptions getFadeOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(null) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(null) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(null) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                        /**设置图片缩放方式：
                         EXACTLY :图像将完全按比例缩小到目标大小
                         EXACTLY_STRETCHED:图片会缩放到目标大小完全
                         IN_SAMPLE_INT:图像将被二次采样的整数倍
                         IN_SAMPLE_POWER_OF_2:图片将降低2倍，直到下一减少步骤，使图像更小的目标大小
                         NONE:图片不会调整
                         ***/
                .imageScaleType(ImageScaleType.NONE)
                .bitmapConfig(Bitmap.Config.RGB_565)
//                .delayBeforeLoading(100)// 设置图片下载前的延迟
//                .displayer(new FadeInBitmapDisplayer(1 * 1000))// 渐显--设置图片渐显的时间
//                .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 构建完成
        return options;
    }

    /**
     * 获取渐现显示选项
     * @param loadingImageResId 加载期间显示的图片
     * @param errorImageResid 加载错误时显示的图片
     * @param emptyImageResId 空图片或者解析图片出错时显示的图片
     * @return
     */
    public static DisplayImageOptions getFadeOptions(int loadingImageResId,int errorImageResid,int emptyImageResId ) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                //设置图片在下载期间显示的图片
                .showImageOnLoading(loadingImageResId)
                        // 设置图片加载/解码过程中错误时候显示的图片
                .showImageOnFail(errorImageResid)
                        // 设置图片Uri为空或是错误的时候显示的图片
                .showImageForEmptyUri(emptyImageResId)
                        // 设置下载的图片是否缓存在内存中
                .cacheInMemory(true)
                        // 设置下载的图片是否缓存在SD卡中
                .cacheOnDisk(true)
                        /**设置图片缩放方式：
                         EXACTLY :图像将完全按比例缩小到目标大小
                         EXACTLY_STRETCHED:图片会缩放到目标大小完全
                         IN_SAMPLE_INT:图像将被二次采样的整数倍
                         IN_SAMPLE_POWER_OF_2:图片将降低2倍，直到下一减少步骤，使图像更小的目标大小
                         NONE:图片不会调整
                         ***/
                .imageScaleType(ImageScaleType.NONE)
                        // 设置图片的解码类型
                .bitmapConfig(Bitmap.Config.RGB_565)
                        // 设置图片下载前的延迟
//                .delayBeforeLoading(100)
                        // delayInMillis为你设置的延迟时间
                        // 设置图片加入缓存前，对bitmap进行设置
                        // .preProcessor(BitmapProcessor preProcessor)

                        /**
                         * 图片显示方式：
                         *  RoundedBitmapDisplayer（int roundPixels）设置圆角图片
                         *  FakeBitmapDisplayer（）这个类什么都没做
                         *  FadeInBitmapDisplayer（int durationMillis）设置图片渐显的时间
                         　　　　   *　SimpleBitmapDisplayer()正常显示一张图片
                         **/
//                .displayer(new FadeInBitmapDisplayer(1 * 1000))// 渐显--设置图片渐显的时间
                .build();
        return options;
    }

    /**
     * 获取默认显示配置选项
     */
    public static DisplayImageOptions getDefaultOptions(){
        return DisplayImageOptions.createSimple();
    }

    /**
     * 清除缓存
     */
    public static void clearCache() {
        ImageLoader.getInstance().clearMemoryCache();
        ImageLoader.getInstance().clearDiskCache();
    }

}
