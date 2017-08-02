package com.github.chengang.albumcloud.ui.upload;


import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.chengang.albumcloud.R;
import com.github.chengang.albumcloud.adapter.UploadImageAdapter;
import com.github.chengang.albumcloud.dagger.DaggerActivity;
import com.github.chengang.albumcloud.model.bean.Image;
import com.github.chengang.library.utils.FileUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListListener;
import cn.bmob.v3.listener.UploadBatchListener;


public class UploadActivity extends DaggerActivity implements View.OnClickListener {

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.iv_btn_upload)
    AppCompatImageButton mUploadBtn;

    private UploadImageAdapter mAdapter;

    private List<Uri> uploadImageUriList;
    private List<String> uploadImageList;
    private List<BmobObject> images;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_upload);

        //init toolbar
        mToolbar.setTitle("上传");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("上传");
        }

        mUploadBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Bundle uriBundle = getIntent().getBundleExtra("uriBundle");
        uploadImageUriList = uriBundle.getParcelableArrayList("uriData");
        //init adapter
        mAdapter = new UploadImageAdapter(this, uploadImageUriList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_btn_upload:
                upload();
                break;
            default:
                break;
        }
    }

    /**
     * upload here
     */
    private void upload() {
        arrangeData();
        BmobFile.uploadBatch(uploadImageList.toArray(new String[uploadImageList.size()]), new UploadBatchListener() {

            @Override
            public void onSuccess(List<BmobFile> files, List<String> urls) {
                if (urls.size() == uploadImageList.size()) {//如果数量相等，则代表文件全部上传完成
                    Toast.makeText(UploadActivity.this, "上传成功", Toast.LENGTH_LONG).show();
                    for (String url : urls) {
                        images.add(new Image(url, "upload", ""));
                    }
                    insertDatas(images);
                }
            }

            @Override
            public void onError(int statuscode, String errormsg) {
                Toast.makeText(UploadActivity.this, "上传成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProgress(int curIndex, int curPercent, int total, int totalPercent) {
                Log.d("upload progress", totalPercent + "");
            }
        });
    }

    private void insertDatas(List<BmobObject> images) {
        new BmobBatch().insertBatch(images).doBatch(new QueryListListener<BatchResult>() {

            @Override
            public void done(List<BatchResult> o, BmobException e) {
                if(e==null){
                    for(int i=0;i<o.size();i++){
                        BatchResult result = o.get(i);
                        BmobException ex =result.getError();
                        if(ex==null){
//                            log("第"+i+"个数据批量添加成功："+result.getCreatedAt()+","+result.getObjectId()+","+result.getUpdatedAt());
                        }else{
//                            log("第"+i+"个数据批量添加失败："+ex.getMessage()+","+ex.getErrorCode());
                        }
                    }
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    /**
     * arrange data for upload
     */
    private void arrangeData() {
        if (uploadImageList == null) {
            uploadImageList = new ArrayList<>();
        } else {
            uploadImageList.clear();
        }
        if(images == null){
            images = new ArrayList<>();
        }else {
            images.clear();
        }
        for (Uri uri : uploadImageUriList) {
            uploadImageList.add(FileUtil.getRealFilePath(this,uri));
        }
    }
}
