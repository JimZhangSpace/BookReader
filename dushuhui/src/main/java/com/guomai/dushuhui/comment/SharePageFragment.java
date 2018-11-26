package com.guomai.dushuhui.comment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseBackFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/17.
 */

public class SharePageFragment extends BaseBackFragment {
    public static SharePageFragment newInstance() {

        Bundle args = new Bundle();

        SharePageFragment fragment = new SharePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "SharePageFragment";

    @BindView(R.id.btn_close)
    Button btn_close;
    @BindView(R.id.share_wechat)
    Button btn_share_wechat;
    @BindView(R.id.share_weibo)
    Button btn_share_weibo;
    /* @BindView(R.id.yes_image)
     ImageView yes_image;*/
    @BindView(R.id.save)
    CheckedTextView save;
    //    @BindView(R.id.determine)
//    ImageView btn_determine;
    @BindView(R.id.anonymous)
    CheckedTextView anonumous;




    private View mRoot;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*mRoot = inflater.inflate(R.layout.frag_common_recycle,
                container, false);*/
        mRoot = inflater.inflate(R.layout.frag_comment_share_page,
                container, false);
//        mainActivity = (MyMainActivity) getActivity();
//        mainActivity.initHeadPanel(Constant.FRAGMENT_FLAG_COMMING_SOON);
        ButterKnife.bind(this,mRoot);

        initView();
        return mRoot;
    }

    private boolean isSave = true;
    private boolean isAnonymous = false;
    String publishContent;

    @OnClick({R.id.share_wechat, R.id.share_weibo, R.id.save, R.id.anonymous, R.id.btn_close})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.share_wechat:      //分享到微信

                //Toast.makeText(getContext(), "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share_weibo:      //分享到微博


                break;
            case R.id.save:
                if(isSave){
                    save.setChecked(true);
                }else {
                    save.setChecked(false);
                }
                isSave = !isSave;
               /* if(edit_publish.getText().toString() != null){
                    btn_determine.setBackgroundResource(R.drawable.btn_determine);
                    publishContent = edit_publish.getText().toString();
                }
                if(edit_publish.getText().toString() == null){
                    btn_determine.setBackgroundResource(R.drawable.btn_defaut_determine);
                    publishContent = edit_publish.getText().toString();
                }*/
                break;
            case R.id.anonymous:
                if(isAnonymous){
                    anonumous.setChecked(true);
                }else {
                    anonumous.setChecked(false);
                }
                isAnonymous = !isAnonymous;
                break;
            case R.id.btn_close:
                pop();
                break;
            default:
                break;
        }
    }

//    private boolean flag = true;  //控制只更写入一次“确定”按钮的背景

    private void initView() {


        /*loading_layout.setVisibility(View.GONE);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading_layout.setVisibility(View.GONE);
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                    }
                }, 1000);
            }

            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading_layout.setVisibility(View.GONE);
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                    }
                }, 1000);
            }
        });

        mRefreshLayout.setEnableLoadMore(false);*/

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }
}
