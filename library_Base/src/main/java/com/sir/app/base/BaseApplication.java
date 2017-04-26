package com.sir.app.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 整个应用程序Applicaiton
 * Created by zhuyinan on 2016/4/25.
 * Contact by 445181052@qq.com
 */
public class BaseApplication extends Application {

    /**
     * 对外提供整个应用生命周期的Context
     **/
    private static Context instance;
    /**
     * 整个应用全局可访问数据集合
     **/
    private static Map<String, Object> gloableData = new HashMap<String, Object>();
    /***
     * 寄存整个应用Activity
     **/
    private final Stack<WeakReference<Activity>> activitys = new Stack<WeakReference<Activity>>();
    /**
     * 图片加载
     */
    private DisplayImageOptions.Builder mImageOptionsBuilder;
    /**
     * 对外提供Application Context
     *
     * @return
     */
    public static Context getContext() {
        return instance;
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    /******************************************************* Application数据操作API（开始） ********************************************************/

    /**
     * 往Application放置数据（最大不允许超过5个）
     *
     * @param strKey   存放属性Key
     * @param strValue 数据对象
     */
    public static void assignData(String strKey, Object strValue) {
        if (gloableData.size() > 5) {
            throw new RuntimeException("超过允许最大数");
        }
        gloableData.put(strKey, strValue);
    }

    /**
     * 从Applcaiton中取数据
     *
     * @param strKey 存放数据Key
     * @return 对应Key的数据对象
     */
    public static Object gainData(String strKey) {
        return gloableData.get(strKey);
    }

    /**
     * 从Application中移除数据
     */
    public static void removeData(String key) {
        if (gloableData.containsKey(key)){
            gloableData.remove(key);
        }
    }

    /******************************************************* Application数据操作API（结束） ********************************************************/

    /******************************************* Application中存放的Activity操作（压栈/出栈）API（开始） *****************************************/

    /**
     * 将Activity压入Application栈
     *
     * @param task 将要压入栈的Activity对象
     */
    public void pushTask(WeakReference<Activity> task) {
        activitys.push(task);
    }

    /**
     * 将传入的Activity对象从栈中移除
     *
     * @param task
     */
    public void removeTask(WeakReference<Activity> task) {
        activitys.remove(task);
    }

    /**
     * 根据指定位置从栈中移除Activity
     *
     * @param taskIndex Activity栈索引
     */
    public void removeTask(int taskIndex) {
        if (activitys.size() > taskIndex)
            activitys.remove(taskIndex);
    }

    /**
     * 将栈中Activity移除至栈顶
     */
    public void removeToTop() {
        int end = activitys.size();
        int start = 1;
        for (int i = end - 1; i >= start; i--) {
            if (!activitys.get(i).get().isFinishing()) {
                activitys.get(i).get().finish();
            }
        }
    }

    /**
     * 移除全部（用于整个应用退出）
     */
    public void removeAll() {
        for (WeakReference<Activity> task : activitys) {
            if (!task.get().isFinishing()) {
                task.get().finish();
            }
        }
    }

    /******************************************* * Application中存放的Activity操作（压栈/出栈）API（结束） *****************************************/

    /******************************************************* Application图片加载操作API（开始） ********************************************************/

    public DisplayImageOptions getImageOptions() {
        return getImageOptions(R.mipmap.ic_fap, R.mipmap.ic_fao, R.mipmap.ic_fao);
    }

    public DisplayImageOptions getImageOptions(int onLoadId, int onFailId, int emptyUriId) {
        if (mImageOptionsBuilder == null) {
            initImageLoader();
            mImageOptionsBuilder = new DisplayImageOptions.Builder()
                    .resetViewBeforeLoading(false) // default
                    .delayBeforeLoading(0)
                    .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                    .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                    .considerExifParams(false) // default
                    .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                    .bitmapConfig(Bitmap.Config.RGB_565) // /设置图片的解码类型
                    .displayer(new RoundedBitmapDisplayer(10));//设置图片的显示方式
            //RoundedBitmapDisplayer设置成圆角图片
            //FadeInBitmapDisplayer设置图片渐显的时间
            //SimpleBitmapDisplayer正常显示一张图片

        }
        DisplayImageOptions options = mImageOptionsBuilder
                .showImageOnLoading(onLoadId) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(onFailId) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(emptyUriId).build(); // 设置图片加载/解码过程中错误时候显示的图片
        return options;
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)//设置当前线程的优先级
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) //可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)  // 内存缓存的最大值
                .memoryCacheSizePercentage(13) // 内存高速缓存大小百分比
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())// default为使用HASHCODE对UIL进行加密命名,还可以用MD5(new Md5FileNameGenerator())加密
                .diskCacheFileCount(100)  // 可以缓存的文件数量
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb sd卡(本地)缓存的最大值
                .writeDebugLogs().build();

        //初始化ImageLoaderConfiguration
        ImageLoader.getInstance().init(config);
    }

    /**
     * 清除内存缓存
     */
    public void onClearMemoryClick() {
        ImageLoader.getInstance().clearMemoryCache();
    }

    /**
     * 清除本地缓存
     */
    public void onClearDiskClick() {
        ImageLoader.getInstance().clearDiskCache();
    }

/******************************************************* Application图片加载操作API（开始） ********************************************************/
}
