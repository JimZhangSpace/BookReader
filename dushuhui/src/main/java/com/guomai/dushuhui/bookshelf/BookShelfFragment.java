package com.guomai.dushuhui.bookshelf;

import android.os.Bundle;

import com.guomai.dushuhui.base.BaseMainFragment;
import com.guomai.dushuhui.bookshop.BookShopFragment;

/***
 *
 * 书架
 */
public class BookShelfFragment extends BaseMainFragment {
    public static BookShelfFragment newInstance() {
        Bundle args = new Bundle();

        BookShelfFragment fragment = new BookShelfFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public static final String TAG = "BookShelfFragment";
}
