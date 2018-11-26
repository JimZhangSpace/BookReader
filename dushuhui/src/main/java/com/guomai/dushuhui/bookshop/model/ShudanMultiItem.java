package com.guomai.dushuhui.bookshop.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2018/9/18.
 */

public class ShudanMultiItem implements MultiItemEntity {
    public static final int HEADER = 1;
    public static final int COMMENT = 2;
    public static final int BOOKS = 3;

    public int itemType;
    public String title;

    @Override
    public int getItemType() {
        return itemType;
    }

}