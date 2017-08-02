package com.github.chengang.albumcloud;

import com.github.chengang.albumcloud.config.AppConstant;
import com.github.chengang.library.BaseApplication;
import com.squareup.leakcanary.LeakCanary;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * Created by 陈岗不行陈 on 2017/7/26.
 * <p>
 * 获取全局应用
 */

public class App extends BaseApplication {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        LeakCanary.install(this);

        initBmob();
    }

    private void initBmob() {
        BmobConfig config = new BmobConfig.Builder(this)
                //设置appkey
                .setApplicationId(AppConstant.BMOB_APPLICATION_ID)
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(15)
                //文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(1024 * 1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);
    }

    public static App getInstance() {
        return instance;
    }
}
