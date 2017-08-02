package com.github.chengang.library.utils;

import android.content.Context;

/**
 * Created by 陈岗不行陈 on 2017/7/25.
 * <p>
 * 屏幕工具类
 */

public class ScreenUtil {

    /**
     * dp转px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
