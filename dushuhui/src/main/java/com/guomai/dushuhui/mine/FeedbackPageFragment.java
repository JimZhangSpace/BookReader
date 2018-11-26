package com.guomai.dushuhui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseBackFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/15.
 */

public class FeedbackPageFragment extends BaseBackFragment {
    public static FeedbackPageFragment newInstance() {

        Bundle args = new Bundle();

        FeedbackPageFragment fragment = new FeedbackPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "FeedbackPageFragment";

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.choice)
    ImageView choice;
    /*@BindView(R.id.version_now)
    LinearLayout version_now;*/
    @BindView(R.id.edit_field)
    EditText edit_field;
    @BindView(R.id.submit)
    ImageView submit;

    private View mRoot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*mRoot = inflater.inflate(R.layout.frag_common_recycle,
                container, false);*/
        mRoot = inflater.inflate(R.layout.frag_feedbak_page,
                container, false);
//        mainActivity = (MyMainActivity) getActivity();
//        mainActivity.initHeadPanel(Constant.FRAGMENT_FLAG_COMMING_SOON);
        ButterKnife.bind(this,mRoot);

        initView();
        return mRoot;
    }

    @OnClick({R.id.back, R.id.choice, R.id.edit_field, R.id.submit})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back:           //返回上一个页面
                pop();
                break;
            case R.id.choice:

                break;
            case R.id.edit_field:

                break;
            case R.id.submit:

                break;
            default:
                break;
        }
    }


    private void initView() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }

}
