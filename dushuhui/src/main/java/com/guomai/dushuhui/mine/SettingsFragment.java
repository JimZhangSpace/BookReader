package com.guomai.dushuhui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseBackFragment;
import com.guomai.dushuhui.main.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/15.
 */

public class SettingsFragment extends BaseBackFragment {
    public static SettingsFragment newInstance() {

        Bundle args = new Bundle();

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "SettingsFragment";

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.clean_cache)
    LinearLayout clean_cache;
    /*@BindView(R.id.version_now)
    LinearLayout version_now;*/
    @BindView(R.id.feedback)
    LinearLayout feedback;
    @BindView(R.id.quit)
    ImageView quit;

    private View mRoot;

    QuitDialog quitDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*mRoot = inflater.inflate(R.layout.frag_common_recycle,
                container, false);*/
        mRoot = inflater.inflate(R.layout.frag_settings_page,
                container, false);
//        mainActivity = (MyMainActivity) getActivity();
//        mainActivity.initHeadPanel(Constant.FRAGMENT_FLAG_COMMING_SOON);
        ButterKnife.bind(this,mRoot);

        initView();
        return mRoot;
    }

    @OnClick({R.id.back, R.id.clean_cache, R.id.feedback, R.id.quit})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back:           //返回上一个页面
                pop();
                break;
            case R.id.clean_cache:

                break;
            case R.id.feedback:
                /*FeedbackPageFragment feedbackPageFragment = FeedbackPageFragment.newInstance();
                MainFragment mainFragment = (MainFragment)getParentFragment().getParentFragment();
                mainFragment.startBrotherFragment(feedbackPageFragment);
*/
                FeedbackPageFragment feedbackPageFragment = FeedbackPageFragment.newInstance();
                start(feedbackPageFragment);
                //MainFragment mainFragment3 = (MainFragment)getParentFragment().getParentFragment();
                /*if(feedbackPageFragment != null){
                    Toast.makeText(getContext(), "feedbackfragment不为空", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "feedbackfragment空", Toast.LENGTH_SHORT).show();
                }*/
                /*if(mainFragment3 != null){
                    Toast.makeText(getContext(), "mainfragment不为空", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "mainfragment空", Toast.LENGTH_SHORT).show();
                }*/
                //mainFragment3.startBrotherFragment(feedbackPageFragment);
                break;
            case R.id.quit:
                    quitDialog.show();
                break;
        }
    }


    private void initView() {
        quitDialog = new QuitDialog(getActivity());
        quitDialog.setQuitCancelOnclickListener(new QuitDialog.quitCancelOnclickListener() {
            @Override
            public void quitCancelClick() {       //取消
                quitDialog.dismiss();
            }
        });
        quitDialog.setQuitConfirmOnclickListener(new QuitDialog.quitConfirmOnclickListener() {
            @Override
            public void quitConfirmClick() {        //确定退出  返回登录页面
                quitDialog.dismiss();

            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }

}

