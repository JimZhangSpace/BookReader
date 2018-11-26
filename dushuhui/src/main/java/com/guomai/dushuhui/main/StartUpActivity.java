package com.guomai.dushuhui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.guomai.dushuhui.login.LoginTipActivity;
import com.guomai.dushuhui.MyApplication;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseActivity;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/3 0003.
 */

public class StartUpActivity extends BaseActivity {
    @BindView(R.id.iv_bg)
    ImageView iv_bg;




    protected int getLayout() {
        return R.layout.act_startup;
    }


    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.e("tag","启动闪屏页");
        ButterKnife.bind(this);
        processLogic();

    }

    protected void processLogic() {
        ViewAnimator.animate(iv_bg).alpha(0.3f,1f)
                .duration(1800)
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
//                        showMianPage();
                        showReadPage();
                    }
                })
                .start();

    }

    private void showMianPage() {
        // 跳到首页
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showReadPage()
    {
        Intent intent = new Intent(getApplicationContext(),LoginTipActivity.class);
        startActivity(intent);
        finish();
    }


    /**
     * 已经登陆过，自动登陆
     */
    private boolean canAutoLogin() {
        return MyApplication.getInst().user!=null;
    }
}



