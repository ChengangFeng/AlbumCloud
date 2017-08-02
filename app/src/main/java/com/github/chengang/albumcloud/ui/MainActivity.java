package com.github.chengang.albumcloud.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.chengang.albumcloud.R;
import com.github.chengang.albumcloud.dagger.DaggerActivity;
import com.github.chengang.albumcloud.dagger.DaggerFragment;
import com.github.chengang.albumcloud.ui.Config.ConfigFragment;
import com.github.chengang.albumcloud.ui.dashboard.DashboardFragment;
import com.github.chengang.albumcloud.ui.home.HomeFragment;
import com.github.chengang.albumcloud.ui.upload.UploadActivity;
import com.github.chengang.library.utils.ScreenUtil;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import rx.Observer;

public class MainActivity extends DaggerActivity implements View.OnClickListener {

    @BindView(R.id.navigation)
    BottomNavigationViewEx mNavigation;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.iv_upload)
    ImageView mImageViewUpload;

    private LinkedList<DaggerFragment> fragmentList;

    private static final int REQUEST_CODE_CHOOSE = 0x10;

    private BottomNavigationViewEx.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            switchToHome();
                            return true;
                        case R.id.navigation_dashboard:
                            switchToDashboard();
                            return true;
                        case R.id.navigation_notifications:
                            switchToConfig();
                            return true;
                        default:
                            return false;
                    }
                }
            };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);

        //init BottomNavigationView
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mNavigation.enableAnimation(false);
        mNavigation.enableShiftingMode(false);
        mNavigation.enableItemShiftingMode(false);
        mNavigation.setTextVisibility(false);
        mNavigation.setItemHeight(ScreenUtil.dp2px(this, 56));
        mNavigation.setIconsMarginTop(ScreenUtil.dp2px(this, 16));

        mImageViewUpload.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        fragmentList = new LinkedList<>();

        fragmentList.add(new HomeFragment());
        fragmentList.add(new DashboardFragment());
        fragmentList.add(new ConfigFragment());

        switchToHome();
    }

    private void switchToHome() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, fragmentList.get(0)).commit();
    }

    private void switchToDashboard() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, fragmentList.get(1)).commit();
    }

    private void switchToConfig() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, fragmentList.get(2)).commit();
    }

    @Override
    public void onClick(View v) {
        selectPhoto();
    }

    private void selectPhoto() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Observer<Boolean>() {

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            Matisse.from(MainActivity.this)
                                    .choose(MimeType.ofAll()) // 选择 mime 的类型
                                    .theme(R.style.MyMatisseTheme)
                                    .countable(true)
                                    .maxSelectable(9) // 图片选择的最多数量
                                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                    .thumbnailScale(0.85f) // 缩略图的比例
                                    .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                                    .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码
                        } else {
                            Toast.makeText(MainActivity.this, "permission_request_denied", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            ArrayList<Uri> imageUriList = (ArrayList<Uri>) Matisse.obtainResult(data);
            if (imageUriList != null && imageUriList.size() > 0) {
                Intent uploadIntent = new Intent(this, UploadActivity.class);
                Bundle uriBundle = new Bundle();
                uriBundle.putParcelableArrayList("uriData", imageUriList);
                uploadIntent.putExtra("uriBundle", uriBundle);
                startActivity(uploadIntent);
            }
        }
    }
}
