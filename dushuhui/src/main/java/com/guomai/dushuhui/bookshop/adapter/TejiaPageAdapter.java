package com.guomai.dushuhui.bookshop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.guomai.dushuhui.bookshop.model.TestData;

/**
 * Created by Administrator on 2018/9/12.
 */

public class TejiaPageAdapter extends BaseQuickAdapter<TestData,BaseViewHolder> {


    public TejiaPageAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestData item) {
        if (helper.getAdapterPosition() == 0){
            // helper.getView(R.id.interval).setVisibility(View.GONE);
        }
        if(helper.getItemViewType() == item.T1){
            //helper.setText(R.id.book_author,"nihao");
        }else if(helper.getItemViewType() == item.T2){

        }else if(helper.getItemViewType() == item.T3){

        }
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


    }
}


