package com.guomai.dushuhui.booklisten;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.booklisten.adapter.BookListenerPageAdapter;
import com.guomai.dushuhui.bookshop.model.TestData;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.Screen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2018/9/19.
 */

public class ChapterActivityDialog extends Activity {
    public static final double SMALL_WIN_H_SCALE = 0.6;
    public static final double SMALL_WIN_W_SCALE = 0.95;


    @BindView(R.id.text_chapters_num)
    TextView textChapterNum;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private BookListenerPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_chapter);
        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP, ActionBar.DISPLAY_HOME_AS_UP);
        actionBar.setHomeAsUpIndicator(R.drawable.close_button);*/

        ButterKnife.bind(this);
        initViews();
        setOnClickListener();
    }

    private void setOnClickListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(ChapterActivityDialog.this, "第"+(position+1)+"章" ,Toast.LENGTH_SHORT).show();
                //Toast.makeText(this, " ", Toast.LENGTH_SHORT)

                FloatWindow.get().show();
//                FloatWindow.get().show();
            }
        });
    }

    private void initViews() {
        adapter = new BookListenerPageAdapter(R.layout.listenerstory_chapter_list_item);

//        adapter.addHeaderView(bannerViewHeader);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        List<TestData> list = new ArrayList<>();
        list.add(new TestData());
        list.add(new TestData());
        list.add(new TestData());
        list.add(new TestData());
        list.add(new TestData());
        list.add(new TestData());
        list.add(new TestData());
        list.add(new TestData());
        adapter.setNewData(list);


        /*adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FloatWindow
                        .with(getApplicationContext())
                        .setView(R.layout.layout_desktop)
                        //.setWidth(100)                               //设置控件宽高
                        //.setHeight(Screen.width,0.2f)
                        //.setHeight()
                        .setX(50)                                   //设置控件初始位置
                        .setY(Screen.height,0.8f)
                        .setDesktopShow(true)                        //桌面显示
                        //.setViewStateListener(mViewStateListener)    //监听悬浮控件状态改变
                        //.setPermissionListener(mPermissionListener)  //监听权限申请结果
                        .build();
                FloatWindow.get().show();
            }
        });*/
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        //overridePendingTransition(R.anim.activity_dialog_close_enter, R.anim.activity_dialog_close_exit);
        resizeActivity();
    }

    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition(R.anim.activity_dialog_close_enter, R.anim.activity_dialog_close_exit);
    }

   /* @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
*/
    private void resizeActivity(){
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        setFinishOnTouchOutside(true);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        layoutParams.copyFrom(getWindow().getAttributes());
        layoutParams.gravity = Gravity.BOTTOM;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        layoutParams.y = 20;
        layoutParams.width = (int) (displayMetrics.widthPixels*SMALL_WIN_W_SCALE);
        layoutParams.height = (int) (displayMetrics.heightPixels*SMALL_WIN_H_SCALE);
        //p.width = (int) (Constants.SCREEN_WIDTH * Constants.WIDTH_WEIGHT);
       // p.height = (int) (Constants.SCREEN_HEIGHT * Constants.HEIGHT_WEIGHT);
        layoutParams.dimAmount = 0.7f;
        layoutParams.alpha = 1.0f;
        getWindow().setAttributes(layoutParams);

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View view = getWindow().getDecorView();
        if(view != null) {
            view.setBackgroundResource(R.drawable.bg_common);
        }
    }
}
