package com.folioreader.ui.folio.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.folioreader.R;
import com.folioreader.R2;
import com.folioreader.model.BookMark;
import com.folioreader.model.BookMarkChangeEvent;
import com.folioreader.model.event.BookNoteChangeEvent;
import com.folioreader.ui.folio.adapter.BookMarkAdapter;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import static com.folioreader.Constants.BOOK_TITLE;

/***
 * 书签
 */
public class ReadBookMarkFragment extends Fragment {
    private View mRootView;
    @BindView(R2.id.recycler_view_menu)
    RecyclerView recyclerView;
    @BindView(R2.id.tv_empty)
    TextView tv_empty;
    BookMarkAdapter adapter;
    String bookId;

    public static ReadBookMarkFragment newInstance(String mBookId) {
        ReadBookMarkFragment fragment = new ReadBookMarkFragment();
        Bundle args = new Bundle();
        args.putString(BOOK_TITLE, mBookId);
        fragment.setArguments(args);
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.frag_read_bookmark, container, false);
        ButterKnife.bind(this,mRootView);
        EventBus.getDefault().register(this);

        return mRootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookId = getArguments().getString(BOOK_TITLE);
        adapter = new BookMarkAdapter(R.layout.list_item_bookmark);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter a, View view, int position) {
                if(view.getId()==R.id.tv_delete)
                {
                    BookMark mark = adapter.getItem(position);
                    LitePal.delete(BookMark.class,mark.getId());
                    final QMUITipDialog tipDialog = new QMUITipDialog.Builder(getActivity())
                            .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                            .setTipWord("删除书签成功")
                            .create();
                    tipDialog.show();
                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tipDialog.dismiss();
                        }
                    },1500);
                    resetBookMark();
                }
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter a, View view, int position) {
                EventBus.getDefault().post(adapter.getItem(position));
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        resetBookMark();
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void bookNoteChanged(BookMarkChangeEvent event) {
        resetBookMark();
    }
    public void resetBookMark()
    {
        List<BookMark> bookMarks = LitePal.where("bookId = ?",bookId).order("id asc").find(BookMark.class);
        if(bookMarks!=null&&bookMarks.size()>0)
        {
            adapter.setNewData(bookMarks);
            adapter.notifyDataSetChanged();
            tv_empty.setVisibility(View.GONE);
        }
        else{
            adapter.setNewData(new ArrayList<BookMark>());
            tv_empty.setVisibility(View.VISIBLE);
        }

    }







}
