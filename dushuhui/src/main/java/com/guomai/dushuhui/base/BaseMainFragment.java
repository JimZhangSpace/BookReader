package com.guomai.dushuhui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.afollestad.materialdialogs.MaterialDialog;
import com.guomai.dushuhui.util.ToastUtil;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 懒加载
 * Created by YoKeyword on 16/6/5.
 */
public abstract class BaseMainFragment extends SupportFragment {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
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

    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
//            ToastUtil.showToast("再按一次退出应用");
//            MyApplication.getInst().exit();
//            Toast.makeText(_mActivity, R.string.press_again_exit, Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
