package com.guomai.dushuhui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guomai.dushuhui.R;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Administrator on 2018/9/13.
 */

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.name_register)
    TextView reginster;
    @BindView(R.id.edit_phone)
    EditText edit_phone;
    @BindView(R.id.edit_pwd)
    EditText edit_pwd;
    @BindView(R.id.agree)
    CheckBox agree;
    @BindView(R.id.login)
    FancyButton login;
    @BindView(R.id.forgot_pwd)
    TextView forgot_pwd;
    @BindView(R.id.edit_check)
    EditText edit_check;
    @BindView(R.id.get_check)
    FancyButton get_check;
    @BindView(R.id.phone_check)
    LinearLayout phone_check;
    @BindView(R.id.interval_below_autocode)
    View interval_view;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        reginster.setText("登录");
        phone_check.setVisibility(View.VISIBLE);
        login.setText("注册");
        forgot_pwd.setVisibility(View.INVISIBLE);
        interval_view.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.back,R.id.name_register,R.id.forgot_pwd,R.id.login})
    public void onClick(View v)
    {
        switch (v.getId()){
            case R.id.back:      //返回
                finish();
                break;
            case R.id.get_check:   //获取验证码

                break;
            case R.id.agree:       //记住密码

                break;
            case R.id.login:       //注册

                break;
            case R.id.name_register:  //右上角登陆
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

}

