package com.guomai.dushuhui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.guomai.dushuhui.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/9/14.
 */

public class ChangePwdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_change_pwd_page);
        ButterKnife.bind(this);
    }
}
