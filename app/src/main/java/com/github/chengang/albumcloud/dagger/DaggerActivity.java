package com.github.chengang.albumcloud.dagger;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.github.chengang.library.base.AbsBaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 陈岗不行陈 on 2017/7/26.
 * <p>
 * DaggerActivity
 */

public abstract class DaggerActivity extends AbsBaseActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去除actionbar的默认的阴影
        if (Build.VERSION.SDK_INT >= 21 && getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }

    }

    @Override
    @CallSuper
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
