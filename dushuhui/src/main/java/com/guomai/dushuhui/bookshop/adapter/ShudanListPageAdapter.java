package com.guomai.dushuhui.bookshop.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.bookshop.model.RcmdMultiItem;
import com.guomai.dushuhui.bookshop.model.ShudanMultiItem;

import java.util.List;

/**
 * Created by Administrator on 2018/9/18.
 */

public class ShudanListPageAdapter extends BaseMultiItemQuickAdapter<ShudanMultiItem, BaseViewHolder> {
    public ShudanListPageAdapter(List<ShudanMultiItem> itemList){
        super(itemList);
        addItemType(ShudanMultiItem.HEADER, R.layout.recommendbook_details_header);
        addItemType(ShudanMultiItem.COMMENT, R.layout.recommendbook_comment_list_item);
        addItemType(ShudanMultiItem.BOOKS, R.layout.recommendbook_books_list_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShudanMultiItem item) {
        if(helper.getItemViewType() == ShudanMultiItem.HEADER){
           /* helper
                    .addOnClickListener(R.id.bangdan)
                    .addOnClickListener(R.id.tejia)
                    .addOnClickListener(R.id.shoushu)
                    .addOnClickListener(R.id.shudan);*/
        }else if(helper.getItemViewType() == ShudanMultiItem.COMMENT){
            helper.addOnClickListener(R.id.more_comment);
                   /* .addOnClickListener(R.id.tv_more)
                    .addOnClickListener(R.id.book_card_one)
                    .addOnClickListener(R.id.book_card_two)
                    .addOnClickListener(R.id.book_card_three);*/
        }else if(helper.getItemViewType() == ShudanMultiItem.BOOKS){
            /*helper.addOnClickListener(R.id.layout_root)
                    .addOnClickListener(R.id.tv_more)
                    .addOnClickListener(R.id.book_card_one)
                    .addOnClickListener(R.id.book_card_two)
                    .addOnClickListener(R.id.book_card_three);*/
        }
    }
}
