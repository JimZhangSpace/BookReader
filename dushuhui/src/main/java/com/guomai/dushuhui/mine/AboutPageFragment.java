package com.guomai.dushuhui.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseBackFragment;
import com.guomai.dushuhui.bookshop.model.TestData;
import com.guomai.dushuhui.mine.adapter.CommentPageAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/15.
 */

public class AboutPageFragment extends BaseBackFragment {
    public static AboutPageFragment newInstance() {

        Bundle args = new Bundle();

        AboutPageFragment fragment = new AboutPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "AboutPageFragment";

    @BindView(R.id.back)
    ImageView back;
    /*@BindView(R.id.loading_layout)
    View loading_layout;
    @BindView(R.id.tv_list_null)
    TextView tv_list_null;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;*/


    private View mRoot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*mRoot = inflater.inflate(R.layout.frag_common_recycle,
                container, false);*/
        mRoot = inflater.inflate(R.layout.frag_about_page,
                container, false);
//        mainActivity = (MyMainActivity) getActivity();
//        mainActivity.initHeadPanel(Constant.FRAGMENT_FLAG_COMMING_SOON);
        ButterKnife.bind(this,mRoot);

        initView();
        return mRoot;
    }

    @OnClick(R.id.back)
    public void onClick(View v){
        if(v.getId() == R.id.back){        //返回上一个页面
            pop();
        }
    }


    private void initView() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }

}

