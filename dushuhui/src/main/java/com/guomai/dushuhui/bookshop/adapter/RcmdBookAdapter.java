package com.guomai.dushuhui.bookshop.adapter;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.bookshop.RcmdBookFragment;
import com.guomai.dushuhui.bookshop.model.RcmdMultiItem;
import com.guomai.dushuhui.bookshop.model.TestData;
import com.guomai.dushuhui.model.Ebooks;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/11.
 */

public class RcmdBookAdapter extends BaseMultiItemQuickAdapter<RcmdMultiItem, BaseViewHolder> {
    public RcmdBookAdapter(List<RcmdMultiItem> itemList){
        super(itemList);
        addItemType(RcmdMultiItem.Bangdang, R.layout.home_bangdan_list_item);
        addItemType(RcmdMultiItem.jianshu, R.layout.home_customview_list_item);
        addItemType(RcmdMultiItem.changxiao, R.layout.home_customview_list_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, RcmdMultiItem item) {
        if(helper.getItemViewType() == RcmdMultiItem.Bangdang){
            helper
                    .addOnClickListener(R.id.bangdan)
                    .addOnClickListener(R.id.tejia)
                    .addOnClickListener(R.id.shoushu)
                    .addOnClickListener(R.id.shudan);
        }else if(helper.getItemViewType() == RcmdMultiItem.jianshu){
            helper.addOnClickListener(R.id.layout_root)
                    .addOnClickListener(R.id.tv_more)
                    .addOnClickListener(R.id.book_card_one)
                    .addOnClickListener(R.id.book_card_two)
                    .addOnClickListener(R.id.book_card_three);
            helper.setText(R.id.custom_title, item.data.getName());

            List<ImageView> imageViews = new ArrayList<>();
            imageViews.add((ImageView)helper.getView(R.id.url_1));
            imageViews.add((ImageView)helper.getView(R.id.url_2));
            imageViews.add((ImageView)helper.getView(R.id.url_3));
            updateBookImage(item.data.getEbooks(),imageViews);


        }else if(helper.getItemViewType() == RcmdMultiItem.changxiao){
            helper.addOnClickListener(R.id.layout_root)
                    .addOnClickListener(R.id.tv_more)
                    .addOnClickListener(R.id.book_card_one)
                    .addOnClickListener(R.id.book_card_two)
                    .addOnClickListener(R.id.book_card_three);
        }else if(helper.getItemViewType() == RcmdMultiItem.Banner){
            /*Banner banner = helper.getView(R.id.banner);
            Glide.with(mContext.getApplicationContext())
                    .load(item.banners.getImg_url())
                    .into((ImageView)banner);*/
        }
    }

    private void updateBookImage(List<Ebooks> ebooks, List<ImageView> imageViews) {
        for(int i=0;i<ebooks.size();i++){
            Glide.with(mContext.getApplicationContext())
                    .load(ebooks.get(i).getCover_url())
                    .into(imageViews.get(i));
        }
    }
}
