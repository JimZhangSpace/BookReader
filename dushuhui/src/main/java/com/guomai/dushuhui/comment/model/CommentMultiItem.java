package com.guomai.dushuhui.comment.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2018/9/17.
 */

public class CommentMultiItem implements MultiItemEntity {
    public static final int BOOK = 1;
    public static final int COMEMNTHEADER = 2;
    public static final int COMMENT = 3;

    public int itemType;
    public String title;

    @Override
    public int getItemType() {
        return itemType;
    }
}

