package com.guomai.dushuhui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.guomai.dushuhui.MyApplication;
import com.guomai.dushuhui.R;
import com.noober.background.BackgroundLibrary;


import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by Administrator on 2018/2/11 0011.
 */

public abstract class BaseToolBarActivity extends SupportActivity {

    protected MaterialDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInst().addActvity(this);
        progressDialog = new MaterialDialog.Builder(this)
                .title("玩命加载中")
                .content("请稍后")
                .progress(true, 0)
                .build();

        setContentView(getLayout());
        initView();
        processLogic();

    }

      protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInst().removeActvity(this);
    }

    /**
     * 加载view
     */
    protected abstract int getLayout();
    protected abstract void initView();
    /**
     * 执行业务方法
     */
    protected abstract void processLogic();

    @Override
    public void onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }


}
