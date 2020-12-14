package com.newblink.blink.android.common.utils.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.newblink.blink.android.R;

public class ImageLoaderUtil {

    //默认加载 内存缓存 硬盘缓存处理后的显示图 只从缓存中加载图片
    public static void loadImg(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).into(iv);
    }

    //本地资源
    public static void loadImg(Context context, int url, ImageView iv) {
        Glide.with(context).load(url).into(iv);
    }

    //加载指定大小
    public static void loadImgBySize(Context context, String url, int width, int height, ImageView iv) {
        Glide.with(context).load(url).override(width, height).into(iv);
    }

    //填充
    public static void loadImgCrop(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(iv);
    }

    //设置加载中以及加载失败图片
    public static void loadImgWithOccupationMap(Context context, String url, ImageView iv, int loadingImgId, int errorImgId) {
        Glide.with(context).load(url).placeholder(loadingImgId).error(errorImgId).into(iv);
    }

    //设置加载中以及加载失败图片并且指定大小
    public static void loadImgWithOccupationMapBySize(Context context, String url, int width, int height, ImageView iv, int loadingImgId, int errorImgId) {
        Glide.with(context).load(url).override(width, height).placeholder(loadingImgId).error(errorImgId).into(iv);
    }



     // 加载圆形图 一般是头像这种相对会变换比较频繁，所以不做磁盘缓存
    public static void loadCircleImg(Context context, String url, ImageView iv, int loadingImgId, int errorImgId) {
        Glide.with(context).load(url).apply(initOptionsA())
                .error(errorImgId).placeholder(loadingImgId)
                .fallback(errorImgId).circleCrop().into(iv);
    }

    // 加载圆形图 加载本地图片
    public static void loadCircleImgEasyChange(Context context, int url, ImageView iv, int loadingImgId, int errorImgId,int radios) {
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).error(errorImgId).placeholder(loadingImgId).transform(new GlideRoundImage(radios)).into(iv);
    }

    //加载网络图片 圆角
    public static void loadCircleImgInNet(Context context, int url, ImageView iv, int loadingImgId, int errorImgId,int radios) {
        Glide.with(context).load(url).error(errorImgId).placeholder(loadingImgId).transform(new GlideRoundImage(radios)).into(iv);
    }

    //加载网络图片 圆角
    public static void loadCircleImgInNet(Context context, String url, ImageView iv, int loadingImgId, int errorImgId,int radios) {
        Glide.with(context).load(url).error(errorImgId).placeholder(loadingImgId).transform(new GlideRoundImage(radios)).into(iv);
    }

    //加载网络图片 圆角 无占位图
    public static void loadCircleImgInNetNoPlaceholder(Context context, String url, ImageView iv,int radios) {
        Glide.with(context).load(url).transform(new GlideRoundImage(radios)).into(iv);
    }

    // 加载圆形图 加载网络图片
    public static void loadCircleImg(Context context, String url, ImageView iv, int loadingImgId, int errorImgId,int radios) {
        Glide.with(context).load(url).error(errorImgId).placeholder(loadingImgId).transform(new GlideRoundImage(radios)).into(iv);
    }

    //TODO 高斯模糊


     //true 不缓存 false 缓存
    private static boolean isSkip() {
        return false;
    }


    private static RequestOptions initOptions(BitmapTransformation transformation) {
        return new RequestOptions()
                .transform(transformation)
                .skipMemoryCache(isSkip())//是否允许内存缓存
                .onlyRetrieveFromCache(true)//是否只从缓存加载图片
                .diskCacheStrategy(DiskCacheStrategy.NONE);//禁止磁盘缓存
    }


    //允许缓存 只能从缓存加载图片 禁止磁盘缓存
    private static RequestOptions initOptionsA() {
        return new RequestOptions()
                .skipMemoryCache(isSkip())
                .onlyRetrieveFromCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
    }

    //允许缓存 只能从缓存加载图片 磁盘只缓存转换过的图片 这个是glide默认
    private static RequestOptions initOptionsB() {
        return new RequestOptions()
                .skipMemoryCache(isSkip())
                .onlyRetrieveFromCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
    }


    //允许缓存 只能从缓存加载图片 磁盘只缓存转换过的图片
    private static RequestOptions initOptionsC() {
        return new RequestOptions()
                .skipMemoryCache(isSkip())
                .onlyRetrieveFromCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }


    public static void clearMemory(Context context) {
        //清理内存缓存  可以在UI主线程中进行
        Glide.get(context).clearMemory();
    }


    public static void clearDiskCache(Context context) {
        //理磁盘缓存 需要在子线程中执行
        Glide.get(context).clearDiskCache();
    }



}
