package com.guomai.dushuhui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseBackFragment;
import com.guomai.dushuhui.bookshop.model.TestData;
import com.guomai.dushuhui.mine.adapter.MyGoldPageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/15.
 */

public class MyGoldPageFragment extends BaseBackFragment {
    public static MyGoldPageFragment newInstance() {

        Bundle args = new Bundle();

        MyGoldPageFragment fragment = new MyGoldPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "MyGoldPageFragment";

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btn_submit)
    Button btn_submit;
    @BindView(R.id.gold_num)
    TextView gold_num;
    @BindView(R.id.btn_gold_details)
    TextView btn_gold_details;

    private View mRoot;

    private MyGoldPageAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*mRoot = inflater.inflate(R.layout.frag_common_recycle,
                container, false);*/
        mRoot = inflater.inflate(R.layout.frag_accountbalance_page,
                container, false);
//        mainActivity = (MyMainActivity) getActivity();
//        mainActivity.initHeadPanel(Constant.FRAGMENT_FLAG_COMMING_SOON);
        ButterKnife.bind(this,mRoot);

        initView();
        return mRoot;
    }

    @OnClick({R.id.back, R.id.btn_submit, R.id.gold_num, R.id.btn_gold_details})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back:
                pop();
                break;
            case R.id.btn_submit:

                break;
            case R.id.gold_num:

                break;
            case R.id.btn_gold_details:
                Intent intent = new Intent(getContext(), GoldDetailsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    private void initView() {
        adapter = new MyGoldPageAdapter(R.layout.chongzhi_list_item);
//        RecyclerView.LayoutManager.setAutoMeasureEnabled(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        List<TestData> list = new ArrayList<>();
        list.add(new TestData());
        list.add(new TestData());
        adapter.setNewData(list);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }

}
