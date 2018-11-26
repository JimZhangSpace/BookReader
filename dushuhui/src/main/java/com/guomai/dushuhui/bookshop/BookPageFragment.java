package com.guomai.dushuhui.bookshop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseFragment;

import butterknife.ButterKnife;

public class BookPageFragment extends BaseFragment {

    int currentPage = 1;
    int pageCount = 20;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_common_recycleview, container, false);
        ButterKnife.bind(this,view);
//        initView(view);

        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);



    }


    private void onRefresh()
    {
        currentPage = 1;

    }

    private void onLoadMore()
    {
        currentPage++;

//        data.size()<pageCount;
//        msmartView.setLoad
    }



}
