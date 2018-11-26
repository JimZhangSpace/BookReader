package com.guomai.dushuhui.bookshop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseMainFragment;

/**
 * Created by Administrator on 2018/9/11.
 */

public class BangdanFragment extends BaseMainFragment {
    public static BangdanFragment newInstance() {
        Bundle args = new Bundle();

        BangdanFragment fragment = new BangdanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "BangdanFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_page_container, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//    if (findChildFragment(ViewPagerFragment.class) == null) {
//        loadRootFragment(R.id.fl_second_container, ViewPagerFragment.newInstance());
//    }

        if (findChildFragment(BangdanViewPagerFragment.class) == null) {
            loadRootFragment(R.id.fl_second_container, BangdanViewPagerFragment.newInstance());
        }
        /*if (findChildFragment(TejiaViewpagerFragment.class) == null) {
            loadRootFragment(R.id.fl_second_container, TejiaViewpagerFragment.newInstance());
        }*/

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        // 这里可以不用懒加载,因为Adapter的场景下,Adapter内的子Fragment只有在父Fragment是show状态时,才会被Attach,Create
    }

}