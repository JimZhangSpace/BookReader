package com.guomai.dushuhui.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.drawablecolorchange.DrawableColorChange;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class UIUtils {
	public static Typeface customTypeFace;
//	public static Typeface ledTypeFace;
	
	public static int getScreenHeight(Context context) {

		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		
		return dm.heightPixels;
	}
	public static int getDensityDpi(Context context) {
		
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		
		return dm.densityDpi;
	}
	
	public static int getDpWidth(Context context)
	{
		
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		
		return (int) (dm.widthPixels/dm.density);
	}
	public static int getDpHeight(Context context)
	{
		
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		
		return (int) (dm.heightPixels/dm.density);
	}
	

	public static int getScreenWidth(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2dp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	// 隐藏键盘
	public static void HideKeyboard(View v) {
		InputMethodManager imm = (InputMethodManager) v.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 2);

		}
	}

	// 显示键盘
	public static void ShowKeyboard(View v) {
		InputMethodManager imm = (InputMethodManager) v.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);

//		imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
		imm.showSoftInput(v, 0);

	}

	// 定时显示/隐藏键盘
	public static void KeyBoard(final EditText txtSearchKey, final String status) {

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				InputMethodManager m = (InputMethodManager) txtSearchKey
						.getContext().getSystemService(
								Context.INPUT_METHOD_SERVICE);
				if (status.equals("open")) {
					m.showSoftInput(txtSearchKey,
							InputMethodManager.SHOW_FORCED);
				} else {
					m.hideSoftInputFromWindow(txtSearchKey.getWindowToken(), 0);
				}
			}
		}, 300);
	}

	// ͨ定时隐藏键盘
	public static void TimerHideKeyboard(final View v) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				InputMethodManager imm = (InputMethodManager) v.getContext()
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				if (imm.isActive()) {
					imm.hideSoftInputFromWindow(v.getApplicationWindowToken(),
							0);
				}
			}
		}, 10);
	}

	// 显示键盘
	public static boolean KeyBoard(EditText edittext) {
		boolean bool = false;
		InputMethodManager imm = (InputMethodManager) edittext.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			bool = true;
		}
		return bool;

	}

	
	/**
	 * 获取手机状态栏高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getStatusBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return statusBarHeight;
	}

	
	
	/**获取自定义格式文本*/
	public static SpannableString getMiddleSpannableString(Context context, String leftText, String markText, String rightText, int fontStyle)
	{
		
		String text = leftText+markText+rightText;
	    SpannableString localSpannableString = new SpannableString(text);
	    localSpannableString.setSpan(new TextAppearanceSpan(context, fontStyle), leftText.length(), leftText.length()+markText.length(), 34);
	    return localSpannableString;
	}
	/**获取自定义格式文本*/
	public static SpannableString getSideSpannableString(Context context, String leftMarkText, String midText, String rightMarkText, int fontStyle)
	{
		
		String text = leftMarkText+midText+rightMarkText;
		SpannableString localSpannableString = new SpannableString(text);
		localSpannableString.setSpan(new TextAppearanceSpan(context, fontStyle), 0, leftMarkText.length(), 34);
		localSpannableString.setSpan(new TextAppearanceSpan(context, fontStyle), leftMarkText.length()+midText.length(), leftMarkText.length()+midText.length()+rightMarkText.length(), 34);
		return localSpannableString;
	}
	/**获取自定义格式文本*/
	public static SpannableString getAppendSpannableString(Context context, String leftText, String markText, int fontStyle)
	{
		
		String text = leftText+markText;
		SpannableString localSpannableString = new SpannableString(text);
		localSpannableString.setSpan(new TextAppearanceSpan(context, fontStyle), leftText.length(), leftText.length()+markText.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
		return localSpannableString;
	}
	
	/**获取自定义格式文本*/
	public static SpannableString getNumberSpan(String str, int number, String patten, String color)
	{
		String text = String.format("%s%d%s",str,number,patten);
		SpannableString ss = new SpannableString(text);
		int minuteLen = String.valueOf(number).length();
		ss.setSpan(new ForegroundColorSpan(Color.parseColor(color)), str.length(), str.length()+minuteLen, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return ss;
	}
	
	/***
	 * 清除动画
	 * @param v
	 */
	public static void clearAnimator(View v) {
		if(v==null)
			return;
        ViewCompat.setAlpha(v, 1);
        ViewCompat.setScaleY(v, 1);
        ViewCompat.setScaleX(v, 1);
        ViewCompat.setTranslationY(v, 0);
        ViewCompat.setTranslationX(v, 0);
        ViewCompat.setRotation(v, 0);
        ViewCompat.setRotationY(v, 0);
        ViewCompat.setRotationX(v, 0);
        // @TODO https://code.google.com/p/android/issues/detail?id=80863
        // ViewCompat.setPivotY(v, v.getMeasuredHeight() / 2);
        v.setPivotY(v.getMeasuredHeight() / 2);
        ViewCompat.setPivotX(v, v.getMeasuredWidth() / 2);
        ViewCompat.animate(v).setInterpolator(null);
    }
	
	public static Typeface getCustomTypeface(Context context) {
		if (customTypeFace == null) {
			try {
				customTypeFace = Typeface.createFromAsset(context.getAssets(),
						"fonts/custom.ttf");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return customTypeFace;
	}
	
	// generated id
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * Generate a value suitable for use in {@link #(int)}.
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    public static int generateViewId() {
        if (Build.VERSION.SDK_INT >= 17) {
            return View.generateViewId();
        } else {
            for (; ; ) {
                final int result = sNextGeneratedId.get();
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
                if (sNextGeneratedId.compareAndSet(result, newValue)) {
                    return result;
                }
            }
        }
    }
    
    /**
	    * 加载本地图片
	    * @param url
	    * @return
	    */
	    public static Bitmap getLoacalBitmap(String url) {
	         try {
	              FileInputStream fis = new FileInputStream(url);
	              return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

	           } catch (FileNotFoundException e) {
	              e.printStackTrace();
	              return null;
	         }
	    }


		public static Drawable getTintImage(Context context, int res, int color)
		{
			DrawableColorChange colorChange = new DrawableColorChange(context);
			colorChange.setDrawable(res);
			colorChange.setColorResId(color);
//			iv_into_room.setImageDrawable(colorChange.getColorChangedDrawable());
			return colorChange.getColorChangedDrawable();
		}






}
