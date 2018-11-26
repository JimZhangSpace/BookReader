package com.guomai.dushuhui.mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseMainFragment;
import com.guomai.dushuhui.bookshop.TejiaFragment;
import com.guomai.dushuhui.bookshop.adapter.ShudanPageAdapter;
import com.guomai.dushuhui.bookshop.model.TestData;
import com.guomai.dushuhui.main.MainFragment;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/***
 * 个人中心
 */
public class MineCenterFragment extends BaseMainFragment {

    /*@BindView(R.id.message_image)
    ImageView message_image;*/
    @BindView(R.id.mine_name)
    TextView my_name;
    @BindView(R.id.change_name)
    ImageView change_name;
    @BindView(R.id.mine_signature)
    TextView mine_signature;
    @BindView(R.id.my_gold)
    LinearLayout my_gold;
    @BindView(R.id.gold_receive)
    LinearLayout gold_receive;
    @BindView(R.id.mine_image)
    CircleImageView mine_image;
    @BindView(R.id.buy_vip)
    LinearLayout buy_vip;
    @BindView(R.id.mine_invite)
    LinearLayout mine_invite;
    @BindView(R.id.mine_comment)
    LinearLayout mine_comment;
    @BindView(R.id.mine_note)
    LinearLayout mine_note;
    @BindView(R.id.mine_about)
    LinearLayout mine_about;
    @BindView(R.id.mine_setting)
    LinearLayout mine_setting;

    CircleImageView myImage;



    private View mRoot;

    public static MineCenterFragment newInstance() {
        Bundle args = new Bundle();

        MineCenterFragment fragment = new MineCenterFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public static final String TAG = "MineCenterFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*mRoot = inflater.inflate(R.layout.frag_common_recycle,
                container, false);*/
        mRoot = inflater.inflate(R.layout.frag_mine_page,
                container, false);
//        mainActivity = (MyMainActivity) getActivity();
//        mainActivity.initHeadPanel(Constant.FRAGMENT_FLAG_COMMING_SOON);
        ButterKnife.bind(this,mRoot);

        initView();
        return mRoot;
    }

    /*@OnClick(R.id.message_image)
    public void onclick(View v){
        if(v.getId()==R.id.message_image){
            Toast.makeText(getContext(), "点击头像",Toast.LENGTH_SHORT).show();
            EditInfoFragment fragment = EditInfoFragment.newInstance();
            MainFragment mainFragment = (MainFragment)getParentFragment();
            mainFragment.startBrotherFragment(fragment);
        }
    }*/

    @OnClick({R.id.mine_name,R.id.change_name,R.id.mine_signature,R.id.my_gold,
            R.id.gold_receive,R.id.mine_image,R.id.buy_vip,R.id.mine_invite,R.id.mine_comment,
            R.id.mine_note,R.id.mine_about,R.id.mine_setting})
    public void onClick(View v){
        switch (v.getId()){
            /*case R.id.message_image:
                Toast.makeText(getContext(), "点击头像",Toast.LENGTH_SHORT).show();
                EditInfoFragment fragment = EditInfoFragment.newInstance();
                MainFragment mainFragment = (MainFragment)getParentFragment();
                mainFragment.startBrotherFragment(fragment);
                break;*/
            case R.id.mine_name:

                break;
            case R.id.change_name:
                EditInfoFragment fragment = EditInfoFragment.newInstance();
                MainFragment mainFragment = (MainFragment)getParentFragment();
                mainFragment.startBrotherFragment(fragment);
                break;
            case R.id.mine_signature:

                break;
            case R.id.my_gold:
                MyGoldPageFragment myGoldPageFragment = MyGoldPageFragment.newInstance();
                MainFragment mainFragment6 = (MainFragment)getParentFragment();
                mainFragment6.startBrotherFragment(myGoldPageFragment);
                break;
            case R.id.gold_receive:
                ReceiveGoldFragment fragment2 = ReceiveGoldFragment.newInstance();
                MainFragment mainFragment2 = (MainFragment)getParentFragment();
                mainFragment2.startBrotherFragment(fragment2);
                break;
            case R.id.mine_image:

                break;
            case R.id.buy_vip:
                Intent intent = new Intent(getActivity(), BuyVIPActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_invite:
                Intent intent2 = new Intent(getActivity(), InviteFriendActivity.class);
                startActivity(intent2);
                break;
            case R.id.mine_comment:
                //Toast.makeText(getContext(), "点击头像",Toast.LENGTH_SHORT).show();
                CommentPageFragment commentPageFragment = CommentPageFragment.newInstance();
                MainFragment mainFragment3 = (MainFragment)getParentFragment();
                mainFragment3.startBrotherFragment(commentPageFragment);
                break;
            case R.id.mine_note:

                break;
            case R.id.mine_about:
                AboutPageFragment aboutPageFragment = AboutPageFragment.newInstance();
                MainFragment mainFragment4 = (MainFragment)getParentFragment();
                mainFragment4.startBrotherFragment(aboutPageFragment);
                break;
            case R.id.mine_setting:
                SettingsFragment settingsFragment = SettingsFragment.newInstance();
                MainFragment mainFragment5 = (MainFragment)getParentFragment();
                mainFragment5.startBrotherFragment(settingsFragment);
                break;
            default:
                break;
        }
    }

    private void initView() {


        /*initToolbarNav(mToolBar);
        tv_title.setText("推荐书单");
        tv_title.setTextColor(Color.parseColor("#333333"));
        tv_title.setTextSize(18);
        //tv_list_null.setText("暂无路演");
        mToolBar.setNavigationIcon(R.drawable.nav_back);
        loading_layout.setVisibility(View.GONE);

        adapter = new ShudanPageAdapter(R.layout.shudan_list_item);

//        adapter.addHeaderView(bannerViewHeader);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.setAdapter(adapter);
        List<TestData> list = new ArrayList<>();
        list.add(new TestData());
        list.add(new TestData());
        adapter.setNewData(list);

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

        mRefreshLayout.setEnableLoadMore(false);*/
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
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        setClickListener();
    }

    /*private void setClickListener() {
        switch ()
    }
*/

}
