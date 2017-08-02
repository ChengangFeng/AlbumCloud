package com.github.chengang.albumcloud.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by 陈岗不行陈 on 2017/7/30.
 * <p>
 * 图片表
 */

public class Image extends BmobObject{

    private String url;
    private String description;
    private String categoryRelId;

    public Image(String url, String description, String categoryRelId) {
        this.url = url;
        this.description = description;
        this.categoryRelId = categoryRelId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryRelId() {
        return categoryRelId;
    }

    public void setCategoryRelId(String categoryRelId) {
        this.categoryRelId = categoryRelId;
    }
}
