package com.folioreader.ui.folio.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.folioreader.Config;
import com.folioreader.Constants;
import com.folioreader.FolioReader;
import com.folioreader.R;
import com.folioreader.R2;
import com.folioreader.model.HighlightImpl;
import com.folioreader.model.event.BookNoteChangeEvent;
import com.folioreader.model.event.MediaOverlayPlayPauseEvent;
import com.folioreader.model.event.UpdateHighlightEvent;
import com.folioreader.model.sqlite.HighLightTable;
import com.folioreader.ui.folio.adapter.BookNoteAdapter;
import com.folioreader.util.AppUtil;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/***
 * 笔记
 */
public class ReadBookNoteFragment extends Fragment {
    private static final String HIGHLIGHT_ITEM = "highlight_item";
    private View mRootView;
//    private HighlightAdapter adapter;
    private String mBookId;
    @BindView(R2.id.tv_empty)
    TextView tv_empty;
    @BindView(R2.id.tv_tip)
    TextView tv_tip;
    @BindView(R2.id.recycler_view_menu)
    RecyclerView highlightsView;
    private BookNoteAdapter adapter;

    public static ReadBookNoteFragment newInstance(String bookId, String epubTitle) {
        ReadBookNoteFragment highlightFragment = new ReadBookNoteFragment();
        Bundle args = new Bundle();
        args.putString(FolioReader.INTENT_BOOK_ID, bookId);
        args.putString(Constants.BOOK_TITLE, epubTitle);
        highlightFragment.setArguments(args);
        return highlightFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.frag_read_booknote, container, false);
        ButterKnife.bind(this,mRootView);
        EventBus.getDefault().register(this);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Config config = AppUtil.getSavedConfig(getActivity());
        mBookId = getArguments().getString(FolioReader.INTENT_BOOK_ID);

//        if (config.isNightMode()) {
//            mRootView.findViewById(R.id.rv_highlights).
//                    setBackgroundColor(ContextCompat.getColor(getActivity(),
//                            R.color.black));
//        }
        highlightsView.setLayoutManager(new LinearLayoutManager(getActivity()));
        highlightsView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        List<HighlightImpl> list =  HighLightTable.getAllNotes(mBookId);
        if(list!=null&&list.size()>0)
        {
            tv_empty.setVisibility(View.GONE);
            tv_tip.setVisibility(View.GONE);
        }
        else{
            tv_empty.setVisibility(View.VISIBLE);
            tv_tip.setVisibility(View.VISIBLE);
        }
        adapter = new BookNoteAdapter(R.layout.reader_note_item);
        adapter.setNewData(list);
        highlightsView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter a, View view, int position) {
                HighlightImpl highlight =  adapter.getItem(position);
                EventBus.getDefault().post(highlight);
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter a, View view, int position) {
                if(view.getId()==R.id.tv_delete) {
                    HighlightImpl highlight = adapter.getItem(position);
                    if (HighLightTable.deleteHighlight(highlight.getId())) {
                        EventBus.getDefault().post(new UpdateHighlightEvent());
                    }
                    List<HighlightImpl> list =  HighLightTable.getAllNotes(mBookId);
                    adapter.setNewData(list);
                    adapter.notifyDataSetChanged();
                    if(list!=null&&list.size()>0)
                    {
                        tv_empty.setVisibility(View.GONE);
                        tv_tip.setVisibility(View.GONE);
                    }
                    else{
                        tv_empty.setVisibility(View.VISIBLE);
                        tv_tip.setVisibility(View.VISIBLE);
                    }
//                    QMUITipDialog.Builder.
//                    Toast.makeText(getActivity(),"删除笔记成功",Toast.LENGTH_SHORT).show();
                    final QMUITipDialog tipDialog = new QMUITipDialog.Builder(getActivity())
                            .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                            .setTipWord("笔记删除成功")
                            .create();
                    tipDialog.show();
                    highlightsView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tipDialog.dismiss();
                        }
                    },1500);

                }
            }
        });
//        adapter = new HighlightAdapter(getActivity(), list, this, config);
//        highlightsView.setAdapter(adapter);
    }



    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void bookNoteChangedEvent(BookNoteChangeEvent event) {
       if(isAdded())
       {
           List<HighlightImpl> list =  HighLightTable.getAllNotes(mBookId);
           adapter.setNewData(list);
           adapter.notifyDataSetChanged();
           if(list!=null&&list.size()>0)
           {
               tv_empty.setVisibility(View.GONE);
               tv_tip.setVisibility(View.GONE);
           }
           else{
               tv_empty.setVisibility(View.VISIBLE);
               tv_tip.setVisibility(View.VISIBLE);
           }
       }
    }

}
