package com.folioreader.ui.folio.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.folioreader.R;
import com.folioreader.model.ReaderFontModel;

public class ReaderFontAdapter extends BaseQuickAdapter <ReaderFontModel,BaseViewHolder>
{
    public ReaderFontAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReaderFontModel item) {
        ImageView iv = helper.getView(R.id.iv_select_icon);
        iv.setVisibility(item.isSelected?View.VISIBLE:View.GONE);
        helper.setText(R.id.tv_font,item.fontName);



    }
}
