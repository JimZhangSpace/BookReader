package com.folioreader.ui.folio.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.folioreader.R;
import com.folioreader.model.BookMark;

public class BookMarkAdapter extends BaseQuickAdapter<BookMark,BaseViewHolder> {
    public BookMarkAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookMark item) {
        helper.setText(R.id.tv_time,item.getDate())
                .setText(R.id.tv_title,item.getChapterTitle())
                .setText(R.id.tv_page,item.getShowPageNumber()+"");
        helper.addOnClickListener(R.id.tv_delete);
    }
}
