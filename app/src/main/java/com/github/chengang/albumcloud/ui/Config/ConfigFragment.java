package com.github.chengang.albumcloud.ui.Config;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chengang.albumcloud.R;
import com.github.chengang.albumcloud.dagger.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigFragment extends DaggerFragment {


    public ConfigFragment() {
        // Required empty public constructor
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_config, container, false);
    }

    @Override
    public void initData() {

    }

}
