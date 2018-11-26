package com.guomai.dushuhui.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.guomai.dushuhui.MyApplication;
import com.guomai.dushuhui.R;
import com.guomai.dushuhui.util.IntentKey;

public abstract class BaseActivity extends AppCompatActivity {
	
	protected final String TAG = getClass().getSimpleName();
	/** ContentView */
	protected int screenWidth;
	protected int screenHeight;
	protected MaterialDialog progressDialog;
	private boolean isDestroyed = false;
	
//	public TextView btn_back;// 返回按钮
//	public TextView btn_next;// 下一步按钮
//	public TextView tv_titlebar_title;// 标题栏
//	public ImageButton btn_img_next;// 右侧图片
//	private OnClickListener buttonClick = new OnClickListener() {
//
//		@Override
//		public void onClick(View v) {
//			switch (v.getId()) {
//			case R.id.btn_back:
//				onHeadLeftButton(v);
//				break;
//			case R.id.btn_next:
//			case R.id.btn_img_next:
//				onHeadRightButton(v);
//				break;
//			default:
//				break;
//			}
//		}
//	};
	/**
	 * 
	 */
	@SuppressLint("WrongViewCast")
	@Override
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setFlag();
		setContentView(getLayout());

//		tv_titlebar_title = (TextView) findViewById(R.id.tv_titlebar_title);
//		btn_back = (TextView)findViewById(R.id.btn_back);
//		btn_next = (TextView)findViewById(R.id.btn_next);
//		btn_img_next = (ImageButton)findViewById(R.id.btn_img_next);
//		if(btn_back!=null)
//		{
//			btn_back.setOnClickListener(buttonClick);
//		}
//		if(btn_next!=null)
//			btn_next.setOnClickListener(buttonClick);
//		if(btn_img_next!=null)
//			btn_img_next.setOnClickListener(buttonClick);
		progressDialog = new MaterialDialog.Builder(this)
				.title("玩命加载中")
				.content("请稍后")
				.progress(true, 0)
				.build();

		isDestroyed = false;
		MyApplication.getInst().addActvity(this);
		screenWidth = getResources().getDisplayMetrics().widthPixels;
		screenHeight = getResources().getDisplayMetrics().heightPixels;
		initView();
		processLogic();
		initHeadTitle();
	}

	
	
	@Override
	public void startActivity(Intent intent) {
		intent.putExtra(IntentKey.KEY_FROM_ACTIVITY, getClass().getSimpleName());
		super.startActivity(intent);
	}
	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		intent.putExtra(IntentKey.KEY_FROM_ACTIVITY, getClass().getSimpleName());
		super.startActivityForResult(intent, requestCode);
	}



	/**
	 * 加载view
	 */
	protected abstract int getLayout();
	protected abstract void initView();
	/**
	 * 执行业务方法
	 */
	protected abstract void processLogic();
	
	protected void setFlag(){};

	/**
	 * 初始化标题栏
	 */
	protected  void initHeadTitle(){}




	@Override
	protected void onResume() {
		super.onResume();
//		StatService.onResume(this);
	}
		
	@Override
	protected void onPause() {
		super.onPause();
//		StatService.onPause(this);
	}
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(progressDialog!=null&&progressDialog.isShowing())
			progressDialog.dismiss();
		
		isDestroyed = true;
		MyApplication.getInst().removeActvity(this);
	}

	

	

	
	 /**
     * 判断页面是否已经被销毁（异步回调时使用）
     */
    protected boolean isDestroyedCompatible() {
        if (Build.VERSION.SDK_INT >= 17) {
            return isDestroyedCompatible17();
        } else {
            return isDestroyed || super.isFinishing();
        }
    }
    
    @TargetApi(17)
    private boolean isDestroyedCompatible17() {
        return super.isDestroyed();
    }

}
