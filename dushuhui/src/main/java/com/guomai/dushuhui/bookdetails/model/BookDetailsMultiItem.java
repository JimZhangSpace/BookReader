package com.guomai.dushuhui.bookdetails.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2018/9/17.
 */

public class BookDetailsMultiItem implements MultiItemEntity {
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE= 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;

    public int itemType;
    public String title;

    @Override
    public int getItemType() {
        return itemType;
    }

}
