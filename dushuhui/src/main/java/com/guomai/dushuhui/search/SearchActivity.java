package com.guomai.dushuhui.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.bookshop.model.TestData;
import com.guomai.dushuhui.search.adapter.SearchAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/19.
 */

public class SearchActivity extends AppCompatActivity {

   /* @BindView(R.id.btn_back)
    ImageView btn_back;*/
    @BindView(R.id.search_content)
    EditText search_content;
    @BindView(R.id.btn_cancel)
    TextView btn_cancel;
   /* @BindView(R.id.search_tip)
    TextView search_tip;
    @BindView(R.id.empty_image)
    ImageView empty_image;
    @BindView(R.id.search_result)
    LinearLayout search_result;
    @BindView(R.id.text_found_num)
    TextView text_found_num;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;*/


    @BindView(R.id.recyclerView)
    RecyclerView listView;
    @BindView(R.id.loading_layout)
    View loading_layout;
    @BindView(R.id.tv_list_null)
    TextView tv_list_null;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
   /* @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;*/



    @BindView(R.id.button1)
    Button btn1;
    @BindView(R.id.button2)
    Button btn2;
    @BindView(R.id.button3)
    Button btn3;
    @BindView(R.id.button4)
    Button btn4;

    private SearchAdapter adapter;
    List<TestData> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_two);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        adapter = new SearchAdapter(R.layout.activity_search_text);
        list = new ArrayList<>();
        list.add(new TestData());
      /*  list.add(new TestData());
        list.add(new TestData());
        list.add(new TestData());
        list.add(new TestData());*/
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(adapter);
        adapter.setNewData(list);

        loading_layout.setVisibility(View.GONE);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading_layout.setVisibility(View.GONE);
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                    }
                }, 1000);
            }

            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading_layout.setVisibility(View.GONE);
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                    }
                }, 1000);
            }
        });

        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableRefresh(false);
    }

    @OnClick({R.id.btn_cancel, R.id.button1, R.id.button2, R.id.button3, R.id.button4})
    public void onClick(View view){
        switch (view.getId()){
            /*case R.id.btn_back:
                //Toast.makeText(SearchActivity.this, "点击返回", Toast.LENGTH_SHORT).show();
                finish();
                break;*/
            case R.id.btn_cancel:
                //Toast.makeText(SearchActivity.this, "点击搜索", Toast.LENGTH_SHORT).show();
               /* search_tip.setVisibility(View.GONE);

                empty_image.setVisibility(View.VISIBLE);
                search_result.setVisibility(View.GONE);*/
               finish();
                break;
            case R.id.button1:
                list.clear();
                TestData testDatat1 = new TestData();
                testDatat1.itemType = 1;
                TestData testDatat2 = new TestData();
                testDatat2.itemType = 1;
                TestData testDatat3 = new TestData();
                testDatat3.itemType = 1;
                TestData testDatat4 = new TestData();
                testDatat4.itemType = 1;
                list.add(testDatat1);
                list.add(testDatat2);
                list.add(testDatat3);
                list.add(testDatat4);
                //list.add(new TestData());

                adapter.setNewData(list);
                mRefreshLayout.setEnableRefresh(false);
                mRefreshLayout.setEnableLoadMore(false);
                break;
            case R.id.button2:
                list.clear();
                TestData testDatat5 = new TestData();
                testDatat5.itemType = 2;
                TestData testDatat6 = new TestData();
                testDatat6.itemType = 2;
                TestData testDatat7 = new TestData();
                testDatat7.itemType = 2;
                TestData testDatat8 = new TestData();
                testDatat8.itemType = 2;
                list.add(testDatat5);
                list.add(testDatat6);
                list.add(testDatat7);
                list.add(testDatat8);
                //list.add(new TestData());

                adapter.setNewData(list);
                mRefreshLayout.setEnableRefresh(false);
                mRefreshLayout.setEnableLoadMore(false);
                break;
            case R.id.button3:
                list.clear();
                TestData testDatat9 = new TestData();
                testDatat9.itemType = 3;
                list.add(testDatat9);

                //list.add(new TestData());
                adapter.setNewData(list);
                mRefreshLayout.setEnableRefresh(false);
                mRefreshLayout.setEnableLoadMore(false);
                break;
            case R.id.button4:

                list.clear();
                TestData testDatat10 = new TestData();
                testDatat10.itemType = 4;
                TestData testDatat11 = new TestData();
                testDatat11.itemType = 4;
                TestData testDatat12 = new TestData();
                testDatat12.itemType = 4;
                TestData testDatat13 = new TestData();
                testDatat13.itemType = 4;
                list.add(testDatat10);
                list.add(testDatat11);
                list.add(testDatat12);
                list.add(testDatat13);
                //list.add(new TestData());

                adapter.setNewData(list);
                mRefreshLayout.setEnableRefresh(true);
                mRefreshLayout.setEnableLoadMore(true);
                break;
            default:
                break;
        }
    }
}
