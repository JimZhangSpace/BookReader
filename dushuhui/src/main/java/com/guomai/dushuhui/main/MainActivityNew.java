package com.guomai.dushuhui.main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.CheckedTextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.guomai.dushuhui.MyApplication;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.bookshelf.BookShelfFragment;
import com.guomai.dushuhui.bookshop.BookShopFragment;
import com.guomai.dushuhui.leadread.LeadReadFragment;
import com.guomai.dushuhui.mine.MineCenterFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import q.rorbin.badgeview.QBadgeView;

public class MainActivityNew extends SupportActivity {
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_main);
        ButterKnife.bind(this);
        MyApplication.getInst().addActvity(this);
//        MyApplication.getInst().mainAct = this;




        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_LOGS, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
        SupportFragment firstFragment = findFragment(LeadReadFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] =   LeadReadFragment.newInstance();
            mFragments[SECOND] = BookShopFragment.newInstance();
            mFragments[THIRD] = BookShelfFragment.newInstance();
            mFragments[FOUTH] = MineCenterFragment.newInstance();
//            mineCenterFragment = (MineCenterFragment) mFragments[FOUTH];
//            homePageFragment = (HomePageFragment) mFragments[FIRST];

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOUTH]

            );
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(BookShopFragment.class);
            mFragments[THIRD] = findFragment(BookShelfFragment.class);
            mFragments[FOUTH] = findFragment(MineCenterFragment.class);
        }
    }


    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            ActivityCompat.finishAfterTransition(this);
        }
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        MyApplication.getInst().removeActvity(this);


    }


    public static void start(Context context) {
        start(context, null);
    }

    public static void start(Context context, Intent extras) {

        List<Activity> acts = MyApplication.getInst().getAllActivity();
        for(Activity act : acts)
        {
            act.finish();
        }
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }

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
}
