package com.guomai.dushuhui.bookshop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseBackFragment;
import com.guomai.dushuhui.leadread.LeadReadPageFragment;
import com.guomai.dushuhui.leadread.LeadReadViewPagerFragment;
import com.guomai.dushuhui.leadread.adapter.PageAdapter;
import com.guomai.dushuhui.main.MainFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by Administrator on 2018/9/11.
 */

public class BangdanViewPagerFragment extends BaseBackFragment {
    private  final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mTitleDataList = new ArrayList<>();
    //    @BindView(R.id.scrollableLayout)
//    ScrollableLayout mScrollLayout;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tab_layout;
//    @BindView(R.id.magic_indicator)
//    MagicIndicator magic_indicator;


    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;


    public static BangdanViewPagerFragment newInstance() {

        Bundle args = new Bundle();

        BangdanViewPagerFragment fragment = new BangdanViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "BangdanViewPagerFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_bangdan_pager, container, false);
        ButterKnife.bind(this,view);

        initView(view);
        return view;
    }

    private void initView(View view) {
        tv_title.setText("榜单");
        mTitleDataList.add("人气榜");
        mTitleDataList.add("新书榜");

        mFragments.add(RankingPageFragment.newInstance());     ////
        for(int i=0;i<mTitleDataList.size()-1;i++)
        {
            LeadReadPageFragment fragment = LeadReadPageFragment.newInstance();
            fragment.index = i;

//           TestFragment fragment = new TestFragment();
            mFragments.add(fragment);
        }

//        initToolbarNav(toolbar);
//        toolbar.setTitle("榜单");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                pop();
               //MainFragment fragment = (MainFragment) getParentFragment().getParentFragment();
//                fragment.pop();
                pop();
            }
        });

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

        FragmentManager manager = getChildFragmentManager();
        PageAdapter pageAdapter = new PageAdapter(manager,mFragments,mTitleDataList);
        mViewPager.setAdapter(pageAdapter);



        tab_layout.setViewPager(mViewPager);
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


    }


}

