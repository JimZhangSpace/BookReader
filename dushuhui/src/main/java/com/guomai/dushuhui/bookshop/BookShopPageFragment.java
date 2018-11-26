package com.guomai.dushuhui.bookshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.JsonArray;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.bookshop.adapter.BookPageAdapter;
import com.guomai.dushuhui.leadread.LeadReadPageFragment;
import com.guomai.dushuhui.model.ServerRsp;
import com.guomai.dushuhui.model.YijiListData;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.litepal.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2018/9/11.
 */

public class BookShopPageFragment extends SupportFragment {
    /**当前页面索引，0表示精选，1表示文学，2表示历史，3表示社科*/
    public int index;
    @BindView(R.id.recyclerView)
    RecyclerView listView;
    @BindView(R.id.loading_layout)
    View loading_layout;
    @BindView(R.id.tv_list_null)
    TextView tv_list_null;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private List<YijiListData> list = new ArrayList<>();
    private BookPageAdapter adapter;

    private int currentPage;
    private int pageCount = 20;

    public static BookShopPageFragment newInstance() {

        Bundle args = new Bundle();
        BookShopPageFragment fragment = new BookShopPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "BookShopPageFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_common_recycleview, container, false);
        ButterKnife.bind(this,view);
        initView(view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        currentPage = 1;
        //setClickListener();
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                currentPage ++;
                requestData(currentPage);
                /*listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading_layout.setVisibility(View.GONE);
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                    }
                },1000);*/
            }

            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                currentPage = 1;
                /*listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading_layout.setVisibility(View.GONE);
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                    }
                },1000);*/
                requestData(currentPage);
            }
        });
        mRefreshLayout.setEnableLoadMore(true);
        requestData(currentPage);
    }

    private void initView(View view) {
        adapter = new BookPageAdapter(R.layout.custom_list_item);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.setAdapter(adapter);

       // mRefreshLayout.setEnableLoadMore(true);
        /*mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
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


        mRefreshLayout.setEnableLoadMore(true);

        requestData();*/

    }

    private void requestData(int pageNum)
    {
        OkGo.<String>get("http://yapi.esctrl.com/mock/9/api/v1/category/index").tag(this)
                .params("page",pageNum)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(final Response<String> response) {

                        String body = response.body();
                        LogUtil.d("tag",body);
                        final ServerRsp serverRsp = JSON.parseObject(body,ServerRsp.class);
                        //List<YijiListData> listData = null;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               if(serverRsp!=null)
                               {
                                   List<YijiListData> listData = JSON.parseArray(serverRsp.data, YijiListData.class);
                                   Log.d("tag", "msgmsg "+list.size());
                                   if(list!=null && list.size()>0) {
                                       //tv_list_null.setVisibility(View.GONE);
                                       loading_layout.setVisibility(View.GONE);
                                       adapter.setNewData(list);
                                       currentPage++;

                                   }else {
                                       mRefreshLayout.setEnableRefresh(false);
                                   }
                               }

                               else{
                                   //提示错误
                               }
                            }
                        });


                    }

                    @Override
                    public void onError(Response<String> response) {

                    }
                });
    }

    private void postData()
    {
        OkGo.<String>post("http://yapi.esctrl.com/mock/9/api/v1/category/index").tag(this)
                .params("","")
                .params("",1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //请求成功

                    }

                    @Override
                    public void onError(Response<String> response) {

                    }
                });
    }



//    @Override
//    public View getScrollableView() {
//        return listView;
//    }
}
