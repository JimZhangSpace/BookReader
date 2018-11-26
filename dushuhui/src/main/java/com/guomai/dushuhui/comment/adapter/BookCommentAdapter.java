package com.guomai.dushuhui.comment.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.bookdetails.model.BookDetailsMultiItem;
import com.guomai.dushuhui.comment.model.CommentMultiItem;

import java.util.List;

/**
 * Created by Administrator on 2018/9/17.
 */

public class BookCommentAdapter extends BaseMultiItemQuickAdapter<CommentMultiItem, BaseViewHolder> {
    public BookCommentAdapter(List<CommentMultiItem> itemList){
        super(itemList);
        addItemType(CommentMultiItem.BOOK, R.layout.all_comment_bookintroduce_list_item);
        addItemType(CommentMultiItem.COMEMNTHEADER, R.layout.all_comment_header_list_item);
        addItemType(CommentMultiItem.COMMENT, R.layout.book_details_list_item_four);
       /* addItemType(BookDetailsMultiItem.FOUR, R.layout.book_details_list_item_four);
        addItemType(BookDetailsMultiItem.FIVE, R.layout.book_details_list_item_five);*/
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentMultiItem item) {
        if(helper.getItemViewType() == CommentMultiItem.BOOK){
           /* helper
                    .addOnClickListener(R.id.leadread)
                    .addOnClickListener(R.id.book_listen)
                    .addOnClickListener(R.id.book_paper)
                    .addOnClickListener(R.id.book_shelf)
                    .addOnClickListener(R.id.book_share);*/
        }else if(helper.getItemViewType() == CommentMultiItem.COMEMNTHEADER){
            //helper.addOnClickListener(R.id.layout_chapter);
        }else if(helper.getItemViewType() == CommentMultiItem.COMMENT){
            helper.addOnClickListener(R.id.praise)
            .addOnClickListener(R.id.comment_list);
        }/*else if(helper.getItemViewType() == BookDetailsMultiItem.FOUR){
            helper.addOnClickListener(R.id.praise)
                    .addOnClickListener(R.id.comment_list);
        }else if(helper.getItemViewType() == BookDetailsMultiItem.FIVE){
            helper.addOnClickListener(R.id.entry_shuquan);
        }*/
    }
}
