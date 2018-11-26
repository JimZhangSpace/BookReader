package com.guomai.dushuhui.mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.aries.ui.widget.BasisDialog;
import com.aries.ui.widget.action.sheet.UIActionSheetDialog;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.folioreader.ui.folio.activity.ContentHighlightActivity;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.base.BaseBackFragment;
import com.guomai.dushuhui.bookshop.CustomizePageFragment;
import com.guomai.dushuhui.bookshop.adapter.CustomizePageAdapter;
import com.guomai.dushuhui.bookshop.model.TestData;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/14.
 */

public class EditInfoFragment extends BaseBackFragment {
    public static EditInfoFragment newInstance() {

        Bundle args = new Bundle();

        EditInfoFragment fragment = new EditInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String TAG = "EditInfoFragment";

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.name)
    EditText edit_name;
    @BindView(R.id.sex)
    TextView edit_sex;
    @BindView(R.id.birthday)
    TextView edit_birthdy;
    @BindView(R.id.phone)
    TextView edit_phone;
    @BindView(R.id.password)
    TextView edit_password;
    @BindView(R.id.signature)
    EditText edit_signature;

    private View mRoot;

    TimePickerView timePickerView;
    Calendar currentDate;
    Calendar endDate;
    String startDate;

    NewPhoneBindDialog newPhoneBindDialog;
    NewPhoneBindDialog newPhoneBindDialog2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*mRoot = inflater.inflate(R.layout.frag_common_recycle,
                container, false);*/
        mRoot = inflater.inflate(R.layout.frag_edit_info_page,
                container, false);
//        mainActivity = (MyMainActivity) getActivity();
//        mainActivity.initHeadPanel(Constant.FRAGMENT_FLAG_COMMING_SOON);
        ButterKnife.bind(this,mRoot);

        initView();
        return mRoot;
    }

    @OnClick({R.id.back,R.id.name, R.id.sex, R.id.birthday, R.id.phone, R.id.password, R.id.signature})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back:
                pop();
                break;
            case R.id.name:

                break;
            case R.id.sex:
            {
                new UIActionSheetDialog.ListSheetBuilder(getActivity())
                        .addItems(R.array.sex_array)
//                        .setItemsTextColorResource(isDefaultItemColor ? R.color.colorActionSheetNormalItemText : android.R.color.holo_purple)
//                        .setTitle(isShowTitle ? "标题" : null)
                        .setCancel("取消")
//                        .setCancelMarginTop(SizeUtil.dp2px(isShowMargin ? 8 : 0))
//                        .setCancelTextColorResource(isDefaultCancelColor ? R.color.colorActionSheetNormalItemText : android.R.color.darker_gray)
                        .setOnItemClickListener(new UIActionSheetDialog.OnItemClickListener() {
                            @Override
                            public void onClick(BasisDialog dialog, View itemView, int position) {

                            }
                        })
                        .create().setDimAmount(0.6f).show();

            }
                break;
            case R.id.birthday:
                timePickerView.show();

                break;
            case R.id.phone:
                newPhoneBindDialog.show();
                break;
            case R.id.password:
                ChangePwdFragment changePwdFragment = ChangePwdFragment.newInstance();
                start(changePwdFragment);
                break;
            case R.id.signature:

                break;
            default:
                break;
        }
    }


    private void initView() {
        //new TimePickerView(this, new TimePickerView.O)
        currentDate = Calendar.getInstance();
        int year = currentDate.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);

        endDate = Calendar.getInstance();
        endDate.set(Calendar.YEAR,year+10);
        endDate.set(Calendar.MONTH,month);
        endDate.set(Calendar.DAY_OF_MONTH,day);

        Calendar startD = Calendar.getInstance();
        startD.set(1900, 1, 1);
        Calendar endD = Calendar.getInstance();
        endD.set(2018, 9, 15);

        timePickerView = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy.M.d");
                String dateStr = format.format(date);
                startDate = dateStr;
                edit_birthdy.setText(dateStr);
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                //.setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                //.setTitleText("选择时间")//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                //.setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.parseColor("#E44649"))//确定按钮文字颜色
                .setCancelColor(getResources().getColor(R.color.black))//取消按钮文字颜色
//				.setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//				.setBgColor(0xFF333333)//滚轮背景颜色 Night mode
                .setDate(Calendar.getInstance())// 如果不设置的话，默认是系统时间*/
                .setRangDate(startD,endD)//起始终止年月日设定
                .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
        timePickerView.setDate(currentDate);//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        initUIBottomSheet();
        initBindPhoneDialog();
    }

    private void initBindPhoneDialog(){
        newPhoneBindDialog = new NewPhoneBindDialog(getActivity());
        newPhoneBindDialog.setGetAuthCodeOnclickListener(new NewPhoneBindDialog.getCodeOnclickListener() {
            @Override
            public void getCodeClick() {       //获取验证码

            }
        });
        newPhoneBindDialog.setSubmitOnclickListener(new NewPhoneBindDialog.submiteOnclickListener() {
            @Override
            public void submitClick() {        //确定
                newPhoneBindDialog.dismiss();
            }
        });

        newPhoneBindDialog2 = new NewPhoneBindDialog(getActivity());
        newPhoneBindDialog2.setGetAuthCodeOnclickListener(new NewPhoneBindDialog.getCodeOnclickListener() {
            @Override
            public void getCodeClick() {       //获取验证码

            }
        });
        newPhoneBindDialog2.setSubmitOnclickListener(new NewPhoneBindDialog.submiteOnclickListener() {
            @Override
            public void submitClick() {        //下一步 弹出另外一个dialog
                newPhoneBindDialog.dismiss();
            }
        });


    }

    private void initUIBottomSheet(){
        /*UIActionSheetDialog dialog = new UIActionSheetDialog.ListSheetBuilder(this)
                .*/
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }

}
