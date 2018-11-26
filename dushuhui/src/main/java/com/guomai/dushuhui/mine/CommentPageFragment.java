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
import android.widget.TextView;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseBackFragment;
import com.guomai.dushuhui.bookshop.CustomizePageFragment;
import com.guomai.dushuhui.bookshop.adapter.CustomizePageAdapter;
import com.guomai.dushuhui.bookshop.model.TestData;
import com.guomai.dushuhui.mine.adapter.CommentPageAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/15.
 */

public class CommentPageFragment extends BaseBackFragment {
    public static CommentPageFragment newInstance() {

        Bundle args = new Bundle();

        CommentPageFragment fragment = new CommentPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "CommentPageFragment";

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

    private CommentPageAdapter adapter;

    private View bannerMyCommentView;
    private View bannerMyComment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*mRoot = inflater.inflate(R.layout.frag_common_recycle,
                container, false);*/
        mRoot = inflater.inflate(R.layout.frag_common_recycle,
                container, false);
//        mainActivity = (MyMainActivity) getActivity();
//        mainActivity.initHeadPanel(Constant.FRAGMENT_FLAG_COMMING_SOON);
        ButterKnife.bind(this,mRoot);

        initView();
        return mRoot;
    }


    private void initView() {
        initToolbarNav(mToolBar);
        tv_title.setText("我的评论");
        tv_title.setTextColor(Color.parseColor("#333333"));
        tv_title.setTextSize(18);
        //tv_list_null.setText("暂无路演");
        mToolBar.setNavigationIcon(R.drawable.nav_back);
        loading_layout.setVisibility(View.GONE);




        adapter = new CommentPageAdapter(R.layout.comment_list_item);
        bannerMyCommentView = View.inflate(getActivity(),R.layout.header_comment, null);
        bannerMyComment = bannerMyCommentView.findViewById(R.id.header_comment);
        ViewGroup parentViewGroup = (ViewGroup) bannerMyComment.getParent();
        if (parentViewGroup != null ) {
            parentViewGroup.removeView(bannerMyComment);
        }
        adapter.addHeaderView(bannerMyComment);

//        adapter.addHeaderView(bannerViewHeader);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.setAdapter(adapter);
        List<TestData> list = new ArrayList<>();
        list.add(new TestData());
        list.add(new TestData());
        adapter.setNewData(list);

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

    }

}
