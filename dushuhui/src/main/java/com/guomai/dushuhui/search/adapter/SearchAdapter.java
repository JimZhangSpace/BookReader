package com.guomai.dushuhui.search.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.bookshop.model.TestData;

/**
 * Created by Administrator on 2018/9/19.
 */

public class SearchAdapter extends BaseQuickAdapter<TestData,BaseViewHolder> {


    public SearchAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestData item) {
        if(item.getItemType() == TestData.T1){
            helper.getView(R.id.hot_search).setVisibility(View.VISIBLE);
            helper.getView(R.id.layout_search_tip).setVisibility(View.GONE);
            helper.getView(R.id.layout_search_empty).setVisibility(View.GONE);
            helper.getView(R.id.bg_custom).setVisibility(View.GONE);
            if(helper.getAdapterPosition() == 0){
                helper.getView(R.id.text_hot).setVisibility(View.VISIBLE);
            }else {
                helper.getView(R.id.text_hot).setVisibility(View.GONE);
            }
        }else if(item.getItemType() == TestData.T2){
            helper.getView(R.id.hot_search).setVisibility(View.GONE);
            helper.getView(R.id.layout_search_tip).setVisibility(View.VISIBLE);
            helper.getView(R.id.layout_search_empty).setVisibility(View.GONE);
            helper.getView(R.id.bg_custom).setVisibility(View.GONE);
        }else if(item.getItemType() == TestData.T3){
            helper.getView(R.id.hot_search).setVisibility(View.GONE);
            helper.getView(R.id.layout_search_tip).setVisibility(View.GONE);
            helper.getView(R.id.layout_search_empty).setVisibility(View.VISIBLE);
            helper.getView(R.id.bg_custom).setVisibility(View.GONE);
        }else if(item.getItemType() == TestData.T4){
            helper.getView(R.id.hot_search).setVisibility(View.GONE);
            helper.getView(R.id.layout_search_tip).setVisibility(View.GONE);
            helper.getView(R.id.layout_search_empty).setVisibility(View.GONE);
            helper.getView(R.id.bg_custom).setVisibility(View.VISIBLE);
        }else {
            helper.getView(R.id.hot_search).setVisibility(View.VISIBLE);
            helper.getView(R.id.layout_search_tip).setVisibility(View.GONE);
            helper.getView(R.id.layout_search_empty).setVisibility(View.GONE);
            helper.getView(R.id.bg_custom).setVisibility(View.GONE);
        }
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


    }
}
