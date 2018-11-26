package com.guomai.dushuhui.bookshop;

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

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.bookshop.adapter.TejiaPageAdapter;
import com.guomai.dushuhui.bookshop.model.TestData;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2018/9/12.
 */

public class TejiaPageFragment extends SupportFragment {

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



    private TejiaPageAdapter adapter;

    private View bannerTejiaHeader;
    private View bannerTejia;

    public static TejiaPageFragment newInstance() {

        Bundle args = new Bundle();
        TejiaPageFragment fragment = new TejiaPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "TejiaPageFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_common_recycleview, container, false);
        ButterKnife.bind(this,view);

        initView(view);
        return view;
    }

    private void initView(View view) {
        adapter = new TejiaPageAdapter(R.layout.tejia_list_item);

        bannerTejiaHeader = View.inflate(getActivity(),R.layout.tejia_banner, null);
        bannerTejia = bannerTejiaHeader.findViewById(R.id.bannerTejia);
        bannerTejia.setVisibility(View.VISIBLE);
        //adapter.removeHeaderView(bannerTejiaHeader);
        ViewGroup parentViewGroup = (ViewGroup) bannerTejia.getParent();
        if (parentViewGroup != null ) {
            parentViewGroup.removeView(bannerTejia);
        }

        adapter.addHeaderView(bannerTejia);

        List<TestData> list = new ArrayList<>();
        list.add(new TestData());
        list.add(new TestData());


        adapter.setNewData(list);

//        adapter.addHeaderView(bannerViewHeader);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.setAdapter(adapter);


/*        ArrayList<RcmdMultiItem> list1 = new ArrayList<>();
        RcmdMultiItem item1 = new RcmdMultiItem();
        item1.itemType = 2;

        RcmdMultiItem item2 = new RcmdMultiItem();
        item2.itemType = 3;
        RcmdMultiItem item3 = new RcmdMultiItem();
        item3.itemType = 4;
        list1.add(item1);
        list1.add(item2);
        list1.add(item3);
        adaptert = new RcmdBookAdapter(list1);

//        adapter.addHeaderView(bannerViewHeader);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.setAdapter(adaptert);                                     */

        loading_layout.setVisibility(View.GONE);

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

        mRefreshLayout.setEnableLoadMore(false);         ////

    }

//    @Override
//    public View getScrollableView() {
//        return listView;
//    }
}
