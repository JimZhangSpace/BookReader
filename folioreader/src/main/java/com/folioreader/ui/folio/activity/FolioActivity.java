/*
* Copyright (C) 2016 Pedro Paulo de Amorim
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.folioreader.ui.folio.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.folioreader.Config;
import com.folioreader.Constants;
import com.folioreader.FolioReader;
import com.folioreader.R;
import com.folioreader.R2;
import com.folioreader.model.BookMark;
import com.folioreader.model.BookMarkChangeEvent;
import com.folioreader.model.EditNoteEvent;
import com.folioreader.model.HighLight;
import com.folioreader.model.HighlightImpl;
import com.folioreader.model.PageChangeEvent;
import com.folioreader.model.ReadPosition;
import com.folioreader.model.TOCLinkWrapper;
import com.folioreader.model.event.MediaOverlayPlayPauseEvent;
import com.folioreader.model.event.PageCalculatedEvent;
import com.folioreader.model.event.ReloadDataEvent;
import com.folioreader.ui.folio.adapter.FolioPageFragmentAdapter;
import com.folioreader.ui.folio.adapter.PageAdapter;
import com.folioreader.ui.folio.adapter.ReaderFontAdapter;
import com.folioreader.ui.folio.fragment.FolioPageFragment;
import com.folioreader.ui.folio.fragment.ReadBookCatalogFragment;
import com.folioreader.ui.folio.fragment.ReadBookMarkFragment;
import com.folioreader.ui.folio.fragment.ReadBookNoteFragment;
import com.folioreader.model.ReaderFontModel;
import com.folioreader.ui.folio.presenter.MainMvpView;
import com.folioreader.ui.folio.presenter.MainPresenter;
import com.folioreader.util.AppUtil;
import com.folioreader.util.FileUtil;
import com.folioreader.util.UiUtil;
import com.folioreader.view.DirectionalViewpager;
import com.folioreader.view.FolioWebView;
import com.folioreader.view.MediaControllerCallback;
import com.folioreader.view.MediaControllerView;
import com.jkb.slidemenu.SlideMenuLayout;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.xw.repo.BubbleSeekBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.LitePal;
import org.readium.r2_streamer.model.container.Container;
import org.readium.r2_streamer.model.container.EpubContainer;
import org.readium.r2_streamer.model.publication.EpubPublication;
import org.readium.r2_streamer.model.publication.link.Link;
import org.readium.r2_streamer.model.tableofcontents.TOCLink;
import org.readium.r2_streamer.server.EpubServer;
import org.readium.r2_streamer.server.EpubServerSingleton;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.folioreader.Constants.CHAPTER_SELECTED;
import static com.folioreader.Constants.HIGHLIGHT_SELECTED;
import static com.folioreader.Constants.SELECTED_CHAPTER_POSITION;
import static com.folioreader.Constants.TYPE;

public class FolioActivity
        extends AppCompatActivity
        implements FolioActivityCallback,
        FolioWebView.ToolBarListener,
        MainMvpView,
        MediaControllerCallback{

    private static final String LOG_TAG = "FolioActivity";
    public static final String INTENT_EPUB_SOURCE_PATH = "com.folioreader.epub_asset_path";
    public static final String INTENT_EPUB_SOURCE_TYPE = "epub_source_type";
    public static final String INTENT_HIGHLIGHTS_LIST = "highlight_list";
    public static final String EXTRA_READ_POSITION = "com.folioreader.extra.READ_POSITION";
    private static final String BUNDLE_READ_POSITION_CONFIG_CHANGE = "BUNDLE_READ_POSITION_CONFIG_CHANGE";
    private static final String BUNDLE_TOOLBAR_IS_VISIBLE = "BUNDLE_TOOLBAR_IS_VISIBLE";
    public enum EpubSourceType {
        RAW,
        ASSETS,
        SD_CARD
    }

    private final String TAG = "FolioActivity";
    public static final int ACTION_CONTENT_HIGHLIGHT = 77;
    private String bookFileName;
    private static final String HIGHLIGHT_ITEM = "highlight_item";
    private boolean isQuickChangePage = false;

    @BindView(R2.id.main)
    View contentView;
    @BindView(R2.id.mainSlideMenu)
    SlideMenuLayout mainSlideMenu;
    @BindView(R2.id.segment_layout)
    SegmentTabLayout segmentTabLayout;
    @BindView(R2.id.viewpager)
    ViewPager viewPager;



    @BindView(R2.id.folioPageViewPager)
    DirectionalViewpager mFolioPageViewPager;
    /**顶部导航栏*/
    @BindView(R2.id.toolbar_container)
    View toolbar;
    @BindView(R2.id.iv_back)
    ImageView btn_back;
    @BindView(R2.id.iv_bookmark)
    ImageView iv_bookmark;
    @BindView(R2.id.iv_search)
    ImageView iv_search;
    @BindView(R2.id.tv_read_catalog)
    TextView tv_read_catalog;
    @BindView(R2.id.tv_read_setting)
    TextView tv_read_setting;
    @BindView(R2.id.layout_read_progress)
    View layout_read_progress;


    /**书籍底部菜单窗口*/
    @BindView(R2.id.read_bottom_menu)
    View read_bottom_menu;
    /**书籍进度悬浮窗*/
    @BindView(R2.id.read_progress_menu)
    View read_progress_menu;
    /**悬浮窗当前章节名称*/
    @BindView(R2.id.tv_read_chapter)
    TextView tv_read_chapter;
    /**悬浮窗口当前阅读进度*/
    @BindView(R2.id.tv_read_progress)
    TextView tv_read_progress;



    @BindView(R2.id.read_seekbar)
    BubbleSeekBar read_seekbar;
    /**底部按钮当前阅读进度*/
    @BindView(R2.id.tv_read_menu_progress)
    TextView tv_read_menu_progress;

    /**当前剩余页码*/
    @BindView(R2.id.tv_page_left)
    TextView tv_page_left;

    //阅读设置按钮
    @BindView(R2.id.read_setting_menu)
    View read_setting_menu;
    @BindView(R2.id.read_font_setting)
    View read_font_setting;
    @BindView(R2.id.read_light_small)
    ImageView read_light_small;
    @BindView(R2.id.read_light_big)
    ImageView read_light_big;
    @BindView(R2.id.read_light_seekbar)
    BubbleSeekBar read_light_seekbar;
    @BindView(R2.id.btn_font_small)
    ImageButton btn_font_small;
    @BindView(R2.id.btn_font_big)
    ImageButton btn_font_big;
    @BindView(R2.id.tv_font_set_tip)
    TextView tv_font_set_tip;
    @BindView(R2.id.tv_current_font)
    TextView tv_current_font;
    @BindView(R2.id.iv_font_setting_arrow)
    ImageView iv_font_setting_arrow;
    @BindView(R2.id.cb_read_setting_white)
    CheckBox cb_read_setting_white;
    @BindView(R2.id.cb_read_setting_yellow)
    CheckBox cb_read_setting_yellow;
    @BindView(R2.id.cb_read_setting_brown)
    CheckBox cb_read_setting_brown;
    @BindView(R2.id.cb_read_setting_blue)
    CheckBox cb_read_setting_blue;
    @BindView(R2.id.cb_read_setting_black)
    CheckBox cb_read_setting_black;
    @BindView(R2.id.recyclerview)
    RecyclerView font_recyclerview;

    /**当前资源章节位置*/
    private int mChapterPosition,quickChapterPositon;
    private FolioPageFragmentAdapter mFolioPageFragmentAdapter;
    private ReadPosition entryReadPosition;
    private ReadPosition lastReadPosition;
    private Bundle outState;
    private Bundle savedInstanceState;
    //书籍资源章节
    private List<Link> mSpineReferenceList = new ArrayList<>();
    //书籍目录章节
    private List<TOCLink> mTocLinks = new ArrayList<>();
    private EpubServer mEpubServer;
    private String mBookId;
    private String mEpubFilePath;
    private EpubSourceType mEpubSourceType;

    String[] bgColors = {"#ffffff","#bb9d77","#615048","#2d3245","#323333"};
    String[] menuColors = {"#f5f7f2","#caac84","#8f7769","#373e52","#1b1b1b"};
    String[] fontColors = {"#999999","#816540","#bcada5","#8991a8","#989898"};
    String whiteThemeTintColor = "#555555";
    String darkThemeTintColor = "#ffffff";
    String whiteThemeFontColor = "#5555557f";
    String darkThmeFontColor = "#ffffff7f";
    String[] readSettingBgColors = {"#f5f7f2","#caac84","#8f7769","#373e52","#1b1b1b"};

    int mEpubRawId = 0;
    @BindView(R2.id.media_controller_view)
    MediaControllerView mediaControllerView;
    private Config.Direction direction = Config.Direction.HORIZONTAL;
    Config config;
    /**用来计算页码的fragment*/
    FolioPageFragment pageCaculateFrag;
    /**当前计算到的页码*/
    int currentCaculateIndex;
    /**每个资源章节的页码数组*/
    List<Integer> pageCoutArr = new ArrayList<>();

    /**当前页码索引*/
    int currentPageIndex;

    /**书籍页码总数*/
    int totalPageIndex;


    //当前所有的书签列表
    List<BookMark> bookMarks = new ArrayList<>();
    /**当前章节的标题*/
    private String currentChapterTitle;

    private String[] fontArray = new String[]{"系统字体","思源黑体","思源宋体"};
    private List<ReaderFontModel> fontModelList = new ArrayList<>();
    private ReaderFontAdapter fontAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //Fix for screen get turned off while reading
        setConfig(savedInstanceState);
        setContentView(R.layout.folio_activity_new);

        ButterKnife.bind(this);
//        BackgroundLibrary.inject(this);
        this.savedInstanceState = savedInstanceState;

        bookMarks = LitePal.findAll(BookMark.class);
        mBookId = getIntent().getStringExtra(FolioReader.INTENT_BOOK_ID);
        mEpubSourceType = (EpubSourceType)
                getIntent().getExtras().getSerializable(FolioActivity.INTENT_EPUB_SOURCE_TYPE);
        if (mEpubSourceType.equals(EpubSourceType.RAW)) {
            mEpubRawId = getIntent().getExtras().getInt(FolioActivity.INTENT_EPUB_SOURCE_PATH);
        } else {
            mEpubFilePath = getIntent().getExtras()
                    .getString(FolioActivity.INTENT_EPUB_SOURCE_PATH);
        }
//        mediaControllerView = findViewById(R.id.media_controller_view);

        mediaControllerView.setListeners(this);

        if (ContextCompat.checkSelfPermission(FolioActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(FolioActivity.this, Constants.getWriteExternalStoragePerms(), Constants.WRITE_EXTERNAL_STORAGE_REQUEST);
        } else {
            setupBook();
        }
        EventBus.getDefault().register(this);


        int light = UiUtil.getScreenBrightness(this);
        read_light_seekbar.setProgress(light);
        initToolbar(savedInstanceState);
        initListener();

        initFont();
        updateTheme();

    }


    /**初始化字体*/
    private void initFont() {
        for(int i=0;i<fontArray.length;i++)
        {
            ReaderFontModel model = new ReaderFontModel();
            model.font = 5+i;
            model.fontName = fontArray[i];
            model.isSelected = config.getFont()==model.font;
            fontModelList.add(model);
        }
        fontAdapter = new ReaderFontAdapter(R.layout.reader_font_item);
        fontAdapter.setNewData(fontModelList);
        font_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        font_recyclerview.setAdapter(fontAdapter);
        fontAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter a, View view, int position) {


                config.setFont(fontModelList.get(position).font);
                AppUtil.saveConfig(FolioActivity.this, config);
                EventBus.getDefault().post(new ReloadDataEvent());
                for(ReaderFontModel model:fontModelList)
                {
                    model.isSelected = model.font==config.getFont();
                }
                fontAdapter.setNewData(fontModelList);
                fontAdapter.notifyDataSetChanged();
                read_font_setting.setVisibility(View.GONE);

            }
        });
    }


    /**设置监听**/
    private void initListener() {

        read_seekbar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                Log.e(TAG,"onProgressChanged");
                if(fromUser) {
                    read_progress_menu.setVisibility(View.VISIBLE);
                    onQuickChangePage(progress);
                }

            }



            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
                Log.e(TAG,"getProgressOnActionUp");
                read_progress_menu.setVisibility(View.GONE);
                //当手指弹起时调用
                onQuickChangePageEnd();


            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                Log.e(TAG,"getProgressOnFinally");
            }
        });
        read_light_seekbar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                UiUtil.setBrightness(FolioActivity.this,progress);
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {

            }
        });


    }

    private void initToolbar(Bundle savedInstanceState) {

//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setListeners(this);
        if (savedInstanceState != null) {
            toolbar.setVisibility(savedInstanceState.getBoolean(BUNDLE_TOOLBAR_IS_VISIBLE)?View.VISIBLE:View.GONE);
//            if (toolbar.getVisible()) {
//                toolbar.show();
//            } else {
//                toolbar.hide();
//
//            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            config = AppUtil.getSavedConfig(getApplicationContext());
            int color;
            if (config.getThemeMode()==Config.THEME_BLACK||config.getThemeMode()==Config.THEME_BLUE) {
                color = ContextCompat.getColor(this, R.color.black);
            } else {
                int[] attrs = {android.R.attr.navigationBarColor};
                TypedArray typedArray = getTheme().obtainStyledAttributes(attrs);
                color = typedArray.getColor(0, ContextCompat.getColor(this, R.color.read_bg_color));
            }
            getWindow().setNavigationBarColor(color);
        }


    }





    /**跳转到目录页面*/
    public void startContentHighlightActivity() {
        Intent intent = new Intent(FolioActivity.this, ContentHighlightActivity.class);
        intent.putExtra(CHAPTER_SELECTED, mSpineReferenceList.get(mChapterPosition).href);
        intent.putExtra(FolioReader.INTENT_BOOK_ID, mBookId);
        intent.putExtra(Constants.BOOK_TITLE, bookFileName);
        startActivityForResult(intent, ACTION_CONTENT_HIGHLIGHT);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        hideToolBarIfVisible();
        read_bottom_menu.setVisibility(View.GONE);

    }

    private void initBook(String mEpubFileName, int mEpubRawId, String mEpubFilePath, EpubSourceType mEpubSourceType) {
        try {
            int portNumber = getIntent().getIntExtra(Config.INTENT_PORT, Constants.PORT_NUMBER);
            mEpubServer = EpubServerSingleton.getEpubServerInstance(portNumber);
            mEpubServer.start();
            String path = FileUtil.saveEpubFileAndLoadLazyBook(FolioActivity.this, mEpubSourceType, mEpubFilePath,
                    mEpubRawId, mEpubFileName);
            addEpub(path);

            String urlString = Constants.LOCALHOST + bookFileName + "/manifest";
            new MainPresenter(this).parseManifest(urlString);

        } catch (IOException e) {
            Log.e(LOG_TAG, "initBook failed", e);
        }
    }

    private void addEpub(String path) throws IOException {
        Container epubContainer = new EpubContainer(path);
        mEpubServer.addEpub(epubContainer, "/" + bookFileName);
        getEpubResource();
    }

    private void getEpubResource() {
    }

    @Override
    public void onDirectionChange(@NonNull Config.Direction newDirection) {
        Log.v(LOG_TAG, "-> onDirectionChange");

        FolioPageFragment folioPageFragment = (FolioPageFragment)
                mFolioPageFragmentAdapter.getItem(mFolioPageViewPager.getCurrentItem());
        entryReadPosition = folioPageFragment.getLastReadPosition();

        direction = newDirection;

        mFolioPageViewPager.setDirection(newDirection);
        mFolioPageFragmentAdapter = new FolioPageFragmentAdapter(getSupportFragmentManager(),
                        mSpineReferenceList, bookFileName, mBookId);
        mFolioPageViewPager.setAdapter(mFolioPageFragmentAdapter);
        mFolioPageViewPager.setCurrentItem(mChapterPosition);
    }


    @Override
    public void hideOrShowToolBar() {
        if(toolbar.getVisibility()==View.VISIBLE)
        {
            toolbar.setVisibility(View.GONE);
        }
        else{
            toolbar.setVisibility(View.VISIBLE);
        }
//        toolbar.showOrHideIfVisible();
        if(toolbar.getVisibility()==View.VISIBLE)
        {//显示
            if(pageCoutArr.size()<mSpineReferenceList.size())
            {
               final QMUITipDialog tipDialog = new QMUITipDialog.Builder(this)
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_INFO)
                        .setTipWord("页码尚未计算完毕，请稍后再试")
                        .create();
               tipDialog.show();
               toolbar.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       tipDialog.dismiss();
                   }
               },1500);
               return;
            }
            read_bottom_menu.setVisibility(View.VISIBLE);
            read_progress_menu.setVisibility(View.GONE);
            resetPageAndProgress();

        }
        else{
            read_bottom_menu.setVisibility(View.GONE);
            read_font_setting.setVisibility(View.GONE);
            read_setting_menu.setVisibility(View.GONE);
            read_progress_menu.setVisibility(View.GONE);
        }

    }

    /**重置书籍进度条和页码**/
    private void resetPageAndProgress()
    {
        //计算出当前页码和总页码
        int totalPage = 0;
        for(Integer page:pageCoutArr)
        {
            totalPage+=page;
        }
        int currentIndex = 0;
        for(int i=0;i<mChapterPosition;i++)
        {
            currentIndex += pageCoutArr.get(i);
        }
        FolioPageFragment fragment = (FolioPageFragment) mFolioPageFragmentAdapter.getItem(mChapterPosition);
        currentIndex += fragment.getmCurrentPage();
        float progress = currentIndex*1.0f/totalPage;

        read_seekbar.setProgress(progress*100);

        currentPageIndex = currentIndex;
        totalPageIndex = totalPage;
        tv_read_menu_progress.setText(String.format("%d/%d",currentIndex,totalPageIndex));
        tv_page_left.setText(String.format("剩%d页",totalPageIndex-currentIndex));

    }


    /**快速翻页结束*/
    private void onQuickChangePageEnd()
    {

        //1.计算出章节内的页码
        int tempPageNumber = 0;
        for(int i=0;i<pageCoutArr.size();i++)
        {
            tempPageNumber += pageCoutArr.get(i);

            if(tempPageNumber>=currentPageIndex)
            {//如果当前postion的总页码大于当前页码，则说明已经移动到该页码所在章节

                break;
            }
        }
        int chapterPage = pageCoutArr.get(quickChapterPositon);
        int pageInChpater = chapterPage - (tempPageNumber-currentPageIndex) - 1;
        //设置webview的偏移量
        if(mChapterPosition==quickChapterPositon)
        {
            FolioPageFragment folioPageFragment = (FolioPageFragment)
                    mFolioPageFragmentAdapter.getItem(mChapterPosition);


            folioPageFragment.scrollToPage(pageInChpater);
        }
        else{
            mChapterPosition = quickChapterPositon;
            mFolioPageViewPager.setCurrentItem(mChapterPosition);
            FolioPageFragment folioPageFragment = (FolioPageFragment)
                    mFolioPageFragmentAdapter.getItem(mChapterPosition);
            folioPageFragment.scrollToNewChapterPage(pageInChpater);


        }
    }


    /**快速翻页中**/
    private void onQuickChangePage(int progress)
    {
        //只更新标题和当前页码进度
        currentPageIndex = (int) (progress*totalPageIndex/100.f);

        int tempPageNumber = 0;
        for(int i=0;i<pageCoutArr.size();i++)
        {
            tempPageNumber += pageCoutArr.get(i);
            if(tempPageNumber>=currentPageIndex)
            {//如果当前postion的总页码大于当前页码，则说明已经移动到该页码所在章节

                quickChapterPositon = i;
                break;
            }
        }
        //设置标题
        tv_read_menu_progress.setText(String.format("%d/%d",currentPageIndex,totalPageIndex));
        tv_page_left.setText(String.format("剩%d页",totalPageIndex-currentPageIndex));

        Log.e(TAG,"progress:"+progress+",quickChapterPostion:"+quickChapterPositon+",currentPageIndex:"+currentPageIndex+",total:"+totalPageIndex+",chapterTitle:"+ currentChapterTitle);
        tv_read_progress.setText(String.format("%d/%d",currentPageIndex,totalPageIndex));
        //获取标题
        Link spine = mSpineReferenceList.get(quickChapterPositon);
//        currentChapterTitle = spine.getChapterTitle();
        for(TOCLink tocLink : mTocLinks)
        {
            if(spine.href.equals(tocLink.href))
            {
                currentChapterTitle = tocLink.bookTitle;
                break;
            }
        }
        tv_read_chapter.setText(currentChapterTitle);

    }


    @Override
    public void hideToolBarIfVisible() {
        toolbar.setVisibility(View.GONE);
        read_bottom_menu.setVisibility(View.GONE);
        read_progress_menu.setVisibility(View.GONE);
    }

    @Override
    public void setPagerToPosition(String href) {
    }

    @Override
    public ReadPosition getEntryReadPosition() {
        if (entryReadPosition != null) {
            ReadPosition tempReadPosition = entryReadPosition;
            entryReadPosition = null;
            return tempReadPosition;
        }
        return null;
    }

    @Override
    public void goToChapter(String href) {
        href = href.substring(href.indexOf(bookFileName + "/") + bookFileName.length() + 1);
        for (Link spine : mSpineReferenceList) {
            if (spine.href.contains(href)) {
                mChapterPosition = mSpineReferenceList.indexOf(spine);
                mFolioPageViewPager.setCurrentItem(mChapterPosition);
                currentChapterTitle = spine.getChapterTitle();
//                toolbar.setTitle(spine.getChapterTitle());
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTION_CONTENT_HIGHLIGHT && resultCode == RESULT_OK && data.hasExtra(TYPE)) {

            String type = data.getStringExtra(TYPE);

            if (type.equals(CHAPTER_SELECTED)) {
                String selectedChapterHref = data.getStringExtra(SELECTED_CHAPTER_POSITION);
                for (Link spine : mSpineReferenceList) {
                    if (selectedChapterHref.contains(spine.href)) {
                        mChapterPosition = mSpineReferenceList.indexOf(spine);
                        mFolioPageViewPager.setCurrentItem(mChapterPosition);
                        FolioPageFragment folioPageFragment = (FolioPageFragment)
                                mFolioPageFragmentAdapter.getItem(mChapterPosition);
                        folioPageFragment.scrollToFirst();
                        folioPageFragment.scrollToAnchorId(selectedChapterHref);
                        break;
                    }
                }
            } else if (type.equals(HIGHLIGHT_SELECTED)) {
                HighlightImpl highlightImpl = data.getParcelableExtra(HIGHLIGHT_ITEM);
                mFolioPageViewPager.setCurrentItem(highlightImpl.getPageNumber());
                FolioPageFragment folioPageFragment = (FolioPageFragment)
                        mFolioPageFragmentAdapter.getItem(highlightImpl.getPageNumber());
                folioPageFragment.scrollToHighlightId(highlightImpl.getRangy());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (outState != null)
            outState.putParcelable(BUNDLE_READ_POSITION_CONFIG_CHANGE, lastReadPosition);

        if (mEpubServer != null) {
            mEpubServer.stop();
        }
        EventBus.getDefault().unregister(this);
    }




    @Override
    public int getChapterPosition() {
        return mChapterPosition;
    }

    @Override
    public void onLoadPublication(EpubPublication publication) {
        mSpineReferenceList.addAll(publication.spines);
        mTocLinks.addAll(publication.tableOfContents);
        if (publication.metadata.title != null) {
//            toolbar.setTitle(publication.metadata.title);
            currentChapterTitle = publication.metadata.title;
        }

        if (mBookId == null) {
            if (publication.metadata.identifier != null) {
                mBookId = publication.metadata.identifier;
            } else {
                if (publication.metadata.title != null) {
                    mBookId = String.valueOf(publication.metadata.title.hashCode());
                } else {
                    mBookId = String.valueOf(bookFileName.hashCode());
                }
            }
        }
        configFolio();

        setupCaculateFragment();
        initViewpager();
    }



    private void configFolio() {

//        mFolioPageViewPager = findViewById(R.id.folioPageViewPager);
        // Replacing with addOnPageChangeListener(), onPageSelected() is not invoked
        mFolioPageViewPager.setOnPageChangeListener(new DirectionalViewpager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.v(LOG_TAG, "-> onPageSelected -> DirectionalViewpager -> position = " + position);

                EventBus.getDefault().post(new MediaOverlayPlayPauseEvent(
                        mSpineReferenceList.get(mChapterPosition).href, false, true));
                mediaControllerView.setPlayButtonDrawable();
                mChapterPosition = position;
//                toolbar.setTitle(mSpineReferenceList.get(mChapterPosition).bookTitle);
                currentChapterTitle = mSpineReferenceList.get(mChapterPosition).bookTitle;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if (state == DirectionalViewpager.SCROLL_STATE_IDLE) {
                    int position = mFolioPageViewPager.getCurrentItem();
                    Log.v(LOG_TAG, "-> onPageScrollStateChanged -> DirectionalViewpager -> " +
                            "position = " + position);

                    FolioPageFragment folioPageFragment =
                            (FolioPageFragment) mFolioPageFragmentAdapter.getItem(position - 1);
                    if (folioPageFragment != null)
                        folioPageFragment.scrollToLast();

                    folioPageFragment =
                            (FolioPageFragment) mFolioPageFragmentAdapter.getItem(position + 1);
                    if (folioPageFragment != null)
                        folioPageFragment.scrollToFirst();
                }
            }
        });

        if (mSpineReferenceList != null) {

            mFolioPageViewPager.setDirection(direction);
            mFolioPageFragmentAdapter = new FolioPageFragmentAdapter(getSupportFragmentManager(),
                    mSpineReferenceList, bookFileName, mBookId);
            mFolioPageViewPager.setAdapter(mFolioPageFragmentAdapter);

            ReadPosition readPosition;
            if (savedInstanceState == null) {
                readPosition = getIntent().getParcelableExtra(FolioActivity.EXTRA_READ_POSITION);
                entryReadPosition = readPosition;
            } else {
                readPosition = savedInstanceState.getParcelable(BUNDLE_READ_POSITION_CONFIG_CHANGE);
                lastReadPosition = readPosition;
            }
            mFolioPageViewPager.setCurrentItem(getChapterIndex(readPosition));

        }
    }
    /**
     * Returns the index of the chapter by following priority -
     * 1. id
     * 2. href
     * 3. index
     *
     * @param readPosition Last read position
     * @return index of the chapter
     */
    private int getChapterIndex(ReadPosition readPosition) {
        if (readPosition == null) {
            return 0;

        } else if (!TextUtils.isEmpty(readPosition.getChapterId())) {
            return getChapterIndex("id", readPosition.getChapterId());

        } else if (!TextUtils.isEmpty(readPosition.getChapterHref())) {
            return getChapterIndex("href", readPosition.getChapterHref());

        } else if (readPosition.getChapterIndex() > -1
                && readPosition.getChapterIndex() < mSpineReferenceList.size()) {
            return readPosition.getChapterIndex();
        }

        return 0;
    }

    private int getChapterIndex(String caseString, String value) {
        for (int i = 0; i < mSpineReferenceList.size(); i++) {
            switch (caseString) {
                case "id":
                    if (mSpineReferenceList.get(i).getId().equals(value))
                        return i;
                case "href":
                    if (mSpineReferenceList.get(i).getOriginalHref().equals(value))
                        return i;
            }
        }
        return 0;
    }

    /**
     * If called, this method will occur after onStop() for applications targeting platforms
     * starting with Build.VERSION_CODES.P. For applications targeting earlier platform versions
     * this method will occur before onStop() and there are no guarantees about whether it will
     * occur before or after onPause()
     *
     * @see Activity#onSaveInstanceState(Bundle) of Build.VERSION_CODES.P
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.v(LOG_TAG, "-> onSaveInstanceState");
        this.outState = outState;

        outState.putBoolean(BUNDLE_TOOLBAR_IS_VISIBLE, toolbar.getVisibility()==View.VISIBLE);
    }

    @Override
    public void storeLastReadPosition(ReadPosition lastReadPosition) {
        Log.v(LOG_TAG, "-> storeLastReadPosition");
        this.lastReadPosition = lastReadPosition;
    }

    private void setConfig(Bundle savedInstanceState) {

        Config config;
        Config intentConfig = getIntent().getParcelableExtra(Config.INTENT_CONFIG);
        boolean overrideConfig = getIntent().getBooleanExtra(Config.EXTRA_OVERRIDE_CONFIG, false);
        Config savedConfig = AppUtil.getSavedConfig(this);

        if (savedInstanceState != null) {
            config = savedConfig;

        } else if (savedConfig == null) {
            if (intentConfig == null) {
                config = new Config();
            } else {
                config = intentConfig;
            }

        } else {
            if (intentConfig != null && overrideConfig) {
                config = intentConfig;
            } else {
                config = savedConfig;
            }
        }

        // Code would never enter this if, just added for any unexpected error
        // and to avoid lint warning
        if (config == null)
            config = new Config();

        AppUtil.saveConfig(this, config);
        direction = config.getDirection();
    }

    @Override
    public void play() {
        EventBus.getDefault().post(new MediaOverlayPlayPauseEvent(mSpineReferenceList.get(mChapterPosition).href, true, false));
    }

    @Override
    public void pause() {
        EventBus.getDefault().post(new MediaOverlayPlayPauseEvent(mSpineReferenceList.get(mChapterPosition).href, false, false));
    }

    @Override
    public void onError() {
    }

    private void setupBook() {
        bookFileName = FileUtil.getEpubFilename(this, mEpubSourceType, mEpubFilePath, mEpubRawId);
        initBook(bookFileName, mEpubRawId, mEpubFilePath, mEpubSourceType);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constants.WRITE_EXTERNAL_STORAGE_REQUEST:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setupBook();
                } else {
                    Toast.makeText(this, getString(R.string.cannot_access_epub_message), Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }


    @Override
    public Config.Direction getDirection() {
        return direction;
    }


    ///////////////////自定义方法/////////////////
    private void initViewpager()
    {
        List<String> mTitles = new ArrayList<>();
        mTitles.add("目录");
        mTitles.add("书签");
        mTitles.add("笔记");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(ReadBookCatalogFragment.newInstance(mSpineReferenceList.get(mChapterPosition).href,bookFileName));
        fragments.add(ReadBookMarkFragment.newInstance(mBookId));
        fragments.add(ReadBookNoteFragment.newInstance(mBookId,bookFileName));

        PageAdapter adapter = new PageAdapter(getSupportFragmentManager(),fragments,mTitles);
        viewPager.setAdapter(adapter);
        segmentTabLayout.setTabData(new String[]{"目录","书签","笔记"});
        segmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                segmentTabLayout.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }



    /***
     * 开始计算页码
     */
    private void startCaculatePageCount()
    {
        Log.d(LOG_TAG,"开始计算页码");
        pageCoutArr.clear();
        currentCaculateIndex = 0;
        pageCaculateFrag.setSpineItem(mSpineReferenceList.get(currentCaculateIndex),currentCaculateIndex);
    }


    /**获取章节的页码*/
    private void getChapterPage()
    {



    }

    /**初始化计算fragment*/
    private void setupCaculateFragment()
    {
        pageCaculateFrag = FolioPageFragment.newInstance(0,
                bookFileName, mSpineReferenceList.get(0), mBookId,true);
        switchFragment(pageCaculateFrag);
        /**开始进行页码计算*/
        startCaculatePageCount();

    }


    public void switchFragment(Fragment targetFragment) {
        if (targetFragment == null)
            return;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //目标Fragment替换原来的Fragment
        transaction.replace(R.id.content, targetFragment);
        transaction.commit();
    }




    /***
     * 页码计算完毕的通知
     * @param event
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void PageCalculated(PageCalculatedEvent event) {
        Log.d(LOG_TAG,"PageCalculated:page-"+event.position+",pageCount:"+event.pageCount);
        pageCoutArr.add(event.pageCount);
        if(event.position<mSpineReferenceList.size()-1)
        {
            currentCaculateIndex = event.position+1;
            pageCaculateFrag.setSpineItem(mSpineReferenceList.get(currentCaculateIndex),currentCaculateIndex);
        }
        else{
            //页码计算完毕
            Log.d(LOG_TAG,"页码计算完毕");
            Log.d(LOG_TAG,pageCoutArr.toString()+",size:"+pageCoutArr.size());
        }

    }


    /**重新计算页码**/
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void resetPageCount(ReloadDataEvent reloadDataEvent) {
        startCaculatePageCount();
    }


    /**跳转到某个章节*/
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void nav2Catalog(TOCLinkWrapper tocLinkWrapper) {
        String selectedChapterHref = tocLinkWrapper.getTocLink().href;
        for (Link spine : mSpineReferenceList) {
            if (selectedChapterHref.contains(spine.href)) {
                mChapterPosition = mSpineReferenceList.indexOf(spine);
                mFolioPageViewPager.setCurrentItem(mChapterPosition);
                FolioPageFragment folioPageFragment = (FolioPageFragment)
                        mFolioPageFragmentAdapter.getItem(mChapterPosition);
                folioPageFragment.scrollToFirst();
                folioPageFragment.scrollToAnchorId(selectedChapterHref);
                break;
            }
        }
        mainSlideMenu.closeLeftSlide();
    }


    /***
     * 点击书签通知
     * @param bookMark
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onClickBookMark(BookMark bookMark) {
        if(mChapterPosition==bookMark.getChapterIndex())
        {
            FolioPageFragment folioPageFragment = (FolioPageFragment)
                    mFolioPageFragmentAdapter.getItem(mChapterPosition);
            folioPageFragment.scrollToPage(bookMark.getPageNumber()-1);
        }
        else{
            mChapterPosition = bookMark.getChapterIndex();
            mFolioPageViewPager.setCurrentItem(mChapterPosition);
            FolioPageFragment folioPageFragment = (FolioPageFragment)
                    mFolioPageFragmentAdapter.getItem(mChapterPosition);
//            folioPageFragment.scrollToFirst();
            folioPageFragment.scrollToNewChapterPage(bookMark.getPageNumber()-1);
        }

        mainSlideMenu.closeLeftSlide();

    }
    /**跳转到某个笔记*/
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void nav2BookNote(HighlightImpl highlightImpl) {
        mFolioPageViewPager.setCurrentItem(highlightImpl.getPageNumber());
        FolioPageFragment folioPageFragment = (FolioPageFragment)
                mFolioPageFragmentAdapter.getItem(highlightImpl.getPageNumber());
        folioPageFragment.scrollToHighlightId(highlightImpl.getRangy());
        mainSlideMenu.closeLeftSlide();
    }

      /***
     * 页码计算完毕的通知
     * @param event
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void pageChanged(PageChangeEvent event) {
        Log.d(LOG_TAG,"PageChangeEvent:page-"+event.currentPage+",totalPage:"+event.totalPage);
        int markIndex = getCurrentBookMarkIndex();
//        if(markIndex==-1)
//        {
//            iv_bookmark.setImageBitmap();
//        }
//        else{
//            iv_bookmark.setImageResource(R.mipmap.nav_bookmark_marked);
//        }
        iv_bookmark.setImageResource(markIndex==-1?R.mipmap.nav_bookmark_unmark:R.mipmap.nav_bookmark_marked);
    }

    /***
     * 页码计算完毕的通知
     * @param event
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEditNote(final EditNoteEvent event) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(event.type==HighLight.HighLightAction.NEW_NOTE)
                {//如果是编辑笔记
                    HighlightImpl highlight = event.highLight;
                    Intent intent = new Intent(getApplicationContext(),EditNoteActivity.class);
                    int currentIndex = 0;
                    for(int i=0;i<mChapterPosition;i++)
                    {
                        currentIndex += pageCoutArr.get(i);
                    }
                    FolioPageFragment fragment = (FolioPageFragment) mFolioPageFragmentAdapter.getItem(mChapterPosition);
                    currentIndex += fragment.getmCurrentPage();
                    highlight.setShowPageNumber(currentIndex);
                    highlight.setChapterTitle(currentChapterTitle);
                    intent.putExtra("note",highlight);
                    startActivity(intent);
                }
            }
        });




    }



    @OnClick({R2.id.iv_back,R2.id.iv_bookmark,R2.id.iv_search,R2.id.layout_read_catalog,R2.id.layout_read_setting,R2.id.btn_font_small,R2.id.btn_font_big,R2.id.layout_font_set,
            R2.id.cb_read_setting_white,R2.id.cb_read_setting_yellow,R2.id.cb_read_setting_brown,R2.id.cb_read_setting_blue,R2.id.cb_read_setting_black})
    public void onClick(View v)
    {
        int i = v.getId();//跳转到搜索页面
//跳转到目录页面
//               startContentHighlightActivity();
        if (i == R.id.iv_back) {
            finish();

        } else if (i == R.id.iv_bookmark) {
            doBookMarkAction();

        } else if (i == R.id.iv_search) {

        } else if (i == R.id.layout_read_catalog) {
            mainSlideMenu.openLeftSlide();
            read_bottom_menu.setVisibility(View.GONE);

        } else if (i == R.id.layout_read_setting) {
            read_bottom_menu.setVisibility(View.GONE);
            read_setting_menu.setVisibility(View.VISIBLE);

        } else if(i == R.id.layout_font_set){
            read_setting_menu.setVisibility(View.GONE);
            read_font_setting.setVisibility(View.VISIBLE);
        }
        else if(i == R.id.cb_read_setting_black)
        {
            cb_read_setting_black.setChecked(true);
            cb_read_setting_blue.setChecked(false);
            cb_read_setting_white.setChecked(false);
            cb_read_setting_brown.setChecked(false);
            cb_read_setting_yellow.setChecked(false);
            config.setThemeMode(Config.THEME_BLACK);
            updateTheme();
            AppUtil.saveConfig(this, config);
            EventBus.getDefault().post(new ReloadDataEvent());
        }
        else if(i == R.id.cb_read_setting_white)
        {
            cb_read_setting_black.setChecked(false);
            cb_read_setting_blue.setChecked(false);
            cb_read_setting_white.setChecked(true);
            cb_read_setting_brown.setChecked(false);
            cb_read_setting_yellow.setChecked(false);
            config.setThemeMode(Config.THEME_WHITE);
            updateTheme();
            AppUtil.saveConfig(this, config);
            EventBus.getDefault().post(new ReloadDataEvent());
        }
        else if(i == R.id.cb_read_setting_yellow)
        {
            cb_read_setting_black.setChecked(false);
            cb_read_setting_blue.setChecked(false);
            cb_read_setting_white.setChecked(false);
            cb_read_setting_brown.setChecked(false);
            cb_read_setting_yellow.setChecked(true);
            config.setThemeMode(Config.THEME_YELLOW);
            updateTheme();
            AppUtil.saveConfig(this, config);
            EventBus.getDefault().post(new ReloadDataEvent());
        }
        else if(i == R.id.cb_read_setting_brown)
        {
            cb_read_setting_black.setChecked(false);
            cb_read_setting_blue.setChecked(false);
            cb_read_setting_white.setChecked(false);
            cb_read_setting_brown.setChecked(true);
            cb_read_setting_yellow.setChecked(false);
            config.setThemeMode(Config.THEME_BROWN);
            updateTheme();
            AppUtil.saveConfig(this, config);
            EventBus.getDefault().post(new ReloadDataEvent());
        }
        else if(i == R.id.cb_read_setting_blue)
        {
            cb_read_setting_black.setChecked(false);
            cb_read_setting_blue.setChecked(true);
            cb_read_setting_white.setChecked(false);
            cb_read_setting_brown.setChecked(false);
            cb_read_setting_yellow.setChecked(false);
            config.setThemeMode(Config.THEME_BLUE);
            updateTheme();
            AppUtil.saveConfig(this, config);
            EventBus.getDefault().post(new ReloadDataEvent());
        }

    }


    /**收藏或取消收藏*/
    private void doBookMarkAction() {
        int markIndex = getCurrentBookMarkIndex();

        if(markIndex==-1)
        {//未添加过书签，则添加书签
            FolioPageFragment fragment = (FolioPageFragment) mFolioPageFragmentAdapter.getItem(mChapterPosition);
            BookMark mark = new BookMark();
            mark.setBookId(mBookId);
            mark.setChapterIndex(mChapterPosition);
            mark.setChapterTitle(currentChapterTitle);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            mark.setDate(sdf.format(new Date()));
            mark.setPageNumber(fragment.getmCurrentPage());
            mark.setPageTotal(fragment.getmTotalPages());
            mark.setShowPageNumber(currentPageIndex);
            mark.save();
            final QMUITipDialog tipDialog = new QMUITipDialog.Builder(this)
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                    .setTipWord("添加书签成功")
                    .create();
            tipDialog.show();
            toolbar.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tipDialog.dismiss();
                }
            },1500);
            iv_bookmark.setImageResource(R.mipmap.nav_bookmark_marked);



        }
        else{//已添加过书签，则删除书签
            BookMark mark = bookMarks.get(markIndex);
            LitePal.delete(BookMark.class,mark.getId());
            iv_bookmark.setImageResource(R.mipmap.nav_bookmark_unmark);
        }
        bookMarks = LitePal.where("bookId = ?",mBookId).find(BookMark.class);
        EventBus.getDefault().post(new BookMarkChangeEvent());

    }

    private int getCurrentBookMarkIndex()
    {
        FolioPageFragment fragment = (FolioPageFragment) mFolioPageFragmentAdapter.getItem(mChapterPosition);
        int markIndex = -1;
        for(int i =0;i<bookMarks.size();i++)
        {
            BookMark bookMark = bookMarks.get(i);
            if(bookMark.getChapterIndex()==mChapterPosition)
            {
                float pageValue = Float.valueOf(bookMark.getPageNumber())/Float.valueOf(bookMark.getPageTotal());
                int pageIndex = Math.round(pageValue*fragment.getmTotalPages());
                if(pageIndex==fragment.getmCurrentPage())
                {//如果是当前页码，则说明当前位置有书签
                    markIndex = i;
                    break;
                }
            }
        }




//        var mark : BookMark? = nil
//        var markIndex = -1
//        for i in 0..<bookMarks.count{
//        let bookMark = bookMarks[i]
//        if( bookMark.chapterIndex == currentPage?.chapterIndex)
//        {
//            // 换在合适的位置
//            let pageValue = Float(bookMark.pageNumber)/Float(bookMark.pageTotal)
//            let pageIndex = lroundf(Float((pageIndicatorView?.totalPages)!)*pageValue)
//            if(pageIndex == pageIndicatorView?.currentPage)
//            {
//                mark = bookMark
//                markIndex = i
//                break
//            }
//        }
//    }
//        if(mark == nil)
//        {
//            mark = BookMark()
//        }

        return markIndex;
    }



    private void updateTheme()
    {
        int index = config.getThemeMode();
        int menuBgColor = Color.parseColor(readSettingBgColors[index]);
        int bgColor = Color.parseColor(bgColors[index]);
        int menuFontColor = Color.parseColor(fontColors[index]);
        contentView.setBackgroundColor(bgColor);
        read_bottom_menu.setBackgroundColor(menuBgColor);
        read_setting_menu.setBackgroundColor(menuBgColor);
        read_font_setting.setBackgroundColor(menuBgColor);
        toolbar.setBackgroundColor(menuBgColor);
        Drawable progressDrawable = UiUtil.getTintImage(this,R.mipmap.bg_read_progress,menuBgColor);
        layout_read_progress.setBackground(progressDrawable);
        if(index<=2)
        {
            //顶部导航栏
            btn_back.setImageResource(R.mipmap.nav_back);
            iv_search.setImageResource(R.mipmap.nav_read_search);



            //数据阅读进度悬浮窗
            int whiteThemeColor = Color.parseColor(whiteThemeFontColor);
            tv_read_chapter.setTextColor(whiteThemeColor);
            tv_read_progress.setTextColor(whiteThemeColor);
            //阅读设置
            read_light_small.setImageResource(R.mipmap.read_light_small_gray);
            read_light_big.setImageResource(R.mipmap.read_light_big_gray);
            btn_font_big.setImageResource(R.mipmap.read_font_big_gray);
            btn_font_small.setImageResource(R.mipmap.read_font_small_gray);
            tv_font_set_tip.setTextColor(whiteThemeColor);
            iv_font_setting_arrow.setImageResource(R.mipmap.read_right_arrow);
            //底部按钮设置
            tv_page_left.setTextColor(whiteThemeColor);
            tv_read_menu_progress.setTextColor(whiteThemeColor);
            tv_read_catalog.setTextColor(whiteThemeColor);
            tv_read_setting.setTextColor(whiteThemeColor);
            Drawable catalogDrawable = getResources().getDrawable(R.mipmap.read_catalog);
//            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_read_catalog.setCompoundDrawablesWithIntrinsicBounds(catalogDrawable,null,null,null);
            Drawable settingDrawable = getResources().getDrawable(R.mipmap.read_setting);
            tv_read_setting.setCompoundDrawablesWithIntrinsicBounds(settingDrawable,null,null,null);



        }
        else{
            //顶部导航栏
            btn_back.setImageResource(R.mipmap.nav_back);
            iv_search.setImageResource(R.mipmap.nav_read_search);

            //书籍阅读悬浮窗口
            int darkThemeColor = Color.parseColor(darkThemeTintColor);
            tv_read_chapter.setTextColor(darkThemeColor);
            tv_read_progress.setTextColor(darkThemeColor);

            //阅读设置
            read_light_small.setImageResource(R.mipmap.read_light_small_white);
            read_light_big.setImageResource(R.mipmap.read_light_big_white);
            btn_font_big.setImageResource(R.mipmap.read_font_big_white);
            btn_font_small.setImageResource(R.mipmap.read_font_small_white);
            tv_font_set_tip.setTextColor(darkThemeColor);
            iv_font_setting_arrow.setImageResource(R.mipmap.read_right_arrow_white);
            //底部按钮设置
            tv_page_left.setTextColor(darkThemeColor);
            tv_read_menu_progress.setTextColor(darkThemeColor);
            tv_read_catalog.setTextColor(darkThemeColor);
            tv_read_setting.setTextColor(darkThemeColor);
            Drawable catalogDrawable = UiUtil.getTintImage(this,R.mipmap.read_catalog,darkThemeColor);
//            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_read_catalog.setCompoundDrawablesWithIntrinsicBounds(catalogDrawable,null,null,null);
            Drawable settingDrawable = UiUtil.getTintImage(this,R.mipmap.read_setting,darkThemeColor);
            tv_read_setting.setCompoundDrawablesWithIntrinsicBounds(settingDrawable,null,null,null);
        }




    }




}

