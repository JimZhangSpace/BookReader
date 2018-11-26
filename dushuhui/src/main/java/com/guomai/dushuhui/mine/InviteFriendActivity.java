package com.guomai.dushuhui.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aries.ui.widget.BasisDialog;
import com.aries.ui.widget.action.sheet.UIActionSheetDialog;
import com.guomai.dushuhui.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/20.
 */

public class InviteFriendActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.qr_code_image)
    ImageView btn_save;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitefriend);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        tv_title.setText("邀请好友加入");
        tv_title.setTextColor(Color.parseColor("#333333"));
        tv_title.setTextSize(18);
        //mToolbar.setTitle("开通VIP会员");
        //mToolbar.setDisplsyShowTitileEnable(false);
        //mToolbar.set
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            mToolbar.setNavigationIcon(R.drawable.nav_back);
            actionBar.setDisplayShowTitleEnabled(false);
        }
//        UMWeb web = new UMWeb(shareServerMsg.data.targetUrl);
//        web.setTitle(shareServerMsg.data.title);
//        web.setDescription(shareServerMsg.data.desc);
//        web.setThumb(new UMImage(this, shareServerMsg.data.coverUrl));
//        ShareAction shareAction =  new ShareAction(getActivity())
//                .withMedia(web)
//                .setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.SINA)
//                .setCallback(umShareListener);
//
//        shareAction.open();
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_save.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Toast.makeText(InviteFriendActivity.this, "长按保存", Toast.LENGTH_SHORT).show();

                new UIActionSheetDialog.ListSheetBuilder(InviteFriendActivity.this)
                        .addItems(R.array.save_array)
//                        .setItemsTextColorResource(isDefaultItemColor ? R.color.colorActionSheetNormalItemText : android.R.color.holo_purple)
//                        .setTitle(isShowTitle ? "标题" : null)
                        .setCancel("取消")
//                        .setCancelMarginTop(SizeUtil.dp2px(isShowMargin ? 8 : 0))
//                        .setCancelTextColorResource(isDefaultCancelColor ? R.color.colorActionSheetNormalItemText : android.R.color.darker_gray)
                        .setOnItemClickListener(new UIActionSheetDialog.OnItemClickListener() {
                            @Override
                            public void onClick(BasisDialog dialog, View itemView, int position) {
                                Toast.makeText(InviteFriendActivity.this, "长按保存", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setCancelMarginTop(0)
                        .create().setDimAmount(0.6f).show();

                return true;
            }
        });
    }
}
