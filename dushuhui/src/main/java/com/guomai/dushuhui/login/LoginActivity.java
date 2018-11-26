package com.guomai.dushuhui.login;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseToolBarActivity;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Administrator on 2018/9/13.
 */

public class LoginActivity extends BaseToolBarActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.name_register)
    TextView reginster;
    @BindView(R.id.edit_phone)
    EditText edit_phone;
    @BindView(R.id.edit_pwd)
    EditText edit_pwd;
    /*@BindView(R.id.agree)
    FancyButton agree;*/
    @BindView(R.id.agree)
    CheckBox agree;
    @BindView(R.id.login)
    FancyButton btn_login;
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
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        reginster.setText("注册");
        phone_check.setVisibility(View.GONE);
        interval_view.setVisibility(View.GONE);
        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/





    }

    @Override
    protected void processLogic() {

    }


    @OnClick({R.id.back,R.id.name_register,R.id.forgot_pwd,R.id.login})
    public void onClick(View v)
    {
        if(v.getId() == R.id.back){
            finish();
        }else if(v.getId() == R.id.name_register){
//            Toast.makeText(getApplication(), "dfsfa", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.forgot_pwd){
            Intent intent = new Intent(LoginActivity.this, SetMobileActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.login){
            final QMUITipDialog tipDialog = new QMUITipDialog.Builder(this)
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                    .setTipWord("登录成功")
                    .create();
            tipDialog.show();
            btn_login.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tipDialog.dismiss();
                }
            },1500);
//            Toast.makeText(getApplication(), "Login", Toast.LENGTH_SHORT).show();
        }
    }

}
