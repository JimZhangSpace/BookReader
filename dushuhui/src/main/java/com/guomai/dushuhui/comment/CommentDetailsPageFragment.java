package com.guomai.dushuhui.comment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseBackFragment;
import com.guomai.dushuhui.comment.adapter.BookCommentAdapter;
import com.guomai.dushuhui.comment.adapter.CommentDetailsAdapter;
import com.guomai.dushuhui.comment.model.ComDetailsMultiItem;
import com.guomai.dushuhui.comment.model.CommentMultiItem;
import com.guomai.dushuhui.util.SoftKeyboardStateHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/18.
 */

public class CommentDetailsPageFragment extends BaseBackFragment {  //BaseMainFragment
    public static CommentDetailsPageFragment newInstance() {

        Bundle args = new Bundle();

        CommentDetailsPageFragment fragment = new CommentDetailsPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "CommentDetailsPageFragment";

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

    @BindView(R.id.my_reply)
    TextView my_reply_text;
//    @BindView(R.id.edit_content)
    @BindView(R.id.edit_content)
    EditText edit_content;
    @BindView(R.id.layout_input_reply)
    FrameLayout layout_input_reply;
    @BindView(R.id.btn_send)
    TextView btn_send;


    private View mRoot;

    private CommentDetailsAdapter adapter;

    //final SoftKeyboardStateHelper softKeyboardStateHelper = new SoftKeyboardStateHelper(mRoot.findViewById(R.id.layout_root));
    SoftKeyboardStateHelper softKeyboardStateHelper;
// then just handle callbacks



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*mRoot = inflater.inflate(R.layout.frag_common_recycle,
                container, false);*/
        mRoot = inflater.inflate(R.layout.frag_comment_details_page,
                container, false);
//        mainActivity = (MyMainActivity) getActivity();
//        mainActivity.initHeadPanel(Constant.FRAGMENT_FLAG_COMMING_SOON);
        ButterKnife.bind(this,mRoot);
        softKeyboardStateHelper = new SoftKeyboardStateHelper(mRoot.findViewById(R.id.layout_root));
        initView();

        return mRoot;
    }

    @OnClick({R.id.my_reply, R.id.edit_content, R.id.layout_input_reply, R.id.btn_send})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.my_reply:           //点击“我来回复”
                layout_input_reply.setVisibility(View.VISIBLE);
                edit_content.setFocusable(true);
                edit_content.setFocusableInTouchMode(true);
                edit_content.requestFocus();
               //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                InputMethodManager inputManager = (InputMethodManager)edit_content.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(edit_content,0);
                break;
            case R.id.edit_content:       //回复内容输入框

                break;
            case R.id.layout_input_reply:

                break;
            case R.id.btn_send:           //发送按钮

                break;
            default:
                break;
        }
        if(v.getId() == R.id.my_reply){

        }
    }


    private void initView() {
        initToolbarNav(mToolBar);

        tv_title.setText("评论详情");
        tv_title.setTextColor(Color.parseColor("#333333"));
        tv_title.setTextSize(18);
        //tv_list_null.setText("暂无路演");
        mToolBar.setNavigationIcon(R.drawable.nav_back);
        loading_layout.setVisibility(View.GONE);

        ArrayList<ComDetailsMultiItem> list = new ArrayList<>();
        ComDetailsMultiItem item1 = new ComDetailsMultiItem();
        item1.itemType = 1;
        ComDetailsMultiItem item2 = new ComDetailsMultiItem();
        item2.itemType = 2;
        ComDetailsMultiItem item3 = new ComDetailsMultiItem();
        item3.itemType = 3;
        ComDetailsMultiItem item4 = new ComDetailsMultiItem();
        item4.itemType = 3;
        ComDetailsMultiItem item5 = new ComDetailsMultiItem();
        item5.itemType = 3;
        ComDetailsMultiItem item6 = new ComDetailsMultiItem();
        item6.itemType = 3;
        ComDetailsMultiItem item7 = new ComDetailsMultiItem();
        item7.itemType = 4;
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);
        list.add(item6);
        list.add(item7);
        adapter = new CommentDetailsAdapter(list);

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

       softKeyboardStateHelper.addSoftKeyboardStateListener(new SoftKeyboardStateHelper.SoftKeyboardStateListener() {
           @Override
           public void onSoftKeyboardOpened(int keyboardHeightInPx) {

           }

           @Override
           public void onSoftKeyboardClosed() {
               layout_input_reply.setVisibility(View.GONE);
               edit_content.setFocusable(false);
               edit_content.setFocusableInTouchMode(false);
               //edit_content.requestFocus();
           }
       });

        edit_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(final TextView v, final int actionId, final KeyEvent event) {
// 可捕捉右下角的Return按钮

//添加抛出收起事件代码
                Toast.makeText(getContext(),"点击发送按钮", Toast.LENGTH_SHORT).show();
                my_reply_text.setText(edit_content.getText().toString());
                InputMethodManager inputManager = (InputMethodManager)edit_content.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(edit_content.getWindowToken(),0);

                return false;
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        setOnClickListener();
    }

    private void setOnClickListener() {
    }
}