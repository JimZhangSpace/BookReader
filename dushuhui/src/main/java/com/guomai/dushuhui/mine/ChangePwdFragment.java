package com.guomai.dushuhui.mine;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseBackFragment;
import com.guomai.dushuhui.main.MainFragment;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/14.
 */

public class ChangePwdFragment extends BaseBackFragment {
    public static ChangePwdFragment newInstance() {

        Bundle args = new Bundle();

        ChangePwdFragment fragment = new ChangePwdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "ChangePwdFragment";

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.old_pwd)
    EditText old_pwd;
    @BindView(R.id.new_pwd)
    EditText new_pwd;
    @BindView(R.id.confirm_pwd)
    EditText confirm_pwd;
    @BindView(R.id.btn_save)
    ImageView btn_save;


    private View mRoot;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*mRoot = inflater.inflate(R.layout.frag_common_recycle,
                container, false);*/
        mRoot = inflater.inflate(R.layout.frag_change_pwd_page,
                container, false);
//        mainActivity = (MyMainActivity) getActivity();
//        mainActivity.initHeadPanel(Constant.FRAGMENT_FLAG_COMMING_SOON);
        ButterKnife.bind(this,mRoot);

        initView();
        return mRoot;
    }

    @OnClick({R.id.back, R.id.old_pwd, R.id.new_pwd, R.id.confirm_pwd, R.id.btn_save})
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.back:
//                MainFragment mainFragment = (MainFragment)getParentFragment().getParentFragment();
                pop();
                break;
            case R.id.old_pwd:

                break;
            case R.id.new_pwd:

                break;
            case R.id.confirm_pwd:

                break;
            case R.id.btn_save:
               /* final QMUITipDialog tipDialog = new QMUITipDialog.Builder(this)
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                        .setTipWord("登录成功")
                        .create();
                tipDialog.show();
                btn_save.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tipDialog.dismiss();
                    }
                },1500);*/
//            Toast.makeText(getApplication(), "Login", Toast.LENGTH_SHORT).show();
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
