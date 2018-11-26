package com.guomai.dushuhui.bookshop.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.guomai.dushuhui.model.Banners;
import com.guomai.dushuhui.model.Data;

import java.util.List;

/**
 * Created by Administrator on 2018/9/11.
 */

public class RcmdMultiItem implements MultiItemEntity {
    public static final int Banner = 1;
    public static final int Bangdang = 2;
    public static final int jianshu = 3;
    public static final int changxiao = 4;

    public int itemType;
    public String title;

    public Data data;
    public Banners banners;

    @Override
    public int getItemType() {
        return itemType;
    }

}
