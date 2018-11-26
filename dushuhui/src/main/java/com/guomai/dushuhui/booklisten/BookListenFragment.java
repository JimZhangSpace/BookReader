package com.guomai.dushuhui.booklisten;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseBackFragment;
import com.guomai.dushuhui.base.BaseMainFragment;
import com.guomai.dushuhui.bookshop.BangdanFragment;
import com.guomai.dushuhui.bookshop.BangdanViewPagerFragment;

/**
 * Created by Administrator on 2018/9/18.
 */

public class BookListenFragment extends BaseBackFragment {       //BaseMainFragment
    public static BookListenFragment newInstance() {
        Bundle args = new Bundle();

        BookListenFragment fragment = new BookListenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "BookListenFragment";

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

        if (findChildFragment(BookListenerViewPagerFragment.class) == null) {
            loadRootFragment(R.id.fl_second_container, BookListenerViewPagerFragment.newInstance());
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