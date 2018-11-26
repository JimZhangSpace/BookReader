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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.folioreader.ui.tableofcontents.adapter.TOCAdapter;

import com.google.gson.JsonArray;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.ApiConstant;
import com.guomai.dushuhui.bookdetails.BookDetailsPageFragment;
import com.guomai.dushuhui.bookdetails.model.BookDetailsMultiItem;
import com.guomai.dushuhui.bookshop.adapter.RankingPageAdapter;
import com.guomai.dushuhui.bookshop.adapter.RcmdBookAdapter;
import com.guomai.dushuhui.bookshop.model.MyBanner;
import com.guomai.dushuhui.bookshop.model.MySubjectCategory;
import com.guomai.dushuhui.bookshop.model.RcmdMultiItem;
import com.guomai.dushuhui.bookshop.model.TestData;
import com.guomai.dushuhui.config.AppConstant;
import com.guomai.dushuhui.main.MainFragment;
import com.guomai.dushuhui.model.Banners;
import com.guomai.dushuhui.model.Data;
import com.guomai.dushuhui.model.Ebooks;
import com.guomai.dushuhui.model.ServerRsp;
import com.guomai.dushuhui.util.GlideImageLoader;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.litepal.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

public class RcmdBookFragment  extends SupportFragment {
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

    private View bannerViewHeader;
    private View bannerView;
    private Banner banner;

    private RcmdBookAdapter adapter;
    RankingPageAdapter adaptert;

    private List<String> imgs;

    private int requestFlag=1;

    private int flag=0;
    private List data1List,data2List;


    public static RcmdBookFragment newInstance() {

        Bundle args = new Bundle();
        RcmdBookFragment fragment = new RcmdBookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "RcmdBookFragment";

    List<RcmdMultiItem> list;

    Integer[] images = {R.drawable.default_image, R.drawable.default_image, R.drawable.default_image,
            R.drawable.default_image, R.drawable.default_image};

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
        setClickListener();
    }

    private void initView(View view) {


        imgs = new ArrayList<>();
        list = new ArrayList<>();

        bannerViewHeader = View.inflate(getActivity(),R.layout.banner_view_header, null);
        bannerView = bannerViewHeader.findViewById(R.id.layout);
        banner =  bannerViewHeader.findViewById(R.id.banner);
        bannerView.setVisibility(View.VISIBLE);   ///

       /* List<Integer> imageViews = new ArrayList<>();
        for(int i =0;i<5;i++){
            imageViews.add(R.drawable.default_image);
        }
*/
        ViewGroup parentViewGroup = (ViewGroup) bannerView.getParent();
        if (parentViewGroup != null ) {
            parentViewGroup.removeView(bannerView);
        }


        banner.setIndicatorGravity(7);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());   //????
        //设置图片集合
      // banner.setImages(imageViews);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        banner.startAutoPlay();




        RcmdMultiItem item1 = new RcmdMultiItem();
        item1.itemType = 2;

        //RcmdMultiItem item2 = new RcmdMultiItem();
       // item2.itemType = 3;
        RcmdMultiItem item3 = new RcmdMultiItem();
        item3.itemType = 4;
        list.add(item1);
       // list.add(item2);
        list.add(item3);
        adapter = new RcmdBookAdapter(list);
      //  adapter.addHeaderView(bannerView);

//        adapter.addHeaderView(bannerViewHeader);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.setAdapter(adapter);
        adapter.addHeaderView(bannerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
/*        List<TestData> list1 = new ArrayList<>();
        list1.add(new TestData());
        adaptert = new RankingPageAdapter(R.layout.test_layout);
//        adapter.addHeaderView(bannerViewHeader);
        //LinearLayoutManager manager = new La
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //listView.setLayoutManager(manager);
        listView.setAdapter(adaptert);            */

        loading_layout.setVisibility(View.GONE);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                listView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        loading_layout.setVisibility(View.GONE);
//                        mRefreshLayout.finishRefresh();
//                        mRefreshLayout.finishLoadMore();
//                    }
//                }, 1000);
            }

            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
//                listView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        loading_layout.setVisibility(View.GONE);
//                        mRefreshLayout.finishRefresh();
//                        mRefreshLayout.finishLoadMore();
//                    }
//                }, 1000);
                requestData();
            }
        });

        mRefreshLayout.setEnableLoadMore(false);   //

        mRefreshLayout.autoRefresh();
//        requestData();
      //  postData();

    }


    private void setClickListener(){
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter a, View view, int position) {
                RcmdMultiItem item = adapter.getItem(position);
                if (item.itemType == RcmdMultiItem.Bangdang) {
                    if (view.getId() == R.id.bangdan) {
                        //Intent intent = new Intent(getActivity(), MoreCompanyActivity.class);
                        //startActivity(intent);
                        Toast.makeText(getContext(), "点击榜單", Toast.LENGTH_SHORT).show();
                        BangdanFragment fragment = BangdanFragment.newInstance();
                        MainFragment mainFragment = (MainFragment)getParentFragment().getParentFragment().getParentFragment();
                        mainFragment.startBrotherFragment(fragment);
//                        start(fragment);

                    } else if (view.getId() == R.id.tejia) {
                        Toast.makeText(getContext(), "点击特价",Toast.LENGTH_SHORT).show();
                        TejiaFragment fragment = TejiaFragment.newInstance();
                        MainFragment mainFragment = (MainFragment)getParentFragment().getParentFragment().getParentFragment();
                        mainFragment.startBrotherFragment(fragment);

                    } else if (view.getId() == R.id.shoushu) {
                        Toast.makeText(getContext(), "点击极简说书",Toast.LENGTH_SHORT).show();
                    } else if (view.getId() == R.id.shudan) {
                        Toast.makeText(getContext(), "点击书单",Toast.LENGTH_SHORT).show();
                        ShudanPageFragment fragment = ShudanPageFragment.newInstance();
                        MainFragment mainFragment = (MainFragment)getParentFragment().getParentFragment().getParentFragment();
                        mainFragment.startBrotherFragment(fragment);
                    }

                }else if(item.getItemType() == RcmdMultiItem.jianshu){
                    if(view.getId() == R.id.layout_root){
                        //Toast.makeText(getContext(), "item点击",Toast.LENGTH_SHORT).show();
                        CustomizePageFragment fragment = CustomizePageFragment.newInstance();
                        MainFragment mainFragment = (MainFragment)getParentFragment().getParentFragment().getParentFragment();
                        mainFragment.startBrotherFragment(fragment);
                    }else if(view.getId() == R.id.book_card_one){
                        //Toast.makeText(getContext(), "card1点击",Toast.LENGTH_SHORT).show();
                        BookDetailsPageFragment bookDetailsPageFragment = BookDetailsPageFragment.newInstance();
                        MainFragment mainFragment = (MainFragment)getParentFragment().getParentFragment().getParentFragment();
                        mainFragment.startBrotherFragment(bookDetailsPageFragment);
                    }else if(view.getId() == R.id.book_card_two){
                        //Toast.makeText(getContext(), "card2点击",Toast.LENGTH_SHORT).show();
                        BookDetailsPageFragment bookDetailsPageFragment = BookDetailsPageFragment.newInstance();
                        MainFragment mainFragment = (MainFragment)getParentFragment().getParentFragment().getParentFragment();
                        mainFragment.startBrotherFragment(bookDetailsPageFragment);
                    }else if(view.getId() == R.id.book_card_three){
                        //Toast.makeText(getContext(), "card3点击",Toast.LENGTH_SHORT).show();
                        BookDetailsPageFragment bookDetailsPageFragment = BookDetailsPageFragment.newInstance();
                        MainFragment mainFragment = (MainFragment)getParentFragment().getParentFragment().getParentFragment();
                        mainFragment.startBrotherFragment(bookDetailsPageFragment);
                    }
                }else if(item.itemType==RcmdMultiItem.changxiao){
                    CustomizePageFragment fragment = CustomizePageFragment.newInstance();
                    MainFragment mainFragment = (MainFragment)getParentFragment().getParentFragment().getParentFragment();
                    mainFragment.startBrotherFragment(fragment);
                }
            }
        });
        /*adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter a, View view, int position) {
//                Toast.makeText(getContext(), "item点击",Toast.LENGTH_SHORT).show();
                RcmdMultiItem item = adapter.getItem(position);
                if(item.itemType==RcmdMultiItem.jianshu ) {

                    CustomizePageFragment fragment = CustomizePageFragment.newInstance();
                    MainFragment mainFragment = (MainFragment)getParentFragment().getParentFragment().getParentFragment();
                    mainFragment.startBrotherFragment(fragment);
                    Toast.makeText(getContext(), "item点击",Toast.LENGTH_SHORT).show();
                }else if(item.itemType==RcmdMultiItem.changxiao){

                }

            }
        });*/

    }

    private void requestData()
    {

        requestFlag = 1;
        //OkGo.<String>get("http://yapi.esctrl.com/mock/9/api/v1/category/index").tag(this)
        OkGo.<String>get("http://yapi.esctrl.com/mock/9/api/v1/category/custom/index").tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(final Response<String> response) {
                        requestFlag += 2;
                        String body = response.body();
                        LogUtil.d("tag",body);

                        final ServerRsp serverRsp = JSON.parseObject(body,ServerRsp.class);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(serverRsp!=null)
                                {
                                    if(requestFlag == 7)
                                    {
                                        mRefreshLayout.finishLoadMore();
                                        mRefreshLayout.finishRefresh();
                                    }
                                    List<Data> dataList = JSON.parseArray(serverRsp.data, Data.class);
                                    for(Data data : dataList){
                                        RcmdMultiItem rcmdMultiItem = new RcmdMultiItem();
                                        rcmdMultiItem.itemType = 3;
                                        rcmdMultiItem.data = data;
                                        list.add(rcmdMultiItem);
                                        Log.d("tag", "msgmsg "+rcmdMultiItem.data.getName());
                                    }
                                    //Log.d("tag", "msgmsg"+dataList.get(0).getName());
                                    //Log.d("tag", "msgmsg"+dataList.get(0).getEbooks().get(0).getName());
                                    adapter.setNewData(list);


                                }

                                else{
                                    //提示错误
                                    Log.d("tag", "msgmsg error 错误");
                                }
                            }
                        });


                    }

                    @Override
                    public void onError(Response<String> response) {
                        requestFlag += 2;
                        if(requestFlag == 7)
                        {
                            mRefreshLayout.finishLoadMore();
                            mRefreshLayout.finishRefresh();
                        }
                    }
                });



       OkGo.<String>get("http://yapi.esctrl.com/mock/9/api/v1/banner/index").tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(final Response<String> response) {
                        requestFlag+=4;
                        String body = response.body();
                        LogUtil.d("tag",body);
                        if(requestFlag == 7)
                        {
                            mRefreshLayout.finishLoadMore();
                            mRefreshLayout.finishRefresh();
                        }
                        final ServerRsp serverRsp = JSON.parseObject(body,ServerRsp.class);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(serverRsp!=null)
                                {
//

                                    List<Banners> bannersList = JSON.parseArray(serverRsp.data, Banners.class);
                                    for(Banners banner : bannersList){
                                        imgs.add(banner.getImg_url());


//                                        RcmdMultiItem rcmdMultiItem = new RcmdMultiItem();
//                                        rcmdMultiItem.itemType = 1;
//                                        rcmdMultiItem.banners = banner;
//                                        list.add(rcmdMultiItem);
                                       // Log.d("tag", "msgmsg "+rcmdMultiItem.data.getName());
                                    }
                                    if(imgs!=null&&imgs.size()>0)
                                    {
//                                        banner.setImages(imgs);
                                        banner.update(imgs);
                                        bannerView.setVisibility(View.VISIBLE);
                                    }
                                   else {
//                                        banner.setImages(new ArrayList<Object>());
                                        bannerView.setVisibility(View.GONE);
                                    }

                                  //  adapter.setNewData(list);

                                }

                                else{
                                    //提示错误
                                }
                            }
                        });


                    }

                    @Override
                    public void onError(Response<String> response) {
                        requestFlag+=4;
                        if(requestFlag == 7)
                        {
                            mRefreshLayout.finishLoadMore();
                            mRefreshLayout.finishRefresh();
                        }
                    }
                });
       // banner.setImages(imgs);

    }

    private void postData()
    {
        flag = 0;


        OkGo.<String>post("http://yapi.esctrl.com/mock/9/api/v1/category/index").tag(this)
                .params("","")
                .params("",1)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //请求成功
                        String body = response.body();
                        LogUtil.d("tag",body);

                        final ServerRsp serverRsp = JSON.parseObject(body,ServerRsp.class);
                        flag+=2;

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                List data1 = new ArrayList();
                                if(serverRsp!=null)
                                {
//                                   serverRsp.data
                                    LogUtil.d("tag",serverRsp.data);
                                  //  Log.d("tag", "msgmsg kk "+serverRsp.data.toString());
                                    Log.d("tag", "msgmsg  aa"+serverRsp.data);
                                    Log.d("tag", "msgmsg "+serverRsp.code);
                                    Log.d("tag", "msgmsg "+serverRsp.msg);
                                 //   Log.d("tag", "msgmsg "+serverRsp.data.toString());
                                    JSONArray jsonArray;
                                    data1List = data1;
                                    //JSONArray jsonArray = new JSONArray(serverRsp.data);
                                    try{
                                        jsonArray = new JSONArray(serverRsp.data);
                                        if(jsonArray == null || jsonArray.length() <= 0){
                                         //   Log.d("tag", "msgmsg error"+serverRsp.data.toString());
                                        }else {
                                            Log.d("tag", "msgmsg arraysize "+jsonArray.length());
                                        }
                                        for (int i=0;i<jsonArray.length();i++){
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            //UserDetail u = JSON.parseObject(userinfoModel.toJSONString(), UserDetail.class);
                                            Data data = JSON.parseObject(jsonObject.toString(), Data.class);
                                            Log.d("tag", "msgmsg data "+data.getName());
                                            // JSONArray jsonArray1 = new JSONArray(data.getEbooks());
                                           /* List<Ebooks> ebooksList = new ArrayList<>();
                                            for(int j=0;j<jsonArray1.length();j++){
                                                JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                                                Ebooks ebooks = JSON.parseObject(jsonObject1.toString(), Ebooks.class);
                                                ebooksList.add(ebooks);
                                                data.setEbooks(ebooksList);
                                            }
                                            RcmdMultiItem rcmdMultiItem = new RcmdMultiItem();
                                            rcmdMultiItem.itemType = 3;
                                            rcmdMultiItem.data = data;
                                            list.add(rcmdMultiItem);*/
                                        }
                                    }catch (Exception e){
                                        Log.d("tag", "msgmsg error"+e.getMessage());
                                    }

                                   /* JSONObject jsonObject = new JSONObject("jdkflkslkjdf");
                                    JSONObject myJsonObject = new JSONObject(serverRsp.data);
                                    JSONArray jsonArray;
                                    try {
                                        jsonArray = new JSONArray(serverRsp.data);
                                    }*/

                                    //adapter.setNewData(list);


                                }

                                else{
                                    //提示错误
                                    Log.d("tag", "msgmsg error 错误");
                                }

                                if(flag==6)
                                {
                                    mRefreshLayout.finishRefresh();
                                    mRefreshLayout.finishLoadMore();
                                    list.add(new RcmdMultiItem());
                                    for(int i=0;i<data1List.size();i++)
                                    {
                                        list.add(new RcmdMultiItem());
                                    }
                                    list.add(new RcmdMultiItem());
                                    for(int i=0;i<data2List.size();i++)
                                    {
                                        list.add(new RcmdMultiItem());
                                    }
                                    adapter.setNewData(list);
                                    adapter.notifyDataSetChanged();

                                }
                            }
                        });


                    }

                    @Override
                    public void onError(Response<String> response) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                flag+=2;
                                if(flag==6)
                                {
                                    mRefreshLayout.finishRefresh();
                                    mRefreshLayout.finishLoadMore();
//                                    Toast.makeText()
                                }
                            }
                        });
                    }
                });
        OkGo.<String>post("http://yapi.esctrl.com/mock/9/api/v1/category/index").tag(this)
                .params("","")
                .params("",1)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //请求成功
                        String body = response.body();
                        LogUtil.d("tag",body);

                        final ServerRsp serverRsp = JSON.parseObject(body,ServerRsp.class);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                flag+=4;
                                if(flag==6)
                                {
                                    mRefreshLayout.finishRefresh();
                                    mRefreshLayout.finishLoadMore();
                                }
                                if(serverRsp!=null&&serverRsp.code==0)
                                {
//                                   serverRsp.data
                                    LogUtil.d("tag",serverRsp.data);
                                    //  Log.d("tag", "msgmsg kk "+serverRsp.data.toString());
                                    Log.d("tag", "msgmsg  aa"+serverRsp.data);
                                    Log.d("tag", "msgmsg "+serverRsp.code);
                                    Log.d("tag", "msgmsg "+serverRsp.msg);
                                    //   Log.d("tag", "msgmsg "+serverRsp.data.toString());
                                    JSONArray jsonArray;
                                    //JSONArray jsonArray = new JSONArray(serverRsp.data);
                                    try{
                                        jsonArray = new JSONArray(serverRsp.data);
                                        if(jsonArray == null || jsonArray.length() <= 0){
                                            //   Log.d("tag", "msgmsg error"+serverRsp.data.toString());
                                        }else {
                                            Log.d("tag", "msgmsg arraysize "+jsonArray.length());
                                        }
                                        for (int i=0;i<jsonArray.length();i++){
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            //UserDetail u = JSON.parseObject(userinfoModel.toJSONString(), UserDetail.class);
                                            Data data = JSON.parseObject(jsonObject.toString(), Data.class);
                                            Log.d("tag", "msgmsg data "+data.getName());
                                            // JSONArray jsonArray1 = new JSONArray(data.getEbooks());
                                           /* List<Ebooks> ebooksList = new ArrayList<>();
                                            for(int j=0;j<jsonArray1.length();j++){
                                                JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                                                Ebooks ebooks = JSON.parseObject(jsonObject1.toString(), Ebooks.class);
                                                ebooksList.add(ebooks);
                                                data.setEbooks(ebooksList);
                                            }
                                            RcmdMultiItem rcmdMultiItem = new RcmdMultiItem();
                                            rcmdMultiItem.itemType = 3;
                                            rcmdMultiItem.data = data;
                                            list.add(rcmdMultiItem);*/



                                        }





                                    }catch (Exception e){
                                        Log.d("tag", "msgmsg error"+e.getMessage());
                                    }

                                   /* JSONObject jsonObject = new JSONObject("jdkflkslkjdf");
                                    JSONObject myJsonObject = new JSONObject(serverRsp.data);
                                    JSONArray jsonArray;
                                    try {
                                        jsonArray = new JSONArray(serverRsp.data);
                                    }*/

                                    //adapter.setNewData(list);


                                }

                                else{
                                    //提示错误
                                    Log.d("tag", "msgmsg error 错误");
                                }

                                if(flag==6)
                                {
                                    mRefreshLayout.finishRefresh();
                                    mRefreshLayout.finishLoadMore();
                                    list.add(new RcmdMultiItem());
                                    for(int i=0;i<data1List.size();i++)
                                    {
                                        list.add(new RcmdMultiItem());
                                    }
                                    list.add(new RcmdMultiItem());
                                    for(int i=0;i<data2List.size();i++)
                                    {
                                        list.add(new RcmdMultiItem());
                                    }
                                    adapter.setNewData(list);
                                    adapter.notifyDataSetChanged();

                                }

                            }
                        });


                    }

                    @Override
                    public void onError(Response<String> response) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                flag+=4;
                                if(flag==6)
                                {
                                    mRefreshLayout.finishRefresh();
                                    mRefreshLayout.finishLoadMore();
//                                    Toast.makeText()
                                }
                            }
                        });

                    }
                });


    }


    private void getBanner()
    {
        Map<String,String> params = new HashMap<>();

        JSONObject jsonObject = new JSONObject(params);

        OkGo.<String>post(ApiConstant.serverUrl+ApiConstant.getBannerUri).tag(this)

                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        //请求成功
                        String body = response.body();
                        LogUtil.d("tag",body);

                        final ServerRsp serverRsp = JSON.parseObject(body,ServerRsp.class);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(serverRsp!=null)
                                {
//
                                    //   Log.d("tag", "msgmsg "+serverRsp.data.toString());
                                    JSONArray jsonArray;
                                    //JSONArray jsonArray = new JSONArray(serverRsp.data);
                                    try{
                                        List<MyBanner> banners = JSON.parseArray(serverRsp.data,MyBanner.class);
                                        MySubjectCategory myData = JSON.parseObject(serverRsp.data,MySubjectCategory.class);


                                        jsonArray = new JSONArray(serverRsp.data);
                                        if(jsonArray == null || jsonArray.length() <= 0){
                                            //   Log.d("tag", "msgmsg error"+serverRsp.data.toString());



                                        }else {
                                            Log.d("tag", "msgmsg arraysize "+jsonArray.length());
                                        }
                                        for (int i=0;i<jsonArray.length();i++){
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            //UserDetail u = JSON.parseObject(userinfoModel.toJSONString(), UserDetail.class);
                                            Data data = JSON.parseObject(jsonObject.toString(), Data.class);
                                            Log.d("tag", "msgmsg data "+data.getName());
                                            // JSONArray jsonArray1 = new JSONArray(data.getEbooks());
                                           /* List<Ebooks> ebooksList = new ArrayList<>();
                                            for(int j=0;j<jsonArray1.length();j++){
                                                JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                                                Ebooks ebooks = JSON.parseObject(jsonObject1.toString(), Ebooks.class);
                                                ebooksList.add(ebooks);
                                                data.setEbooks(ebooksList);
                                            }
                                            RcmdMultiItem rcmdMultiItem = new RcmdMultiItem();
                                            rcmdMultiItem.itemType = 3;
                                            rcmdMultiItem.data = data;
                                            list.add(rcmdMultiItem);*/
                                        }
                                    }catch (Exception e){
                                        Log.d("tag", "msgmsg error"+e.getMessage());
                                    }

                                   /* JSONObject jsonObject = new JSONObject("jdkflkslkjdf");
                                    JSONObject myJsonObject = new JSONObject(serverRsp.data);
                                    JSONArray jsonArray;
                                    try {
                                        jsonArray = new JSONArray(serverRsp.data);
                                    }*/

                                    //adapter.setNewData(list);


                                }

                                else{
                                    //提示错误
                                    Log.d("tag", "msgmsg error 错误");
                                }
                            }
                        });


                    }

                    @Override
                    public void onError(Response<String> response) {

                    }
                });
    }

}