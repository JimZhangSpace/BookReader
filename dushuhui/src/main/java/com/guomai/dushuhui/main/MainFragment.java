package com.guomai.dushuhui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.guomai.dushuhui.MyApplication;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.bookshelf.BookShelfFragment;
import com.guomai.dushuhui.bookshop.BookShopFragment;
import com.guomai.dushuhui.entity.MyMessage;
import com.guomai.dushuhui.leadread.LeadReadFragment;
import com.guomai.dushuhui.mine.MineCenterFragment;
import com.guomai.dushuhui.model.UserDetail;

import org.litepal.crud.DataSupport;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by Administrator on 2018/2/9 0009.
 */

public class MainFragment extends SupportFragment {
    private static final int REQ_MSG = 10;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUTH = 3;
    private SupportFragment[] mFragments = new SupportFragment[4];
    @BindView(R.id.check_leadread)
    CheckedTextView check_leadread;
    @BindView(R.id.check_bookshop)
    CheckedTextView check_bookshop;
    @BindView(R.id.check_bookshelf)
    CheckedTextView check_bookshelf;
    @BindView(R.id.check_mine)
    CheckedTextView chek_mine;
    private QBadgeView mineBadgeView;
//    @BindView(R.id.tv_message_count)
//    TextView tv_message_count;
    MaterialDialog progressDialog;

//    public MineCenterFragment mineCenterFragment;
//    public HomePageFragment homePageFragment;

    private int prePosition,position;

    public void changeTab(int position)
    {
        if(prePosition==position)
        {
            return;
        }
        if(position==0)
        {
            check_leadread.setChecked(true);
            check_bookshop.setChecked(false);
            check_bookshelf.setChecked(false);
            chek_mine.setChecked(false);
        }
        else if(position==1)
        {
            check_leadread.setChecked(false);
            check_bookshop.setChecked(true);
            check_bookshelf.setChecked(false);
            chek_mine.setChecked(false);
        }
        else if(position==2)
        {
            check_leadread.setChecked(false);
            check_bookshop.setChecked(false);
            check_bookshelf.setChecked(true);
            chek_mine.setChecked(false);
        }
        else if(position==3)
        {
            check_leadread.setChecked(false);
            check_bookshop.setChecked(false);
            check_bookshelf.setChecked(false);
            chek_mine.setChecked(true);
        }
        showHideFragment(mFragments[position], mFragments[prePosition]);
        this.position = position;
        prePosition = position;
    }

    public void checkTab(View v)
    {
        switch (v.getId())
        {
            case R.id.check_leadread:
                position = 0;
                if(prePosition==position)
                {
                    return;
                }
//                homePageFragment.refresh();
                check_leadread.setChecked(true);
                check_bookshop.setChecked(false);
                check_bookshelf.setChecked(false);
                chek_mine.setChecked(false);

                showHideFragment(mFragments[position], mFragments[prePosition]);
                prePosition = 0;
                break;
            case R.id.check_bookshop:
                position = 1;
                if(prePosition==position)
                {
                    return;
                }
                check_leadread.setChecked(false);
                check_bookshop.setChecked(true);
                check_bookshelf.setChecked(false);
                chek_mine.setChecked(false);
                showHideFragment(mFragments[position], mFragments[prePosition]);
                prePosition = 1;

                break;
            case R.id.check_bookshelf:
                position = 2;
                if(prePosition==position)
                {
                    return;
                }
                check_leadread.setChecked(false);
                check_bookshop.setChecked(false);
                check_bookshelf.setChecked(true);
                chek_mine.setChecked(false);

                showHideFragment(mFragments[position], mFragments[prePosition]);
                prePosition = 2;
                break;
            case R.id.check_mine:
                position = 3;
                if(prePosition==position)
                {
                    return;
                }
                check_leadread.setChecked(false);
                check_bookshop.setChecked(false);
                check_bookshelf.setChecked(false);
                chek_mine.setChecked(true);
//                MineCenterFragment fragment = (MineCenterFragment) mFragments[position];
//                fragment.refresh();

                showHideFragment(mFragments[position], mFragments[prePosition]);
                prePosition = 3;
                break;
        }
    }


    @OnClick({R.id.check_leadread,R.id.check_bookshop,R.id.check_bookshelf,R.id.check_mine})
    public void ClickBTN(View v)
    {

        checkTab(v);
    }


    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main, container, false);
        ButterKnife.bind(this,view);

        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportFragment firstFragment = findChildFragment(LeadReadFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] =   LeadReadFragment.newInstance();
            mFragments[SECOND] = BookShopFragment.newInstance();
            mFragments[THIRD] = BookShelfFragment.newInstance();
            mFragments[FOUTH] = MineCenterFragment.newInstance();
//            mineCenterFragment = (MineCenterFragment) mFragments[FOUTH];
//            homePageFragment = (HomePageFragment) mFragments[FIRST];

            loadMultipleRootFragment(R.id.fl_tab_container, SECOND,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOUTH]

            );
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(BookShopFragment.class);
            mFragments[THIRD] = findChildFragment(BookShelfFragment.class);
            mFragments[FOUTH] = findChildFragment(MineCenterFragment.class);
        }
    }

    private void initView(View view) {
        prePosition = 1;
        position = 1;
        mineBadgeView = new QBadgeView(getContext());
        mineBadgeView.bindTarget(chek_mine);
        mineBadgeView.setBadgeGravity(Gravity.CENTER | Gravity.END);
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQ_MSG && resultCode == RESULT_OK) {

        }
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        progressDialog = new MaterialDialog.Builder(getActivity())
                .title("玩命加载中")
                .content("请稍后")
                .progress(true, 0)
                .build();
//        getClientConfig();

        if(MyApplication.getInst().hasLogin)
        {
            UserDetail userDetail = MyApplication.getInst().user;
            int unreadCount = DataSupport
                    .where("mineId = ? and state = 0", userDetail.uid+"")
                    .count(MyMessage.class);
            resetBadge(unreadCount);
        }
    }

    public void resetBadge(int badgeCount)
    {

        if(mineBadgeView!=null)
        {
            mineBadgeView.setBadgeText(badgeCount>0?" ":null);
            mineBadgeView.setVisibility(badgeCount>0?View.VISIBLE:View.GONE);
        }

    }




    /**
     * start other BrotherFragment
     */
    public void startBrotherFragment(SupportFragment targetFragment) {
        start(targetFragment);
    }
}