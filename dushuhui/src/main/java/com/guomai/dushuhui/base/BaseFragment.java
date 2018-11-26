package com.guomai.dushuhui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;

import me.yokeyword.fragmentation.SupportFragment;

public class BaseFragment extends SupportFragment {
    protected MaterialDialog progressDialog;

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        progressDialog = new MaterialDialog.Builder(getActivity())
                .title("玩命加载中")
                .content("请稍后")
                .progress(true, 0)
                .build();
    }
}
