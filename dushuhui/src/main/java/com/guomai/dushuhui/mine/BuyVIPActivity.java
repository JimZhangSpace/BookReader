package com.guomai.dushuhui.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.bookshop.model.TestData;
import com.guomai.dushuhui.mine.adapter.BuyVIPAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/20.
 */

public class BuyVIPActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btn_submit)
    Button btn_submit;

    private BuyVIPAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyvip);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        tv_title.setText("开通VIP会员");
        tv_title.setTextColor(Color.parseColor("#333333"));
        tv_title.setTextSize(18);
        //mToolbar.setTitle("开通VIP会员");
        //mToolbar.setDisplsyShowTitileEnable(false);
        //mToolbar.set
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            mToolbar.setNavigationIcon(R.drawable.nav_back);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new BuyVIPAdapter(R.layout.chongzhi_list_item);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        List<TestData> list = new ArrayList<>();
        list.add(new TestData());
        list.add(new TestData());
        adapter.setNewData(list);
    }
}
