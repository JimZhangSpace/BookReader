package com.guomai.dushuhui.bookshop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/9/11.
 */

public class PageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> titles;
    public  PageAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }
    public  PageAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;

    }



    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(titles==null)
            return "";
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    public void setData(List<Fragment> fragments,List<String> titles)
    {
        this.fragments = fragments;
        this.titles = titles;
    }
}
