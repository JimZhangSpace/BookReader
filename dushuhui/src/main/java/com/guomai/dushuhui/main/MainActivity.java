package com.guomai.dushuhui.main;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;


import com.guomai.dushuhui.MyApplication;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseActivity;
import com.guomai.dushuhui.entity.MyMessage;
import com.guomai.dushuhui.model.UserDetail;
import com.guomai.dushuhui.util.LogoutHelper;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.Screen;


import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import okhttp3.Call;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class MainActivity extends SupportActivity {
    private static final String TAG = "MainActivity";
    private static final String EXTRA_APP_QUIT = "APP_QUIT";
    //	private UserDetail mSelfUserInfo;
//    private MyCustomMsg currentMsg;
    public MainFragment mainFragment;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyApplication.getInst().addActvity(this);
        MyApplication.getInst().mainAct = this;
        if (findFragment(MainFragment.class) == null) {
            mainFragment = MainFragment.newInstance();
            loadRootFragment(R.id.fl_container, mainFragment);
        }



        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_LOGS, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }

      /*  View view = View.inflate(this,R.layout.layout_desktop,null);
        view.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatWindow.get().hide();
            }
        });
        FloatWindow
                .with(getApplicationContext())
                .setView(view)
                //.setWidth(100)                               //设置控件宽高
                //.setHeight(Screen.width,0.2f)
                //.setHeight()
                .setX(50)                                   //设置控件初始位置
                .setY(Screen.height,0.8f)
                .setDesktopShow(true)                        //桌面显示
                //.setViewStateListener(mViewStateListener)    //监听悬浮控件状态改变
                //.setPermissionListener(mPermissionListener)  //监听权限申请结果
                .build();*/
//        FloatWindow.get().hide();

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
//        FloatWindow.get().hide();*/

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {


    }






    @Override
    public void onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }
//    private Subscriber<RefreshMessageEvent> sysMessageSubscriber = new Subscriber<RefreshMessageEvent>() {
//        @Override
//        public void onEvent(RefreshMessageEvent event) {
//            resetBadge();
//        }
//    };
//    private Subscriber<LoginStatusChangeEvent> LoginStatusChangeEventSubscriber = new Subscriber<LoginStatusChangeEvent>() {
//        @Override
//        public void onEvent(LoginStatusChangeEvent event) {
//            if(MyApplication.getInst().hasLogin) {
//                setupInComingMessage();
//            }
//        }
//    };





    @Override
    protected void onDestroy() {
        super.onDestroy();

        MyApplication.getInst().removeActvity(this);

        FloatWindow.destroy();

    }





    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

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

    // 注销
    public static void logout(Context context, boolean quit) {
        Intent extra = new Intent();
        extra.putExtra(EXTRA_APP_QUIT, quit);
        start(context, extra);
    }
    // 注销
    private void onLogout() {
        // 清理缓存&注销监听
//		MyApplication.getInst().logOut();
        LogoutHelper.logout();
        // 启动登录
//		if(!MyApplication.getInst().hasLogin())
//		{
//			startActivity(new Intent(getApplicationContext(), com.gelonghui.luyan.login.LoginActivity.class));
//		}

    }





    /*public void resetBadge()
    {
        UserDetail userDetail = MyApplication.getInst().user;
        int unreadCount = DataSupport
                .where("mineId = ? and state = 0", userDetail.id+"")
                .count(MyMessage.class);
        if(mainFragment!=null)
        {
//            mainFragment.resetBadge(unreadCount);
//            if(mainFragment.mineCenterFragment!=null)
//            {
//                mainFragment.mineCenterFragment.resetUnRead();
//            }
        }
    }*/





}

