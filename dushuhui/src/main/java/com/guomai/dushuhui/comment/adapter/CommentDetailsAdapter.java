package com.guomai.dushuhui.comment.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.comment.model.ComDetailsMultiItem;
import com.guomai.dushuhui.comment.model.CommentMultiItem;

import java.util.List;

/**
 * Created by Administrator on 2018/9/18.
 */

public class CommentDetailsAdapter extends BaseMultiItemQuickAdapter<ComDetailsMultiItem, BaseViewHolder> {
    public CommentDetailsAdapter(List<ComDetailsMultiItem> itemList){
        super(itemList);
        addItemType(ComDetailsMultiItem.MINE, R.layout.comment_details_mine_list_item);
        addItemType(ComDetailsMultiItem.REPLY, R.layout.comment_details_reply_list_item);
        addItemType(ComDetailsMultiItem.COMMENT, R.layout.comment_details_content);
        addItemType(ComDetailsMultiItem.NOMORE, R.layout.comment_details_nomore_list_item);
       /* addItemType(BookDetailsMultiItem.FOUR, R.layout.book_details_list_item_four);
        addItemType(BookDetailsMultiItem.FIVE, R.layout.book_details_list_item_five);*/
    }

    @Override
    protected void convert(BaseViewHolder helper, ComDetailsMultiItem item) {
        if(helper.getItemViewType() == ComDetailsMultiItem.MINE){
           /* helper
                    .addOnClickListener(R.id.leadread)
                    .addOnClickListener(R.id.book_listen)
                    .addOnClickListener(R.id.book_paper)
                    .addOnClickListener(R.id.book_shelf)
                    .addOnClickListener(R.id.book_share);*/
        }else if(helper.getItemViewType() == ComDetailsMultiItem.REPLY){
            //helper.addOnClickListener(R.id.layout_chapter);
        }else if(helper.getItemViewType() == ComDetailsMultiItem.COMMENT){
            /*helper.addOnClickListener(R.id.praise)
                    .addOnClickListener(R.id.comment_list);*/
        }/*else if(helper.getItemViewType() == BookDetailsMultiItem.FOUR){
            helper.addOnClickListener(R.id.praise)
                    .addOnClickListener(R.id.comment_list);
        }else if(helper.getItemViewType() == BookDetailsMultiItem.FIVE){
            helper.addOnClickListener(R.id.entry_shuquan);
        }*/
    }
}
