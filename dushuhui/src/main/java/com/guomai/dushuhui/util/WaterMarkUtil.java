package com.guomai.dushuhui.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;


public class WaterMarkUtil {
//	private static int UNCONSTRAINED = 1280;
//
//	public static Bitmap createWaterMaskImage(Context gContext, Bitmap src, Bitmap watermark)
//    {
//
//        String tag = "createBitmap";
//        if (src == null)
//        {
//            return null;
//        }
//        int w = src.getWidth();
//        int h = src.getHeight();
//        int ww = watermark.getWidth();
//        int wh = watermark.getHeight();
//        // create the new blank bitmap
//        Bitmap newb = Bitmap.createBitmap(w, h, Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
//        Canvas cv = new Canvas(newb);
//        // draw src into
//        cv.drawBitmap(src, 0, 0, null);// 在 0，0坐标开始画入src
//        // draw watermark into
//        cv.drawBitmap(watermark, 20, 20, null);// 在src的右下角画入水印
//        // save all clip
//
//        cv.save();// 保存
//        // store
//        cv.restore();// 存储
//        return newb;
//    }
//
//
//	public static Bitmap drawTextToBitmap(Context gContext, Bitmap bmp,
//                                          String gText) {
//          Resources resources = gContext.getResources();
//          float scale = resources.getDisplayMetrics().density;
//          Bitmap bitmap = bmp;
//          int w = bitmap.getWidth();
//          int h = bitmap.getHeight();
//          Config bitmapConfig =
//              bitmap.getConfig();
//          // set default bitmap config if none
//          if(bitmapConfig == null) {
//            bitmapConfig = Config.ARGB_8888;
//          }
//          // resource bitmaps are imutable,
//          // so we need to convert it to mutable one
//          bitmap = bitmap.copy(bitmapConfig, true);
//          Canvas canvas = new Canvas(bitmap);
//          // new antialised Paint
//          Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//          // text color - #3D3D3D
//          paint.setColor(Color.WHITE);
//          paint.setTextSize((int) (30 * scale));
//          paint.setDither(true); //获取跟清晰的图像采样
//          paint.setFilterBitmap(true);//过滤一些
//          Rect bounds = new Rect();
//          paint.getTextBounds(gText, 0, gText.length(), bounds);
//          float x =  30*scale;
//          float y = h - 30*scale;
//          canvas.drawText(gText, x , y , paint);
//          return bitmap;
//        }
//
//	  /**
//	 * @Description: 添加水印
//	 * @param bitmap
//	 * @param title
//	 * @param context
//	 * @return
//	 * @return: Bitmap
//	 */
//	public static Bitmap watermarkBitmap(Bitmap bitmap, String title, Context context, Options options) {
//	    if (bitmap == null) {
//	        return null;
//	    }
//	    Bitmap watermark = BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo, options);
//	    int w = bitmap.getWidth();
//	    int h = bitmap.getHeight();
//	    // 需要处理图片太大造成的内存超过的问题,这里我的图片很小所以不写相应代码了
//	    Bitmap newb = Bitmap.createBitmap(w, h, Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
//	    Canvas cv = new Canvas(newb);
//	    cv.drawBitmap(bitmap, 0, 0, null);// 在 0，0坐标开始画入src
//	    // 加入图片
//	    if (watermark != null) {
//	        int ww = watermark.getWidth();
//	        int wh = watermark.getHeight();
//	        Rect src = new Rect();// 图片
//	        Rect dst = new Rect();// 屏幕位置及尺寸
//	        src.left = 0; // 0,0
//	        src.top = 0;
//	        src.right = w;// 是桌面图的宽度，
//	        src.bottom = h;// 是桌面图的高度
//	        // 下面的 dst 是表示 绘画这个图片的位置
//	        dst.left = 0; // 绘图的起点X位置
//	        dst.top = 0; // 相当于 桌面图片绘画起点的Y坐标
//	        dst.right = ww + w - 60; // 表示需绘画的图片的右上角
//	        dst.bottom = wh + h - 60; // 表示需绘画的图片的右下角
//	        cv.drawBitmap(watermark, src, dst, null);// 在src的右下角画入水印
//	        src = null;
//	        dst = null;
//	    }
//	    // 加入文字
//	    if (title != null) {
//	        String familyName = "宋体";
//	        Typeface font = Typeface.create(familyName, Typeface.BOLD);
//	        TextPaint textPaint = new TextPaint();
//	        textPaint.setColor(Color.RED);
//	        textPaint.setTypeface(font);
//	        textPaint.setTextSize(22);
//	        textPaint.setAlpha(50);
//	        cv.drawText(title, 40, h - 30, textPaint);
//	    }
//	    cv.save(Canvas.ALL_SAVE_FLAG);// 保存
//	    cv.restore();// 存储
//	    watermark.recycle();
//	    bitmap.recycle();
//	    return recycleBitmap(newb);
//	}
//
//	  public static Bitmap recycleBitmap(Bitmap bitmap) {
//		  if (bitmap == null || bitmap.getConfig() != null) {
//		      return bitmap;
//		  }
//		  Bitmap newBitmap = bitmap.copy(Config.ARGB_8888, false);
//		  bitmap.recycle();
//		  return newBitmap;
//	  }
//
//      /**
//     * @Description:
//     * @param options
//     * @param minSideLength
//     * @param maxNumOfPixels
//     * @return 动态计算出图片的inSampleSize
//     * @return: int
//     */
//	  public static int computeSampleSize(Options options, int minSideLength, int maxNumOfPixels) {
//        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
//        int roundedSize;
//        if (initialSize <= 8) {
//            roundedSize = 1;
//            while (roundedSize < initialSize) {
//                roundedSize <<= 1;
//            }
//        } else {
//            roundedSize = (initialSize + 7) / 8 * 8;
//        }
//        return roundedSize;
//    }
//
//	private static int computeInitialSampleSize(Options options, int minSideLength, int maxNumOfPixels) {
//        double w = options.outWidth;
//        double h = options.outHeight;
//        // Math.ceil(Math.sqrt(w * h / maxNumOfPixels)) ：w * h /
//        // maxNumOfPixels平方结果的小数部分一律向整数部分进位
//        int lowerBound = (maxNumOfPixels == UNCONSTRAINED ) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
//        // Math.floor(w / minSideLength) 将w / minSideLength结果值一律舍去小数，仅保留整数
//        int upperBound = (minSideLength == UNCONSTRAINED) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));
//        if (upperBound < lowerBound) {
//            return lowerBound;
//        }
//        if ((maxNumOfPixels == UNCONSTRAINED) && (minSideLength == UNCONSTRAINED)) {
//            return 1;
//        } else if (minSideLength == UNCONSTRAINED) {
//            return lowerBound;
//        } else {
//            return upperBound;
//        }
//    }
	
}
