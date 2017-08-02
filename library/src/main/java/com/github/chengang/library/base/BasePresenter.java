package com.github.chengang.library.base;

/**
 * Created by 陈岗不行陈 on 2017/7/26.
 * <p>
 * Presenter基类
 */
public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}
