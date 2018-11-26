package com.guomai.dushuhui.mine;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.guomai.dushuhui.R;

/**
 * Created by Administrator on 2018/9/15.
 */

public class NewPhoneBindDialog extends Dialog {

    private Button submit;//确定按钮
    private Button getAuthCode;//取消按钮
    /*private TextView titleTv;//消息标题文本
    private TextView messageTv;//消息提示文本
    private String titleStr;//从外界设置的title文本
    private String messageStr;//从外界设置的消息文本*/
    //确定文本和取消文本的显示内容
//    private String yesStr, noStr;

    private getCodeOnclickListener getCodeOnclickListener;//取消按钮被点击了的监听器
    private submiteOnclickListener submiteOnclickListener;//确定按钮被点击了的监听器


    public void setGetAuthCodeOnclickListener(getCodeOnclickListener getCodeOnclickListener) {

        this.getCodeOnclickListener = getCodeOnclickListener;
    }


    public void setSubmitOnclickListener(submiteOnclickListener submitOnclickListener) {
        this.submiteOnclickListener = submitOnclickListener;
    }

    public NewPhoneBindDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bind_phone);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

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
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submiteOnclickListener != null) {
                    submiteOnclickListener.submitClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        getAuthCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getCodeOnclickListener != null) {
                    getCodeOnclickListener.getCodeClick();
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
        submit = findViewById(R.id.submit);
        getAuthCode = findViewById(R.id.get_auth_code);
    }


    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface getCodeOnclickListener {
        public void getCodeClick();
    }

    public interface submiteOnclickListener {
        public void submitClick();
    }
}
