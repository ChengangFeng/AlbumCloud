package com.github.chengang.library.base;

/**
 * Created by 陈岗不行陈 on 2017/7/26.
 * <p>
 * View基类
 */
public interface BaseView {

    void showErrorMsg(String msg);

    void useNightMode(boolean isNight);

    //=======  State  =======
    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();
}
