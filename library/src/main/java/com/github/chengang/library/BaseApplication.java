package com.github.chengang.library;

import android.app.Application;

import com.github.chengang.library.helper.AppFileHelper;

/**
 * Created by 陈岗不行陈 on 2017/7/26.
 * <p>
 * Base Application for Application
 */

public class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppFileHelper.initStoryPath();
    }

    public static BaseApplication getInstance(){
        return instance;
    }

}
