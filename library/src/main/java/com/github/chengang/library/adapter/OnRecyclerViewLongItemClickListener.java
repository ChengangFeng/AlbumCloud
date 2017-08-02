package com.github.chengang.library.adapter;

import android.view.View;

/**
 * Created by fengchengang on 2017/5/14.
 */

public interface OnRecyclerViewLongItemClickListener<T> {
    boolean onItemLongClick(View v, int position, T data);
}
