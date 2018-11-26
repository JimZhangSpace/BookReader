package com.guomai.dushuhui.leadread;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseFragment;
import com.guomai.dushuhui.base.HeaderViewPagerFragment;
import com.guomai.dushuhui.bookshop.adapter.CustomizePageAdapter;
import com.guomai.dushuhui.bookshop.model.TestData;
import com.guomai.dushuhui.leadread.adapter.LeadReadPageAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;


public class LeadReadPageFragment extends SupportFragment {

    /**当前页面索引，0表示推荐领读，1表示我的领读*/
    public int index;
    @BindView(R.id.recyclerView)
    RecyclerView listView;
    @BindView(R.id.loading_layout)
    View loading_layout;
    @BindView(R.id.tv_list_null)
    TextView tv_list_null;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    LeadReadPageAdapter adapter;

    public static LeadReadPageFragment newInstance() {

        Bundle args = new Bundle();
        LeadReadPageFragment fragment = new LeadReadPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "LeadReadPageFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_leadread_page, container, false);
        ButterKnife.bind(this,view);

        initView(view);
        return view;
    }

    private void initView(View view) {
        adapter = new LeadReadPageAdapter(R.layout.tuijian_list_item);

//        adapter.addHeaderView(bannerViewHeader);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.setAdapter(adapter);
        List<TestData> list = new ArrayList<>();
        list.add(new TestData());
        list.add(new TestData());
        adapter.setNewData(list);

        loading_layout.setVisibility(View.GONE);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull  RefreshLayout refreshLayout) {
                listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading_layout.setVisibility(View.GONE);
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                    }
                },1000);
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
                },1000);
            }
        });


        mRefreshLayout.setEnableLoadMore(true);



    }

//    @Override
//    public View getScrollableView() {
//        return listView;
//    }
}
