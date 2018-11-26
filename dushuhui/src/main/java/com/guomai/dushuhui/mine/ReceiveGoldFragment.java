package com.guomai.dushuhui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseBackFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/14.
 */

public class ReceiveGoldFragment extends BaseBackFragment {
    public static ReceiveGoldFragment newInstance() {

        Bundle args = new Bundle();

        ReceiveGoldFragment fragment = new ReceiveGoldFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "EditInfoFragment";

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.shared_everyday)
    ImageView shared_everyday;
    @BindView(R.id.read_everyday)
    ImageView read_everyday;
    @BindView(R.id.note_everyday)
    ImageView note_everyday;
    @BindView(R.id.comment_everyday)
    ImageView comment_everyday;
    @BindView(R.id.leadread_everyday)
    ImageView leadread_everyday;
    @BindView(R.id.gold_num)
    TextView gold_num;

    private View mRoot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*mRoot = inflater.inflate(R.layout.frag_common_recycle,
                container, false);*/
        mRoot = inflater.inflate(R.layout.frag_receive_page,
                container, false);
//        mainActivity = (MyMainActivity) getActivity();
//        mainActivity.initHeadPanel(Constant.FRAGMENT_FLAG_COMMING_SOON);
        ButterKnife.bind(this,mRoot);

        initView();
        return mRoot;
    }

    @OnClick({R.id.back,R.id.shared_everyday, R.id.read_everyday, R.id.note_everyday,
            R.id.comment_everyday, R.id.leadread_everyday, R.id.gold_num})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back:

                break;
            case R.id.shared_everyday:

                break;
            case R.id.read_everyday:

                break;
            case R.id.note_everyday:

                break;
            case R.id.comment_everyday:

                break;
            case R.id.leadread_everyday:

                break;
            case R.id.gold_num:

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
