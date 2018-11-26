package com.guomai.dushuhui.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.guomai.dushuhui.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Administrator on 2018/9/13.
 */

public class SetMobileActivity extends AppCompatActivity{
    /*@BindView(R.id.edit_phone)
    EditText edit_phone;
    @BindView(R.id.edit_password)
    EditText edit_password;
    @BindView(R.id.edit_authcode)
    FancyButton get_authcode;
    @BindView(R.id.edit_confirm_pwd)
    EditText edit_confirm_pwd;
    @BindView(R.id.reset)
    FancyButton resetPwd;*/



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);
        ButterKnife.bind(this);
    }

    /*@OnClick({R.id.edit_authcode,R.id.reset})
    public void onClick(View v){

    }*/

}
