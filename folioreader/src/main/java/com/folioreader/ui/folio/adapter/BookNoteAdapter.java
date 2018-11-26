package com.folioreader.ui.folio.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.folioreader.R;
import com.folioreader.model.HighlightImpl;

import java.text.SimpleDateFormat;

public class BookNoteAdapter extends BaseQuickAdapter<HighlightImpl,BaseViewHolder> {
    public BookNoteAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HighlightImpl item) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        helper.setText(R.id.tv_title,item.getChapterTitle())
                .setText(R.id.tv_time,sdf.format(item.getDate()))
        .setText(R.id.tv_note,item.getNote())
        .setText(R.id.tv_page,String.valueOf(item.getShowPageNumber()))
        ;
        helper.addOnClickListener(R.id.tv_delete);
    }
}
