package com.folioreader.ui.folio.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.folioreader.R;
import com.folioreader.model.HighlightImpl;
import com.folioreader.model.event.BookNoteChangeEvent;
import com.folioreader.model.sqlite.HighLightTable;
import com.folioreader.util.SoftKeyBoardListener;
import com.noober.background.BackgroundLibrary;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import org.greenrobot.eventbus.EventBus;

public class EditNoteActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_submit;
    EditText edit_content;
    TextView tv_text;

    HighlightImpl highlight;
    View layout_button;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        BackgroundLibrary.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reader_edit_note);
        highlight = getIntent().getParcelableExtra("note");


        btn_submit = findViewById(R.id.btn_submit);
        tv_text = findViewById(R.id.tv_text);
        edit_content = findViewById(R.id.edit_content);
        layout_button = findViewById(R.id.layout_button);
        findViewById(R.id.btn_close).setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        SoftKeyBoardListener.setListener(this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
//                Toast.makeText(getApplicationContext(),"键盘显示:"+height,Toast.LENGTH_SHORT).show();
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layout_button.getLayoutParams();
                params.bottomMargin = height;
                layout_button.setLayoutParams(params);
            }

            @Override
            public void keyBoardHide(int height) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layout_button.getLayoutParams();
                params.bottomMargin = 0;
                layout_button.setLayoutParams(params);
//                Toast.makeText(getApplicationContext(),"键盘隐藏:"+height,Toast.LENGTH_SHORT).show();
            }
        });

        edit_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btn_submit.setEnabled(charSequence.length()>0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }



    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_close)
        {
            finish();
        }
        else if(view.getId()==R.id.btn_submit)
        {
            String note = edit_content.getText().toString().trim();
            if(TextUtils.isEmpty(note))
            {
                Toast.makeText(getApplicationContext(),"请输入笔记",Toast.LENGTH_SHORT).show();
                return;
            }
            highlight.setNote(note);
            HighLightTable.updateHighlight(highlight);
            final QMUITipDialog tipDialog = new QMUITipDialog.Builder(this)
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                    .setTipWord("笔记保存成功")
                    .create();
            tipDialog.show();
            layout_button.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tipDialog.dismiss();
                    EventBus.getDefault().post(new BookNoteChangeEvent());
                    finish();
                }
            },1500);
//            Toast.makeText(getApplicationContext(),"笔记保存成功",Toast.LENGTH_SHORT).show();

        }
    }
}
