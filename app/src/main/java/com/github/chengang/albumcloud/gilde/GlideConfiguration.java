package com.github.chengang.albumcloud.gilde;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;
import com.github.chengang.library.helper.AppFileHelper;

/**
 * Created by 陈岗不行陈 on 2017/6/27.
 * <p>
 * Glide自定义配置
 */
public class GlideConfiguration implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        //解决压缩图片后颜色变绿的问题
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        /*
        内存缓存
        Glide提供了一个类MemorySizeCalculator，用于决定内存缓存大小以及 bitmap 的缓存池。
        bitmap 池维护了你 Application 的堆中的图像分配。
        正确的 bitmpa 池是非常必要的，因为它避免很多的图像重复回收，这样可以确保垃圾回收器的管理更加合理。
        它的默认计算实现
        */
        MemorySizeCalculator memorySizeCalculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = memorySizeCalculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = memorySizeCalculator.getBitmapPoolSize();
        builder.setMemoryCache(new LruResourceCache((int) (defaultMemoryCacheSize * 1.2f)));
        builder.setBitmapPool(new LruBitmapPool((int) (defaultBitmapPoolSize * 1.2f)));

        /*
        磁盘缓存
        Glide图片缓存有两种情况，一种是内部磁盘缓存另一种是外部磁盘缓存。
        我们可以通过 builder.setDiskCache（）设置，并且Glide已经封装好了两个类实现外部和内部磁盘缓存，
        分别是InternalCacheDiskCacheFactory和ExternalCacheDiskCacheFactory
         */
        //磁盘缓存50M
        builder.setDiskCache(new DiskLruCacheFactory(AppFileHelper.getAppPicPath(), 50 * 1024 * 1024));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
    }
}
