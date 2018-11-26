package com.guomai.dushuhui.booklisten;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseBackFragment;


import com.guomai.dushuhui.player.service.MediaService;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/18.
 */

public class BookPlayPageFragment extends BaseBackFragment {
    public static BookPlayPageFragment newInstance() {

        Bundle args = new Bundle();

        BookPlayPageFragment fragment = new BookPlayPageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public static final String TAG = "BookPlayPageFragment";

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    @BindView(R.id.btn_time)
    ImageView btn_time;
    @BindView(R.id.text_time)
    TextView text_time;
    @BindView(R.id.seek_bar)
    SeekBar seekBar;
    @BindView(R.id.text1)
    TextView textView;
    @BindView(R.id.btn_catalog)
    ImageView btn_catalog;
    @BindView(R.id.btn_play)
    CheckBox btn_play;

    @BindView(R.id.begin_time)
    TextView begin_time;
    @BindView(R.id.end_time)
    TextView end_time;


    private View mRoot;

    //private BookListenerPageAdapter adapter;

    private QMUIListPopup mListPopup;

    DialogChapter dialogChapter;

    private Handler mHandler = new Handler();
    private MediaService.MyBinder mMyBinder;

    //进度条下面的当前进度文字，将毫秒化为m:ss格式
    private SimpleDateFormat time = new SimpleDateFormat("m:ss");
    //“绑定”服务的intent
    Intent MediaServiceIntent;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*mRoot = inflater.inflate(R.layout.frag_common_recycle,
                container, false);*/
        mRoot = inflater.inflate(R.layout.frag_listenbook_page ,
                container, false);
//        mainActivity = (MyMainActivity) getActivity();
//        mainActivity.initHeadPanel(Constant.FRAGMENT_FLAG_COMMING_SOON);
        ButterKnife.bind(this,mRoot);

        initView();
        return mRoot;
    }



    @OnClick({R.id.btn_time, R.id.btn_catalog, R.id.btn_play})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_time:
                initListPopupIfNeed();
                mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_BOTTOM);
                mListPopup.show(v);
                //mActionButton2.setText(getContext().getResources().getString(R.string.popup_list_action_button_text_hide));
                break;
            case R.id.btn_catalog:
               // dialogChapter.show();
                Intent intent = new Intent(getActivity(), ChapterActivityDialog.class);
               startActivity(intent);
                break;
            case R.id.btn_play:
                if(btn_play.isChecked()){
                    mMyBinder.playMusic();
                }else {
                    mMyBinder.pauseMusic();
                }
                break;
            default:
                break;
        }
    }



    private void initView() {
        MediaServiceIntent = new Intent(getActivity(), MediaService.class);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
        } else {
            //够了就设置路径等，准备播放
            getActivity().bindService(MediaServiceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
        }
       /* seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mMyBinder.seekToPositon(seekBar.getProgress());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });*/



        initToolbarNav(mToolBar);
        mToolBar.setNavigationIcon(R.drawable.nav_back);


        initDialog();
       /* tv_title.setText("推荐书单");
        tv_title.setTextColor(Color.parseColor("#333333"));
        tv_title.setTextSize(18);
        //tv_list_null.setText("暂无路演");
        mToolBar.setNavigationIcon(R.drawable.nav_back);
        loading_layout.setVisibility(View.GONE);*/

        /*adapter = new BookListenerPageAdapter(R.layout.listenerstory_chapter_list_item);

//        adapter.addHeaderView(bannerViewHeader);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listView.setAdapter(adapter);
        List<TestData> list = new ArrayList<>();
        list.add(new TestData());
        list.add(new TestData());
        adapter.setNewData(list);*/

    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyBinder = (MediaService.MyBinder) service;
//            mMediaService = ((MediaService.MyBinder) service).getInstance();
            seekBar.setMax(mMyBinder.getProgress());

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    //这里很重要，如果不判断是否来自用户操作进度条，会不断执行下面语句块里面的逻辑，然后就会卡顿卡顿
                    if(fromUser){
                        mMyBinder.seekToPositon(seekBar.getProgress());
//                    mMediaService.mMediaPlayer.seekTo(seekBar.getProgress());
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            mHandler.post(mRunnable);

            Log.d(TAG, "Service与Activity已连接");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        //我们的handler发送是定时1000s发送的，如果不关闭，MediaPlayer release掉了还在获取getCurrentPosition就会爆IllegalStateException错误
        mHandler.removeCallbacks(mRunnable);

        mMyBinder.closeMedia();
        getActivity().unbindService(mServiceConnection);
    }

    /**
     * 更新ui的runnable
     */
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            seekBar.setProgress(mMyBinder.getPlayPosition());
            begin_time.setText(time.format(mMyBinder.getPlayPosition()) + "s");
            mHandler.postDelayed(mRunnable, 1000);
        }
    };

    private void initMusic() {
        test();
        Field[] fields = R.raw.class.getDeclaredFields();
        int rawId;
        String rawName;
        for (int i = 0; i < fields.length; i++) {
            try {
                rawId = fields[i].getInt(R.raw.class);
                rawName = fields[i].getName();
//                Log.i(TAG, "-----------rawId="+rawId+"----------");
                Uri uri = Uri.parse("android.resource://" + getContext().getPackageName() + "/" + rawId);
                Log.d("tag", "music music "+uri);
                /*mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(getContext(), uri);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.prepare();
                int duration = mediaPlayer.getDuration();
                audioInfo = new AlarmClockAudioInfo(rawName, RingTextUtil.showAudioTime(duration), uri);*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void test(){
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        Uri uri = Uri.parse("android.resource://com.guomai.dushuhui/" + R.raw.test);
        Log.d("tag", "music music "+uri);
        Log.d("tag", "music music "+uri.getPath());
        //InputStream s = getResources().openRawResource(R.raw.test);

        //Cursor cursor = query(uri, null, null, null, null);
        //mmr.setDataSource(getActivity(), uri);
        mmr.setDataSource("/storage/emulated/0/test/test.mp3");
        /*mMediaPlayer=new MediaPlayer();
        try {
            mMediaPlayer.setDataSource("/storage/emulated/0/test/test.mp3");
            mMediaPlayer.prepare();
        }catch (Exception e){

        }
        mMediaPlayer.start();*/


/*
            Cursor cursor = getContext().getContentResolver()
                    .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
                            null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
//遍历媒体数据库
            if(cursor.moveToFirst()){

                while (!cursor.isAfterLast()) {
                    Song song = new Song();
                    //歌曲编号
                    song.setDisplayName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
                    song.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
                    song.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)));
                    song.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                    song.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
                    song.setSize(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));
                    songs.add(song);
                    cursor.moveToNext();
                }
            }

        player = Player.getInstance();*/
//        player.play(songs.get(0));




    }

   /* private Song cursorToMusic(Cursor cursor) {
        String realPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
        File songFile = new File(realPath);
        Song song;
        if (songFile.exists()) {
            // Using song parsed from file to avoid encoding problems
            song = FileUtils.fileToMusic(songFile);
            if (song != null) {
                return song;
            }
        }
        song = new Song();
        song.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
        String displayName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
        if (displayName.endsWith(".mp3")) {
            displayName = displayName.substring(0, displayName.length() - 4);
        }
        song.setDisplayName(displayName);
        song.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
        song.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)));
        song.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
        song.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
        song.setSize(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));
        return song;
    }*/

    private void initDialog() {
        dialogChapter = new DialogChapter(getActivity());
    }

    private void initListPopupIfNeed() {
        if (mListPopup == null) {

            String[] listItems = new String[]{
                    "15分钟",
                    "30分钟",
                    "60分钟",
                    "90分钟",
                    "确定",
            };
            List<String> data = new ArrayList<>();

            Collections.addAll(data, listItems);

            ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), R.layout.timeout_list_item, data);

            mListPopup = new QMUIListPopup(getContext(), QMUIPopup.DIRECTION_NONE, adapter);
            mListPopup.create(QMUIDisplayHelper.dp2px(getContext(), 151), QMUIDisplayHelper.dp2px(getContext(), 161), new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //Toast.makeText(getActivity(), "Item " + (i + 1), Toast.LENGTH_SHORT).show();
                    switch ((i+1)){
                        case 1:
                            Toast.makeText(getActivity(), "15分钟", Toast.LENGTH_SHORT).show();
                            text_time.setText("15:00");
                            break;
                        case 2:
                            Toast.makeText(getActivity(), "30分钟", Toast.LENGTH_SHORT).show();
                            text_time.setText("30:00");
                            break;
                        case 3:
                            Toast.makeText(getActivity(), "60分钟", Toast.LENGTH_SHORT).show();
                            text_time.setText("60:00");
                            break;
                        case 4:
                            Toast.makeText(getActivity(), "90分钟", Toast.LENGTH_SHORT).show();
                            text_time.setText("90:00");
                            break;
                        case 5:
                            Toast.makeText(getActivity(), "确定", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                    mListPopup.dismiss();
                }
            });
            mListPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    //mActionButton2.setText(getContext().getResources().getString(R.string.popup_list_action_button_text_show));
                }
            });
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        setOnClickListener();
    }

    private void setOnClickListener() {
       /* adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter a, View view, int position) {
                TestData testData = adapter.getItem(position);
                if(view.getId() == R.id.layout_shudan){
                    //Toast.makeText()
                    ShudanListPageFragment shudanListPageFragment = ShudanListPageFragment.newInstance();
                    start(shudanListPageFragment);
                }
            }
        });*/
    }




}
