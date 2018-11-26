package com.guomai.dushuhui.comment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseBackFragment;
import com.guomai.dushuhui.comment.adapter.BookCommentAdapter;
import com.guomai.dushuhui.comment.model.CommentMultiItem;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/17.
 */

public class PublishCommentFragment extends BaseBackFragment {
    public static PublishCommentFragment newInstance() {

        Bundle args = new Bundle();

        PublishCommentFragment fragment = new PublishCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "PublishCommentFragment";

    @BindView(R.id.comment)
    EditText edit_publish;
   /* @BindView(R.id.yes_image)
    ImageView yes_image;*/
    @BindView(R.id.is_share)
    CheckedTextView is_share;
//    @BindView(R.id.determine)
//    ImageView btn_determine;
    @BindView(R.id.determine)
    Button btn_determine;

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;


    private View mRoot;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*mRoot = inflater.inflate(R.layout.frag_common_recycle,
                container, false);*/
        mRoot = inflater.inflate(R.layout.frag_publishcomment_page,
                container, false);
//        mainActivity = (MyMainActivity) getActivity();
//        mainActivity.initHeadPanel(Constant.FRAGMENT_FLAG_COMMING_SOON);
        ButterKnife.bind(this,mRoot);

        initView();
        return mRoot;
    }

    private boolean isShared = true;
    String publishContent;

    @OnClick({R.id.is_share, R.id.determine, R.id.comment})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.is_share:
                if(isShared){
                    is_share.setChecked(true);
                }else {
                    is_share.setChecked(false);
                }
                isShared = !isShared;
                //Toast.makeText(getContext(), "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.determine:
                //Toast.makeText(getContext(), "分享确定", Toast.LENGTH_SHORT).show();
                //if(TextUtils.isEmpty(publishContent) && !is_share.isChecked()){
                    SharePageFragment sharePageFragment = SharePageFragment.newInstance();
                    start(sharePageFragment);
                    Toast.makeText(getContext(), "分享确定", Toast.LENGTH_SHORT).show();
               // }
                break;
            case R.id.comment:

               /* if(edit_publish.getText().toString() != null){
                    btn_determine.setBackgroundResource(R.drawable.btn_determine);
                    publishContent = edit_publish.getText().toString();
                }
                if(edit_publish.getText().toString() == null){
                    btn_determine.setBackgroundResource(R.drawable.btn_defaut_determine);
                    publishContent = edit_publish.getText().toString();
                }*/
                break;
            default:
                break;
        }
    }

    private boolean flag = true;  //控制只更写入一次“确定”按钮的背景

    private void initView() {
        initToolbarNav(mToolBar);

        tv_title.setText("发表书评");
        tv_title.setTextColor(Color.parseColor("#333333"));
        tv_title.setTextSize(18);
        mToolBar.setNavigationIcon(R.drawable.nav_back);

        edit_publish.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!TextUtils.isEmpty(edit_publish.getText().toString().trim())){


                    if (flag) {
                        btn_determine.setBackgroundResource(R.drawable.btn_determine);
                        flag = false;
                    }

                }else{
                    btn_determine.setBackgroundResource(R.drawable.btn_defaut_determine);
                    flag = true;
                }
                publishContent = edit_publish.getText().toString();
                /*if(edit_publish.getText().toString() != ""){
                    btn_determine.setBackgroundResource(R.drawable.btn_determine);
                    publishContent = edit_publish.getText().toString();
                }else {
                    btn_determine.setBackgroundResource(R.drawable.btn_defaut_determine);
                    publishContent = edit_publish.getText().toString();
                }*/
                /*if(edit_publish.getText().toString() == null){
                    btn_determine.setBackgroundResource(R.drawable.btn_defaut_determine);
                    publishContent = edit_publish.getText().toString();
                }*/
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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