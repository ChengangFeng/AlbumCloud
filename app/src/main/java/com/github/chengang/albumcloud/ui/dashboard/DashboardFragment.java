package com.github.chengang.albumcloud.ui.dashboard;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chengang.albumcloud.R;
import com.github.chengang.albumcloud.adapter.DashboardImageAdapter;
import com.github.chengang.albumcloud.dagger.DaggerFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class DashboardFragment extends DaggerFragment {

    List<String> mutiImages =
            new ArrayList<>(
                    Arrays.asList(
                            "http://imglf1.nosdn.127.net/img/NWxuTTNsdXVnVlBIU2JiUXBjQzkySmNMdFhXWEtsNnRmVklEeGpBMElMSi9mYkVaNXpCQ0dBPT0.jpg?imageView&thumbnail=1680x0&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=2&text=wqkg6ZmI5bKX5LiN5aeT6ZmIIC8gY2hhbmtvbmcubG9mdGVyLmNvbQ==&font=bXN5aA==&gravity=southwest&dissolve=30&fontsize=680&dx=32&dy=36&stripmeta=0",
                            "http://imglf1.nosdn.127.net/img/NWxuTTNsdXVnVlB0Y0YzdDBWMFZ0dnVlWnFSK3R0cHBuRnFXREc0WUlVU2ZHcGVZcEkydWZnPT0.jpg?imageView&thumbnail=1680x0&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=2&text=wqkg6ZmI5bKX5LiN5aeT6ZmIIC8gY2hhbmtvbmcubG9mdGVyLmNvbQ==&font=bXN5aA==&gravity=southwest&dissolve=30&fontsize=680&dx=32&dy=36&stripmeta=0",
                            "http://imglf2.nosdn.127.net/img/NWxuTTNsdXVnVk1vb3dudkVROGNHVkdHekFsU3VUODlSdDVDWXlhQkxGVWt0bzV2UFk1TE5RPT0.jpg?imageView&thumbnail=1680x0&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=2&text=wqkg6ZmI5bKX5LiN5aeT6ZmIIC8gY2hhbmtvbmcubG9mdGVyLmNvbQ==&font=bXN5aA==&gravity=southwest&dissolve=30&fontsize=680&dx=32&dy=36&stripmeta=0",
                            "http://imglf1.nosdn.127.net/img/NWxuTTNsdXVnVk1aS2xlTlZQblQ3KzZ0eDlmTTlob3UyakJxdFByeU1MUmgvZEJzc0gvMFJBPT0.jpg?imageView&thumbnail=1680x0&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=2&text=wqkg6ZmI5bKX5LiN5aeT6ZmIIC8gY2hhbmtvbmcubG9mdGVyLmNvbQ==&font=bXN5aA==&gravity=southwest&dissolve=30&fontsize=680&dx=32&dy=36&stripmeta=0",
                            "http://imglf2.nosdn.127.net/img/NWxuTTNsdXVnVk04MzE2YkRlVFovcjJxeWlRQVNmN2w3K2FOMUh0M2hCd21PbmxtRkxIV3R3PT0.jpg?imageView&thumbnail=1680x0&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=2&text=wqkg6ZmI5bKX5LiN5aeT6ZmIIC8gY2hhbmtvbmcubG9mdGVyLmNvbQ==&font=bXN5aA==&gravity=southwest&dissolve=30&fontsize=340&dx=16&dy=20&stripmeta=0",
                            "http://imglf1.nosdn.127.net/img/NWxuTTNsdXVnVk53ejBoYVJWWXkxSUdYZEErUU5rOURTU2hZcmEvQlFhR0luenVNNjJlRkNRPT0.jpg?imageView&thumbnail=1680x0&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=2&text=wqkg6ZmI5bKX5LiN5aeT6ZmIIC8gY2hhbmtvbmcubG9mdGVyLmNvbQ==&font=bXN5aA==&gravity=southwest&dissolve=30&fontsize=340&dx=16&dy=20&stripmeta=0",
                            "http://imglf0.ph.126.net/9QxriHiQRIN7kyZsCOnQZQ==/671880769426312011.jpg"));


    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void initData() {
        //init adapter
        DashboardImageAdapter mAdapter = new DashboardImageAdapter(getActivity(), mutiImages);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(mAdapter);
    }
}
