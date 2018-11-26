package com.guomai.dushuhui.bookdetails.adapter;

import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.bookdetails.model.TestData2;
import com.guomai.dushuhui.bookshop.model.TestData;

/**
 * Created by Administrator on 2018/9/20.
 */

public class PurchaseGoldAdapter extends BaseQuickAdapter<TestData2,BaseViewHolder> {


    public PurchaseGoldAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestData2 item) {
        helper.addOnClickListener(R.id.rmb_num)
                .addOnClickListener(R.id.rmb_num2);
        if(helper.getAdapterPosition()==item.postion && item.direction==0){   //点击左边
            helper.getView(R.id.rmb_num).setBackgroundResource(R.drawable.bg_purchase_gold);
        }else if(helper.getAdapterPosition()==item.postion && item.direction==1){   //点击左边
            helper.getView(R.id.rmb_num).setBackgroundResource(R.drawable.bg_purchase_gold);
        }else {
            helper.getView(R.id.rmb_num).setBackgroundResource(R.drawable.bg_account_gold);
        }
        //helper.addOnClickListener(R.id.button_play);
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
