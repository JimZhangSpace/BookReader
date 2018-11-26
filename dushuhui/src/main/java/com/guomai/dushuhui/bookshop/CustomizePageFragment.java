package com.guomai.dushuhui.bookshop;

import android.content.Intent;
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
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseBackFragment;
import com.guomai.dushuhui.bookdetails.BookDetailsPageFragment;
import com.guomai.dushuhui.bookdetails.model.BookDetailsMultiItem;
import com.guomai.dushuhui.bookshop.adapter.CustomizePageAdapter;
import com.guomai.dushuhui.bookshop.model.TestData;
import com.guomai.dushuhui.main.MainFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/9/12.
 */

public class CustomizePageFragment extends BaseBackFragment {
    public static CustomizePageFragment newInstance() {

        Bundle args = new Bundle();

        CustomizePageFragment fragment = new CustomizePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "CustomizePageFragment";

    @BindView(R.id.recyclerView)
    RecyclerView listView;
    @BindView(R.id.loading_layout)
    View loading_layout;
    @BindView(R.id.tv_list_null)
    TextView tv_list_null;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;


    private View mRoot;

    private CustomizePageAdapter adapter;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*mRoot = inflater.inflate(R.layout.frag_common_recycle,
                container, false);*/
        mRoot = inflater.inflate(R.layout.frag_custom_page,
                container, false);
//        mainActivity = (MyMainActivity) getActivity();
//        mainActivity.initHeadPanel(Constant.FRAGMENT_FLAG_COMMING_SOON);
        ButterKnife.bind(this,mRoot);

        initView();
        return mRoot;
    }


    private void initView() {
        initToolbarNav(mToolBar);
        tv_title.setText("小编荐书");
        tv_title.setTextColor(Color.parseColor("#333333"));
        tv_title.setTextSize(18);
        //tv_list_null.setText("暂无路演");
        mToolBar.setNavigationIcon(R.drawable.nav_back);
        loading_layout.setVisibility(View.GONE);

        adapter = new CustomizePageAdapter(R.layout.custom_list_item);

//        adapter.addHeaderView(bannerViewHeader);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.setAdapter(adapter);
        List<TestData> list = new ArrayList<>();
        list.add(new TestData());
        list.add(new TestData());
        adapter.setNewData(list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BookDetailsPageFragment bookDetailsPageFragment = BookDetailsPageFragment.newInstance();

                start(bookDetailsPageFragment);
            }
        });
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

        mRefreshLayout.setEnableLoadMore(false);
       /* adapter = new TodaySelectionAdapter(R.layout.today_selection_list_item);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter a, View view, int position) {
                if(MyApplication.getInst().hasLogin) {
                    toRoadShow(adapter.getItem(position));
                }
                else{
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });*/
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        setClickListener();
    }

    private void setClickListener() {
        /*adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter a, View view, int position) {
                BookDetailsMultiItem item = adapter.getItem(position);
                switch (adapter.get)
            }
        });*/
    }

}