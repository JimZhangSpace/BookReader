package com.guomai.dushuhui.bookshop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseMainFragment;
import com.guomai.dushuhui.leadread.LeadReadFragment;
import com.guomai.dushuhui.leadread.LeadReadViewPagerFragment;

import me.yokeyword.fragmentation.SupportFragment;
/**书城*/
public class BookShopFragment extends BaseMainFragment {
    public static BookShopFragment newInstance() {
        Bundle args = new Bundle();

        BookShopFragment fragment = new BookShopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "BookShopFragment";

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

        if (findChildFragment(BookShopViewPagerFragment.class) == null) {
            loadRootFragment(R.id.fl_second_container, BookShopViewPagerFragment.newInstance());
        }
        /*if (findChildFragment(LeadReadViewPagerFragment.class) == null) {
            loadRootFragment(R.id.fl_second_container, LeadReadViewPagerFragment.newInstance());
        }*/

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        // 这里可以不用懒加载,因为Adapter的场景下,Adapter内的子Fragment只有在父Fragment是show状态时,才会被Attach,Create
    }

}
