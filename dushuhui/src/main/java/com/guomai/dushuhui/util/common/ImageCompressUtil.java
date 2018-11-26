package com.guomai.dushuhui.util.common;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * 图片压缩工具类
 *
 * @author 丶Life_
 */
public class ImageCompressUtil
{

    public static class Size
    {

        public Size (int width, int height)
        {
            this.width = width;
            this.height = height;
        }

        public int width;
        public int height;
    }

    public static Bitmap getBitmapByPath (String path)
    {


        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 设置为ture只获取图片大小
        opts.inJustDecodeBounds = false;
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        // 返回为空
        return BitmapFactory.decodeFile(path, opts);
    }

    /**
     * 通过降低图片的质量来压缩图片(百分比)
     *
     * @param bitmap         要压缩的图片
     * @param qualityPercent 压缩后图片大小的最大值,单位KB
     * @return 压缩后的图片
     */
    public static Bitmap compressByQualityPercent (Bitmap bitmap, int qualityPercent)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, qualityPercent, baos);
        bitmap.recycle();
        bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0,
                baos.toByteArray().length);
        return bitmap;
    }

    public static Size getBitmapSize (String filePath)
    {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, opts);

        return new Size(opts.outWidth, opts.outHeight);
    }

    /**
     * 通过降低图片的质量来压缩图片
     *
     * @param maxSize 要压缩的图片
     *                压缩后图片大小的最大值,单位KB
     * @return 压缩后的图片
     */
    public static byte[] compressByQuality (Bitmap bitmap, final boolean needRecycle, final long maxSize)
    {
        byte[] result = null;
        int quality = 60;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while (true)
        {
            try
            {
                baos.reset();
                bitmap.compress(CompressFormat.JPEG, quality, baos);
                result = baos.toByteArray();
            }
            catch (Throwable e)
            {
                quality -= 10;
                if (quality <= 0)
                {
                    break;
                }
                continue;
            }
            quality -= 10;
            if (result.length < maxSize || quality <= 0)
            {
                break;
            }
        }

        ResCloser.close(baos);

        if (needRecycle && bitmap != null && !bitmap.isRecycled())
        {
            bitmap.recycle();
        }

        return result;
    }
  

    /**
     * 通过压缩图片的尺寸来压缩图片大小
     *
     * @param pathName     图片的完整路径
     * @param targetWidth  缩放的目标宽度
     * @param targetHeight 缩放的目标高度
     * @return 缩放后的图片
     */
    public static Bitmap compressBySize (String pathName, int targetWidth, int targetHeight)
    {
        Size size = getBitmapSize(pathName);

        return compressBySize(pathName, size, targetWidth, targetHeight);
    }

    public static Bitmap compressBySize (String pathName, Size size, float targetWidth, float targetHeight)
    {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 分别计算图片宽度、高度与目标宽度、高度的比例；取大于等于该比例的最小整数；
        int widthRatio = (int) Math.ceil(size.width / targetWidth);
        int heightRatio = (int) Math.ceil(size.height / targetHeight);
        if (widthRatio > 1 || heightRatio > 1)
        {
            if (widthRatio > heightRatio)
            {
                opts.inSampleSize = widthRatio;
            }
            else
            {
                opts.inSampleSize = heightRatio;
            }
        }

        return BitmapFactory.decodeFile(pathName, opts);
    }

    /**
     * 通过压缩图片的尺寸来压缩图片大小
     *
     * @param bitmap       要压缩图片
     * @param targetWidth  缩放的目标宽度
     * @param targetHeight 缩放的目标高度
     * @return 缩放后的图片
     */
    public static Bitmap compressBySize (Bitmap bitmap, int targetWidth, int targetHeight)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, baos);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.toByteArray().length, opts);
        // 得到图片的宽度、高度；
        int imgWidth = opts.outWidth;
        int imgHeight = opts.outHeight;
        // 分别计算图片宽度、高度与目标宽度、高度的比例；取大于该比例的最小整数；
        int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
        int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
        if (widthRatio > 1 && heightRatio > 1)
        {
            if (widthRatio > heightRatio)
            {
                opts.inSampleSize = widthRatio;
            }
            else
            {
                opts.inSampleSize = heightRatio;
            }
        }
        // 设置好缩放比例后，加载图片进内存；
        opts.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.toByteArray().length, opts);
        return bitmap;
    }

    public static Bitmap compressImageFromFile (String srcPath, int width, int height)
    {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;//只读边,不读内容
        BitmapFactory.decodeFile(srcPath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        int be = 1;
        if (w > h && w > width)
        {
            be = newOpts.outWidth / width;
        }
        else if (w < h && h > height)
        {
            be = newOpts.outHeight / height;
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;
        newOpts.inPurgeable = true;
        newOpts.inInputShareable = true;

        return BitmapFactory.decodeFile(srcPath, newOpts);
    }

    /**
     * 通过压缩图片的尺寸来压缩图片大小，通过读入流的方式，可以有效防止网络图片数据流形成位图对象时内存过大的问题；
     *
     * @param is           要压缩图片，以流的形式传入
     * @param targetWidth  缩放的目标宽度
     * @param targetHeight 缩放的目标高度
     * @return 缩放后的图片
     * @throws java.io.IOException 读输入流的时候发生异常
     */
    public static Bitmap compressBySize (InputStream is, int targetWidth,
                                         int targetHeight) throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len;
        while ((len = is.read(buff)) != -1)
        {
            baos.write(buff, 0, len);
        }

        byte[] data = baos.toByteArray();
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, opts);
        // 得到图片的宽度、高度；
        int imgWidth = opts.outWidth;
        int imgHeight = opts.outHeight;
        // 分别计算图片宽度、高度与目标宽度、高度的比例；取大于该比例的最小整数；
        int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
        int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
        if (widthRatio > 1 && heightRatio > 1)
        {
            if (widthRatio > heightRatio)
            {
                opts.inSampleSize = widthRatio;
            }
            else
            {
                opts.inSampleSize = heightRatio;
            }
        }
        // 设置好缩放比例后，加载图片进内存；
        opts.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data, 0, data.length, opts);
    }

    public static String getCompressedImagePath(String filePath, int IMAGE_SIZE_LIMIT, int MAX_WIDTH, int MAX_HEIGHT)
    {
    	File file = new File(filePath);
        long length = file.length();
        if (length < IMAGE_SIZE_LIMIT ) {
            return filePath;
        }

        ImageCompressUtil.Size size = ImageCompressUtil.getBitmapSize(filePath);
        Bitmap bitmap = null;
        try {
            bitmap = ImageCompressUtil.compressBySize(filePath, size, MAX_WIDTH, MAX_HEIGHT);
            if (bitmap != null) {
                byte[] bytes = ImageCompressUtil.compressByQuality(bitmap, true, IMAGE_SIZE_LIMIT);
                String tempFilePath = DirectoryUtil.cacheDirectory + "/" + System.currentTimeMillis() + ".jpg";
                ImageCompressUtil.writeBitmapToFile(bytes, tempFilePath);
                filePath = tempFilePath;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
           
        }
		return filePath;
    }

    public static void writeBitmapToFile (byte[] bytes, String filePath)
    {
        if (bytes == null)
        {
            return;
        }

        OutputStream fos = null;
        try
        {
            fos = new FileOutputStream(filePath);

            fos.write(bytes);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ResCloser.close(fos);
        }
    }

    public static void writeBitmapToFile (Bitmap bitmap, String filePath)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();
        writeBitmapToFile(bytes, filePath);
    }

    
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.PNG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}
		
		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
    public static Bitmap drawableToBitmap(Drawable drawable) {



        Bitmap bitmap = Bitmap.createBitmap(

                drawable.getIntrinsicWidth(),

                drawable.getIntrinsicHeight(),

                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

                        : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        //canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;

    }


}
