package com.guomai.dushuhui.bookshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.flyco.tablayout.SlidingTabLayout;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.bookshop.adapter.PageAdapter;
import com.guomai.dushuhui.model.Data;
import com.guomai.dushuhui.model.ServerRsp;
import com.guomai.dushuhui.search.SearchActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.litepal.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2018/9/11.
 */

public class BookShopViewPagerFragment extends SupportFragment {
    private  final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mTitleDataList = new ArrayList<>();

    @BindView(R.id.slide_title)
    SlidingTabLayout slide_title;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.search)
    LinearLayout search;

    PageAdapter pageAdapter;
    FragmentManager manager;

    public static BookShopViewPagerFragment newInstance() {

        Bundle args = new Bundle();

        BookShopViewPagerFragment fragment = new BookShopViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R.id.search)
    public void onClick(View view){
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_bookshop, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        requestData();
        return view;
    }

    private void requestData()
    {
        Log.d("tag", "msgmsg begin");
        OkGo.<String>get("http://yapi.esctrl.com/mock/9/api/v1/category/index").tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(final Response<String> response) {

                        String body = response.body();
                        LogUtil.d("tag",body);

                        final ServerRsp serverRsp = JSON.parseObject(body,ServerRsp.class);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(serverRsp!=null)
                                {

//                                    Data data = new Data();



//                                   serverRsp.data
//                                    LogUtil.d("tag",serverRsp.data);
//                                    Log.d("tag", "msgmsg kk "+serverRsp.data.toString());
//                                    Log.d("tag", "msgmsg  aa"+serverRsp.data);
//                                    Log.d("tag", "msgmsg "+serverRsp.code);
//                                    Log.d("tag", "msgmsg "+serverRsp.msg);
//                                    Log.d("tag", "msgmsg "+serverRsp.data.toString());
//
                                    List<Data> dataList = JSON.parseArray(serverRsp.data, Data.class);
                                   // Log.d("tag", "msgmsg "+dataList.get(0).getName());
                                    for(Data data : dataList){

                                        Log.d("tag", "msgmsg kkk"+data.getName());

                                        BookShopPageFragment fragment = BookShopPageFragment.newInstance();
                                        fragment.index = data.getSequence();
                                        mFragments.add(fragment);
                                        mTitleDataList.add(data.getName());
                                        //slide_title.addNewTab(data.getName());
                                    }

                                    pageAdapter = new PageAdapter(manager,mFragments,mTitleDataList);
                                    mViewPager.setAdapter(pageAdapter);
                                    pageAdapter.setData(mFragments, mTitleDataList);
                                    slide_title.setViewPager(mViewPager);

                                  // pageAdapter.setData(mFragments, mTitleDataList);

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
                       // Log.d("tag", "msgmsg 错误");
                    }
                });
    }


    private void initView(View view){
        mTitleDataList.add("精选");
      /*  mTitleDataList.add("历史");
        mTitleDataList.add("历史");
        mTitleDataList.add("社科");*/
        mFragments.add(RcmdBookFragment.newInstance());
        /*for(int i=0; i<mTitleDataList.size()-1; i++){
            BookShopPageFragment fragment = BookShopPageFragment.newInstance();
            fragment.index = i;
            mFragments.add(fragment);
        }*/

        //        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
//        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
//
//            @Override
//            public int getCount() {
//                return mTitleDataList == null ? 0 : mTitleDataList.size();
//            }
//
//            @Override
//            public IPagerTitleView getTitleView(Context context, final int index) {
//                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
//                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
//                colorTransitionPagerTitleView.setSelectedColor(Color.BLACK);
//                colorTransitionPagerTitleView.setText(mTitleDataList.get(index));
//                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        mViewPager.setCurrentItem(index);
//                    }
//                });
//                return colorTransitionPagerTitleView;
//            }
//
//            @Override
//            public IPagerIndicator getIndicator(Context context) {
//                LinePagerIndicator indicator = new LinePagerIndicator(context);
//                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
//                return indicator;
//            }
//        });
//        magic_indicator.setNavigator(commonNavigator);

        manager = getChildFragmentManager();

        pageAdapter = new PageAdapter(manager,mFragments,mTitleDataList);
        mViewPager.setAdapter(pageAdapter);
        //pageAdapter.setData(mFragments, mTitleDataList);
        slide_title.setViewPager(mViewPager);

//        mScrollLayout.getHelper().setCurrentScrollableContainer((HeaderViewPagerFragment)mFragments.get(0));
//        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                Log.e(TAG,"onPageSelected");
////                mScrollLayout.getHelper().setCurrentScrollableContainer((HeaderViewPagerFragment)mFragments.get(position));
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

        requedata();
    }

    private void requedata() {

    }
}
