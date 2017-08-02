package com.github.chengang.albumcloud.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by 陈岗不行陈 on 2017/7/30.
 * <p>
 * 类别表
 */

public class Category extends BmobObject {

    private String name; //类别名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
