package com.github.chengang.albumcloud.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by 陈岗不行陈 on 2017/7/30.
 * <p>
 * 类别与图片关联表
 */

public class ImageCategoryRel extends BmobObject {

    private String imageId;//图片id
    private String categoryId;//类别id

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
