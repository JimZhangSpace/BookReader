package com.guomai.dushuhui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.guomai.dushuhui.R;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by YoKeyword on 16/2/7.
 */
public class BaseBackFragment extends SwipeBackFragment {
    private static final String TAG = "Fragmentation";
    protected MaterialDialog progressDialog;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setParallaxOffset(0.5f);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        progressDialog = new MaterialDialog.Builder(getActivity())
                .title("玩命加载中")
                .content("请稍后")
                .progress(true, 0)
                .build();
    }

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }
}
