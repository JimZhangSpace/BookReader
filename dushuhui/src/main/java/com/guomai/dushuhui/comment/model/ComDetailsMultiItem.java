package com.guomai.dushuhui.comment.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2018/9/18.
 */

public class ComDetailsMultiItem implements MultiItemEntity {
    public static final int MINE = 1;
    public static final int REPLY = 2;
    public static final int COMMENT = 3;
    public static final int NOMORE = 4;

    public int itemType;
    public String title;

    @Override
    public int getItemType() {
        return itemType;
    }
}
