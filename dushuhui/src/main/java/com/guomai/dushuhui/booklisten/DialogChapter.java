package com.guomai.dushuhui.booklisten;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.guomai.dushuhui.R;

/**
 * Created by Administrator on 2018/9/19.
 */

public class DialogChapter extends Dialog {

  /*  private Button yes;//确定按钮
    private Button no;//取消按钮
    private TextView titleTv;//消息标题文本
    private TextView messageTv;//消息提示文本
    private String titleStr;//从外界设置的title文本
    private String messageStr;//从外界设置的消息文本
    //确定文本和取消文本的显示内容
    private String yesStr, noStr;*/

    private onPlayOnclickListener playOnclickListener;//取消按钮被点击了的监听器
    //private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    Window dialogWindow;


    public void setPlayOnclickListener(onPlayOnclickListener onPlayOnclickListener) {

        this.playOnclickListener = onPlayOnclickListener;
    }

    public DialogChapter(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_chapter);

        setCanceledOnTouchOutside(true);       //按空白处隐藏dialog

        //初始化界面控件
    //    initView();
        //初始化界面数据
    //    initData();
        //初始化界面控件的事件
    //    initEvent();

        dialogWindow = getWindow();
        dialogWindow.setBackgroundDrawableResource(R.color.red);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.BOTTOM);
        lp.y = 10;
        dialogWindow.setAttributes(lp);

    }

    /**
     * 初始化界面的确定和取消监听器
     */
   /* private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
    }*/

    /**
     * 初始化界面控件的显示数据
     */
  /*  private void initData() {
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
   /* private void initView() {
        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);
        titleTv = (TextView) findViewById(R.id.title);
        messageTv = (TextView) findViewById(R.id.message);
    }
*/
  /*  *//**
     * 从外界Activity为Dialog设置标题
     *
     * @param title
     *//*
    public void setTitle(String title) {
        titleStr = title;
    }

    *//**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param message
     *//*
    public void setMessage(String message) {
        messageStr = message;
    }*/

    /**
     * 设置确定按钮和取消被点击的接口
     */
   /* public interface onYesOnclickListener {
        public void onYesClick();
    }*/

    public interface onPlayOnclickListener {
        public void onPlayClick();
    }
}
