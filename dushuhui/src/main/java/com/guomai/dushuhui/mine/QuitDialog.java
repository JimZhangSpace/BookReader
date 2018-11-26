package com.guomai.dushuhui.mine;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.guomai.dushuhui.R;

/**
 * Created by Administrator on 2018/9/15.
 */

public class QuitDialog extends Dialog {

    private TextView confitm_quit;//确定按钮
    private TextView cancel_quit;//取消按钮
    /*private TextView titleTv;//消息标题文本
    private TextView messageTv;//消息提示文本
    private String titleStr;//从外界设置的title文本
    private String messageStr;//从外界设置的消息文本*/
    //确定文本和取消文本的显示内容
//    private String yesStr, noStr;
    Window window = null;

    private QuitDialog.quitCancelOnclickListener quitCancelOnclickListener;//取消按钮被点击了的监听器
    private QuitDialog.quitConfirmOnclickListener quitConfirmOnclickListener;//确定按钮被点击了的监听器


    public void setQuitCancelOnclickListener(QuitDialog.quitCancelOnclickListener quitCancelOnclickListener) {

        this.quitCancelOnclickListener = quitCancelOnclickListener;
    }


    public void setQuitConfirmOnclickListener(QuitDialog.quitConfirmOnclickListener quitConfirmOnclickListener) {
        this.quitConfirmOnclickListener = quitConfirmOnclickListener;
    }

    public QuitDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout_dialog_page);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);


        window = getWindow(); //得到对话框
        //window.setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画
        //window.setBackgroundDrawableResource(R.color.vifrification); //设置对话框背景为透明
        WindowManager.LayoutParams wl = window.getAttributes();
        //根据x，y坐标设置窗口需要显示的位置
        //wl.x = x; //x小于0左移，大于0右移
        wl.y = 150; //y小于0上移，大于0下移
        //            wl.alpha = 0.6f; //设置透明度
        //            wl.gravity = Gravity.BOTTOM; //设置重力
                   window.setAttributes(wl);


        //初始化界面控件
        initView();
        //初始化界面数据
        //initData();
        //初始化界面控件的事件
        initEvent();

    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        confitm_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quitConfirmOnclickListener != null) {
                    quitConfirmOnclickListener.quitConfirmClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        cancel_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quitCancelOnclickListener != null) {
                    quitCancelOnclickListener.quitCancelClick();
                }
            }
        });
    }

    /**
     * 初始化界面控件的显示数据
     */
   /* private void initData() {
        //如果用户自定了title和message
        if (titleStr != null) {
            titleTv.setText(titleStr);
        }
        if (messageStr != null) {
            messageTv.setText(messageStr);
        }
        //如果设置按钮的文字
        if (yesStr != null) {
            yes.setText(yesStr);
        }
        if (noStr != null) {
            no.setText(noStr);
        }
    }*/

    /**
     * 初始化界面控件
     */
    private void initView() {
        confitm_quit = findViewById(R.id.confirm_quit);
        cancel_quit = findViewById(R.id.cancel_quit);
    }


    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface quitCancelOnclickListener {
        public void quitCancelClick();
    }

    public interface quitConfirmOnclickListener {
        public void quitConfirmClick();
    }
}
