package com.guomai.dushuhui.bookshop.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.bookshop.model.TestData;
import com.guomai.dushuhui.model.YijiListData;

/**
 * Created by Administrator on 2018/9/11.
 */

public class BookPageAdapter  extends BaseQuickAdapter<YijiListData,BaseViewHolder> {


    public BookPageAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, YijiListData item) {
        /*if (helper.getAdapterPosition() == 0){
            helper.getView(R.id.interval).setVisibility(View.GONE);
        }*/
        /*ImageView iv_cover = helper.getView(R.id.iv_cover);
        final ImageView iv_logo = helper.getView(R.id.iv_logo);
        Glide.with(mContext.getApplicationContext()).load(item.roadshowInfo.coverUrl)
                .placeholder(R.drawable.ic_logo_default)
                .into(iv_cover);
        Glide.with(mContext.getApplicationContext()).load(item.companyInfo.logoUrl)
                .placeholder(R.drawable.default_image)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        iv_logo.setImageDrawable(resource);
                    }
                });
        helper.setText(R.id.tv_company,item.companyInfo.title)
                .setText(R.id.tv_title,item.roadshowInfo.title)
                .setText(R.id.road_start_time, TimeUtil.getDateString(item.roadshowInfo.openTime))
                .setText(R.id.road_play_times,String.format("播放%d次",item.roadshowInfo.showUserCount));*/
        //ImageView book_image = helper.getView(R.id.book_img);
       /* Glide.with(mContext.getApplicationContext()).load(item.getCover_url())
                //.placeholder(R.drawable.ic_logo_default)
                .into(book_image);*/

        helper.setText(R.id.book_name,item.getName());
               //.setText(R.id.book_author, item.getAuthor())
                //.setText(R.id.book_introduce, item.getIntroduction());
               /*  .setText(R.id.btn_upload_img, item.getCategory_name());*/


    }
}
