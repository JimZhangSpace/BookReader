package com.guomai.dushuhui.bookdetails.adapter;

import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.bookdetails.model.BookDetailsMultiItem;

import java.util.List;

import cn.iwgang.simplifyspan.SimplifySpanBuild;
import cn.iwgang.simplifyspan.unit.SpecialTextUnit;

/**
 * Created by Administrator on 2018/9/17.
 */

public class BookDetailsPageAdapter extends BaseMultiItemQuickAdapter<BookDetailsMultiItem, BaseViewHolder> {
    public BookDetailsPageAdapter(List<BookDetailsMultiItem> itemList){
        super(itemList);
        addItemType(BookDetailsMultiItem.ONE, R.layout.book_details_list_item_one);
        addItemType(BookDetailsMultiItem.TWO, R.layout.book_details_list_item_two);
        addItemType(BookDetailsMultiItem.THREE, R.layout.book_details_list_item_three);
        addItemType(BookDetailsMultiItem.FOUR, R.layout.book_details_list_item_four);
        addItemType(BookDetailsMultiItem.FIVE, R.layout.book_details_list_item_five);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookDetailsMultiItem item) {
        if(helper.getItemViewType() == BookDetailsMultiItem.ONE){
            helper
                    .addOnClickListener(R.id.leadread)
                    .addOnClickListener(R.id.book_listen)
                    .addOnClickListener(R.id.book_paper)
                    .addOnClickListener(R.id.book_shelf)
                    .addOnClickListener(R.id.book_share);
        }else if(helper.getItemViewType() == BookDetailsMultiItem.TWO){
            helper.addOnClickListener(R.id.layout_chapter);
            TextView textView = helper.getView(R.id.book_introduce_content);
            SimplifySpanBuild currentBuild = new SimplifySpanBuild();
            currentBuild.append(new SpecialTextUnit("内容简介：",Color.parseColor("#000000"),15));
            currentBuild.append(new SpecialTextUnit("降低了快速减肥拉萨的房间按理说发动机艰苦奋斗是老" +
                    "款的房间数量即" +
                    "可离开拉风",Color.parseColor("#000000"), 13));
            textView.setText(currentBuild.build());
        }else if(helper.getItemViewType() == BookDetailsMultiItem.THREE){
            helper.addOnClickListener(R.id.book_write_comment);
        }else if(helper.getItemViewType() == BookDetailsMultiItem.FOUR){
            helper.addOnClickListener(R.id.praise)
                    .addOnClickListener(R.id.comment_list);
        }else if(helper.getItemViewType() == BookDetailsMultiItem.FIVE){
                    helper.addOnClickListener(R.id.entry_shuquan);
        }
    }
}

