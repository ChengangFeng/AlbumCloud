package com.github.chengang.library.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by 陈岗不行陈 on 2017/7/26.
 * <p>
 * AbsBaseFragment
 */

public abstract class AbsBaseFragment extends Fragment {

    //根部 view
    protected View rootView;

    //是否初始化view
    protected Boolean isInitView = false;

    //是否是第一次加载数据
    private boolean isFirstLoad = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 设置监听器
     */
    protected void initListener() {

    }

    /**
     * 组件初始化设置
     */
    protected void initWidget() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = initView(inflater, container, savedInstanceState);
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //如果没有初始化数据，则初始化数据
        if (!isFirstLoad) {
            onLazyLoad();
        }
    }

    /**
     * 子类实现初始化View操作
     */
    protected abstract View initView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState);

    /**
     * 子类实现初始化数据操作(子类自己调用)
     */
    public abstract void initData();

    /**
     * fragment懒加载
     * <p>
     * 当fragment可见的时候才加载数据
     */
    protected void onLazyLoad() {
        if (!isFirstLoad || !isInitView) {
            return;
        }
        initData();
        isFirstLoad = false;
    }
}
