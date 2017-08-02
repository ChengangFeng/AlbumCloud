package com.github.chengang.albumcloud.dagger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chengang.library.base.AbsBaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 陈岗不行陈 on 2017/7/26.
 * <p>
 * DaggerFragment
 */

public abstract class DaggerFragment extends AbsBaseFragment {

    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = initView(inflater, container, savedInstanceState);
            unbinder = ButterKnife.bind(this, rootView);
        } else {
            unbinder = ButterKnife.bind(this, rootView);
        }

        //初始化view完毕
        isInitView = true;

        //设置监听器
        initListener();

        //组件初始化设置（例如初始化recyclerview的配置等等）
        initWidget();

        //onLazyLoad，解决fragment可见但是还没执行onCreateView方法
        onLazyLoad();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
