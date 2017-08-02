package com.github.chengang.albumcloud.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.chengang.albumcloud.R;
import com.github.chengang.library.adapter.BaseRecyclerViewAdapter;
import com.github.chengang.library.adapter.BaseRecyclerViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 陈岗不行陈 on 2017/7/27.
 * <p>
 * 上传图片展示适配器
 */

public class UploadImageAdapter extends BaseRecyclerViewAdapter<Uri> {

    private Context mContext;

    public UploadImageAdapter(@NonNull Context context, @NonNull List<Uri> datas) {
        super(context, datas);
        this.mContext = context;
    }

    @Override
    protected int getViewType(int position, @NonNull Uri data) {
        return 0;
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_upload_image;
    }

    @Override
    protected BaseRecyclerViewHolder getViewHolder(ViewGroup parent, View rootView, int viewType) {
        return new UploadImageVH(rootView, viewType);
    }

    class UploadImageVH extends BaseRecyclerViewHolder<Uri> {

        @BindView(R.id.image)
        ImageView mImageView;

        UploadImageVH(View itemView, int viewType) {
            super(itemView, viewType);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBindData(Uri imageUrl, int position) {
            Glide.with(mContext).load(imageUrl).listener(new RequestListener<Uri, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
                    Log.e("Glide error", e.toString());
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    return false;
                }
            }).into(mImageView);
        }
    }

}
