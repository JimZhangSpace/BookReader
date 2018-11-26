package com.guomai.dushuhui.mine;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.mine.adapter.GoldDetailsAdapter;
import com.guomai.dushuhui.mine.model.GoldDetailsMultiItem;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/19.
 */

public class GoldDetailsActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView listView;
   /* @BindView(R.id.loading_layout)
    View loading_layout;
    @BindView(R.id.tv_list_null)
    TextView tv_list_null;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;*/
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    /*@BindView(R.id.icon_right)
    ImageView icon_right;        */


    GoldDetailsAdapter adapter;
    List<GoldDetailsMultiItem> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_golddetails);
        ButterKnife.bind(this);

        initView();
        setOnClickListener();
    }

    private void setOnClickListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter a, View view, int position) {
                GoldDetailsMultiItem item = adapter.getItem(position);
                if(item.itemType == GoldDetailsMultiItem.MONTH) {

                    if (!item.isOpen) {
//                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.btn_lower));
                        item.isOpen = true;
                        for (int i = 1; i <= 5; i++) {
                            GoldDetailsMultiItem item2 = new GoldDetailsMultiItem();
                            item2.itemType = 2;
                            list.add(position + i, item2);
                        }
                        adapter.setNewData(list);
                    } else {
//                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.btn_right));
                        item.isOpen = false;
                        for (int i = 1; i <= 5; i++) {
                            list.remove(position + 1);
                        }
                        adapter.setNewData(list);
                    }
                }
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter a, View view, int position) {
                GoldDetailsMultiItem item = adapter.getItem(position);
                if(item.itemType == GoldDetailsMultiItem.MONTH){
                    if (view.getId() == R.id.icon_right){
                        Toast.makeText(GoldDetailsActivity.this, "点击展开", Toast.LENGTH_SHORT).show();
                        //view.setBackgroundResource(R.drawable.btn);
                        //view.setImageDrawable( getResources().getDrawable(R.drawable.btn_lower) );
                        //view.setBackgroundResource(R.drawable.btn_lower);


                        //view.setI


                    }
                }
            }
        });
    }

    private void initView() {

        tv_title.setText("书币账单详情");
        tv_title.setTextColor(Color.parseColor("#333333"));
        tv_title.setTextSize(18);
        //tv_list_null.setText("暂无路演");
   //     mToolBar.setNavigationIcon(R.drawable.nav_back);

        listView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        GoldDetailsMultiItem item1 = new GoldDetailsMultiItem();
        item1.itemType = 1;
        item1.isOpen = false;
        GoldDetailsMultiItem item2 = new GoldDetailsMultiItem();
        item2.itemType = 2;
        GoldDetailsMultiItem item3 = new GoldDetailsMultiItem();
        item3.itemType = 2;
        list.add(item1);
        list.add(item1);
        list.add(item1);
        //list.add(item2);
        //list.add(item3);
        adapter = new GoldDetailsAdapter(list);
        listView.setAdapter(adapter);

    }
}
