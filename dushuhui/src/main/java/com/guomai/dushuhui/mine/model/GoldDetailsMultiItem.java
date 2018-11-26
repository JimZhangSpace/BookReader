package com.guomai.dushuhui.mine.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2018/9/19.
 */

public class GoldDetailsMultiItem implements MultiItemEntity {
    public static final int MONTH = 1;
    public static final int DAY = 2;

    public int itemType;
    public boolean isOpen = false;

    @Override
    public int getItemType() {
        return itemType;
    }

    public boolean isOpen() {
        return isOpen;
    }

}
