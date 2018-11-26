package com.guomai.dushuhui.bookdetails;

import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.hotspot2.omadm.PpsMoParser;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseBackFragment;
import com.guomai.dushuhui.bookdetails.adapter.BookDetailsPageAdapter;
import com.guomai.dushuhui.bookdetails.adapter.PurchaseGoldAdapter;
import com.guomai.dushuhui.bookdetails.model.BookDetailsMultiItem;
import com.guomai.dushuhui.bookdetails.model.TestData2;
import com.guomai.dushuhui.booklisten.BookListenFragment;
import com.guomai.dushuhui.booklisten.BookPlayPageFragment;
import com.guomai.dushuhui.booklisten.ChapterActivityDialog;
import com.guomai.dushuhui.bookshop.ShudanListPageFragment;
import com.guomai.dushuhui.bookshop.adapter.ShudanPageAdapter;
import com.guomai.dushuhui.bookshop.model.RcmdMultiItem;
import com.guomai.dushuhui.bookshop.model.TestData;
import com.guomai.dushuhui.comment.BookCommentFragment;
import com.guomai.dushuhui.comment.CommentDetailsPageFragment;
import com.guomai.dushuhui.comment.PublishCommentFragment;
import com.guomai.dushuhui.main.MainFragment;
import com.guomai.dushuhui.util.NumberUtils;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.litepal.util.BaseUtility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.simplifyspan.SimplifySpanBuild;
import cn.iwgang.simplifyspan.unit.SpecialTextUnit;

/**
 * Created by Administrator on 2018/9/17.
 */

public class BookDetailsPageFragment extends BaseBackFragment {
    public static BookDetailsPageFragment newInstance() {

        Bundle args = new Bundle();

        BookDetailsPageFragment fragment = new BookDetailsPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "BookDetailsPageFragment";

    @BindView(R.id.recyclerView)
    RecyclerView listView;
    @BindView(R.id.loading_layout)
    View loading_layout;
    @BindView(R.id.tv_list_null)
    TextView tv_list_null;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    @BindView(R.id.btn_shidu)
    Button btn_shidu;
    @BindView(R.id.btn_purchase)
    Button btn_purchase;

    @BindView(R.id.layout_pay_gold)
    LinearLayout layout_pay_gold;
    @BindView(R.id.layout_buy_gold)
    LinearLayout layout_buy_gold;
    @BindView(R.id.layout_pay_tools)
    LinearLayout layout_pay_tools;

    @BindView(R.id.bottom_book)
    LinearLayout bottom_book;
    @BindView(R.id.content_book)
    LinearLayout content_book;

    @BindView(R.id.btn_close)
    ImageView btn_close;
    @BindView(R.id.btn_pay)
    Button btn_pay;
    @BindView(R.id.btn_choice_close)
    ImageView btn_choice_close;
    @BindView(R.id.btn_paytools_close)
    ImageView btn_paytools_close;

    @BindView(R.id.btn_toolspay)
    Button btn_toolspay;

    /*@BindView(R.id.book_introduce_content)
    TextView content;*/

    @BindView(R.id.recyclerView_shubi)
    RecyclerView recyclerView;

    private PurchaseGoldAdapter adapter2;
    List<TestData2> list2;



    private View mRoot;

    private BookDetailsPageAdapter adapter;
   // SimplifySpanBuild currentBuild = new SimplifySpanBuild();





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*mRoot = inflater.inflate(R.layout.frag_common_recycle,
                container, false);*/
        mRoot = inflater.inflate(R.layout.frag_bookdetails_page,
                container, false);
//        mainActivity = (MyMainActivity) getActivity();
//        mainActivity.initHeadPanel(Constant.FRAGMENT_FLAG_COMMING_SOON);
        ButterKnife.bind(this,mRoot);

        initView();
        return mRoot;
    }

    @OnClick({R.id.btn_shidu, R.id.btn_purchase, R.id.btn_close, R.id.btn_pay, R.id.btn_choice_close,
            R.id.btn_paytools_close, R.id.btn_toolspay})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_shidu:
//                Toast.makeText(getActivity(), "点击免费试读", Toast.LENGTH_SHORT).show();
                BookPlayPageFragment bookPlayPageFragment = BookPlayPageFragment.newInstance();
                start(bookPlayPageFragment);
                break;
            case R.id.btn_purchase:
                /*Intent intent = new Intent(getActivity(), PurchaseBookActivityDialog.class);
                startActivity(intent);*/
                layout_pay_gold.setVisibility(View.VISIBLE);
                enableDisableViewGroup(bottom_book,false);
                enableDisableViewGroup(content_book,false);
                mRefreshLayout.setEnableRefresh(false);
                mRefreshLayout.setEnableLoadMore(false);
                listView.setLayoutFrozen(true);

                break;
            case R.id.btn_close:                           //支付书币取消
                layout_pay_gold.setVisibility(View.GONE);
                layout_buy_gold.setVisibility(View.GONE);
                layout_pay_tools.setVisibility(View.GONE);
                enableDisableViewGroup(bottom_book,true);
                enableDisableViewGroup(content_book,true);
                mRefreshLayout.setEnableRefresh(true);
                mRefreshLayout.setEnableLoadMore(false);
                listView.setLayoutFrozen(false);
                break;
            case R.id.btn_pay:                                 //支付书币
                layout_pay_gold.setVisibility(View.GONE);
                layout_buy_gold.setVisibility(View.VISIBLE);
                layout_pay_tools.setVisibility(View.GONE);
                enableDisableViewGroup(bottom_book,false);
                enableDisableViewGroup(content_book,false);
                mRefreshLayout.setEnableRefresh(false);
                mRefreshLayout.setEnableLoadMore(false);
                listView.setLayoutFrozen(true);
                break;
            case R.id.btn_choice_close:                    //选择充值金额取消
                layout_pay_gold.setVisibility(View.GONE);
                layout_buy_gold.setVisibility(View.GONE);
                layout_pay_tools.setVisibility(View.GONE);
                enableDisableViewGroup(bottom_book,true);
                enableDisableViewGroup(content_book,true);
                mRefreshLayout.setEnableRefresh(true);
                mRefreshLayout.setEnableLoadMore(false);
                listView.setLayoutFrozen(false);
                break;
            case R.id.btn_paytools_close:                       //选择支付工具取消
                layout_pay_gold.setVisibility(View.GONE);
                layout_buy_gold.setVisibility(View.GONE);
                layout_pay_tools.setVisibility(View.GONE);
                enableDisableViewGroup(bottom_book,true);
                enableDisableViewGroup(content_book,true);
                mRefreshLayout.setEnableRefresh(true);
                mRefreshLayout.setEnableLoadMore(false);
                listView.setLayoutFrozen(false);
                break;
            case R.id.btn_toolspay:
                final QMUITipDialog tipDialog = new QMUITipDialog.Builder(getActivity())
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                        .setTipWord("您已成功购买")
                        .create();
                tipDialog.show();
                btn_toolspay.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tipDialog.dismiss();
                    }
                },1500);
                break;
            default:
                break;
        }
    }

    public static void enableDisableViewGroup(ViewGroup viewGroup, boolean enabled) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i <childCount; i++) {
            View view = viewGroup.getChildAt(i);
            view.setEnabled(enabled);
            if (view instanceof ViewGroup) {
                enableDisableViewGroup((ViewGroup) view, enabled);
            }
        }
    }


    private void initView() {
        initToolbarNav(mToolBar);
        tv_title.setText("书籍详情");
        tv_title.setTextColor(Color.parseColor("#333333"));
        tv_title.setTextSize(18);
        //tv_list_null.setText("暂无路演");
        mToolBar.setNavigationIcon(R.drawable.nav_back);
        loading_layout.setVisibility(View.GONE);

        ArrayList<BookDetailsMultiItem> list = new ArrayList<>();
        BookDetailsMultiItem item1 = new BookDetailsMultiItem();
        item1.itemType = 1;
        BookDetailsMultiItem item2 = new BookDetailsMultiItem();
        item2.itemType = 2;
        BookDetailsMultiItem item3 = new BookDetailsMultiItem();
        item3.itemType = 3;
        BookDetailsMultiItem item4 = new BookDetailsMultiItem();
        item4.itemType = 4;
        BookDetailsMultiItem item5 = new BookDetailsMultiItem();
        item5.itemType = 4;
        BookDetailsMultiItem item6 = new BookDetailsMultiItem();
        item6.itemType = 5;
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);
        list.add(item6);
        adapter = new BookDetailsPageAdapter(list);

//        adapter.addHeaderView(bannerViewHeader);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.setAdapter(adapter);


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
       /* adapter = new TodaySelectionAdapter(R.layout.today_selection_list_item);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter a, View view, int position) {
                if(MyApplication.getInst().hasLogin) {
                    toRoadShow(adapter.getItem(position));
                }
                else{
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });*/
        /*currentBuild.append(new SpecialTextUnit("内容简介：", getResources().getColor(R.color.black),15));
        currentBuild.append(new SpecialTextUnit("降低了快速减肥拉萨的房间按理说发动机艰苦奋斗是老" +
                "款的房间数量即" +
                "可离开拉风",getResources().getColor(R.color.red), 18));
        content.setText(currentBuild.build());*/

        /*currentBuild.append(new SpecialTextUnit("当前小计:"));
        currentBuild.append(new SpecialTextUnit("123456",getResources().getColor(R.color.red), 18));
        content.setText(currentBuild.build());*/
        adapter2 = new PurchaseGoldAdapter(R.layout.chongzhi_list_item);

//        adapter.addHeaderView(bannerViewHeader);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter2);
        list2 = new ArrayList<>();
        TestData2 t1 = new TestData2();
        t1.direction = 0;
        TestData2 t2 = new TestData2();
        t2.direction = 1;
        TestData2 t3 = new TestData2();
        t3.direction = 0;
        TestData2 t4 = new TestData2();
        t4.direction = 1;
        list2.add(t1);
        list2.add(t2);
        list2.add(t3);
        list2.add(t4);
        adapter2.setNewData(list2);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        setClickListener();
    }

    private void setClickListener() {
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter a, View view, int position) {
                BookDetailsMultiItem item = adapter.getItem(position);
                if(item.getItemType() == BookDetailsMultiItem.FIVE){
                    if(view.getId() == R.id.entry_shuquan) {
                        //判断是否有更多评论，这里直接点击进入全部书评
                        BookCommentFragment bookCommentFragment = BookCommentFragment.newInstance();
                        start(bookCommentFragment);
                    }
                }else if(item.getItemType() == BookDetailsMultiItem.THREE){
                    if(view.getId() == R.id.book_write_comment){
                        PublishCommentFragment publishCommentFragment = PublishCommentFragment.newInstance();
                        start(publishCommentFragment);
                    }
                }else if(item.getItemType() == BookDetailsMultiItem.ONE){
                    if(view.getId() == R.id.book_listen){
                        Toast.makeText(getContext(), "听书", Toast.LENGTH_SHORT).show();
                        BookListenFragment bookListenFragment = BookListenFragment.newInstance();
                        start(bookListenFragment);
                    }
                }else if(item.getItemType() == BookDetailsMultiItem.FOUR){
                    if(view.getId() == R.id.comment_list){
                        CommentDetailsPageFragment commentDetailsPageFragment = CommentDetailsPageFragment.newInstance();
                        start(commentDetailsPageFragment);
                    }
                }
            }
        });

        adapter2.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.rmb_num){
                    //Toast.makeText(PurchaseBookActivityDialog.this, "第"+(position+1)+"章" ,Toast.LENGTH_SHORT).show();
                    //adapter.setNewData(list);
                    layout_pay_gold.setVisibility(View.GONE);
                    layout_buy_gold.setVisibility(View.GONE);
                    layout_pay_tools.setVisibility(View.VISIBLE);
                    enableDisableViewGroup(bottom_book,false);
                    enableDisableViewGroup(content_book,false);
                    mRefreshLayout.setEnableRefresh(false);
                    mRefreshLayout.setEnableLoadMore(false);
                    listView.setLayoutFrozen(true);
                }else if(view.getId() == R.id.rmb_num2){
                    //adapter.setNewData(list);
                }
            }
        });
    }

}

