package com.guomai.dushuhui.mine.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.bookshop.model.RcmdMultiItem;
import com.guomai.dushuhui.mine.model.GoldDetailsMultiItem;

import java.util.List;

/**
 * Created by Administrator on 2018/9/19.
 */

public class GoldDetailsAdapter extends BaseMultiItemQuickAdapter<GoldDetailsMultiItem, BaseViewHolder> {
    public GoldDetailsAdapter(List<GoldDetailsMultiItem> itemList){
        super(itemList);
        addItemType(GoldDetailsMultiItem.MONTH, R.layout.mygolddetails_month_list_item);
        addItemType(GoldDetailsMultiItem.DAY, R.layout.mygolddetails_day_list_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoldDetailsMultiItem item) {
        if(helper.getItemViewType() == GoldDetailsMultiItem.MONTH){
            /*helper
                    .addOnClickListener(R.id.bangdan)
                    .addOnClickListener(R.id.tejia)
                    .addOnClickListener(R.id.shoushu)
                    .addOnClickListener(R.id.shudan);*/
//            helper.addOnClickListener(R.id.icon_right);
            ImageView iv = helper.getView(R.id.icon_right);
            iv.setImageResource(item.isOpen?R.drawable.btn_lower:R.drawable.btn_right);
        }else if(helper.getItemViewType() == GoldDetailsMultiItem.DAY){
            /*helper.addOnClickListener(R.id.layout_root)
                    .addOnClickListener(R.id.tv_more)
                    .addOnClickListener(R.id.book_card_one)
                    .addOnClickListener(R.id.book_card_two)
                    .addOnClickListener(R.id.book_card_three);*/
        }
    }
}
