package com.guomai.dushuhui.util.imageloader;

import android.app.Activity;
import android.widget.ImageView;

import com.guomai.dushuhui.R;
import com.guomai.dushuhui.config.GlideApp;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class MyImageLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
//        Glide.with(activity).load(new File(path))
//
//                .into(imageView);
//        GlideApp.with(activity)
//                .load(uri)
//                .override(Target.SIZE_ORIGINAL)
//                .into(imageViewLookup);

        GlideApp.with(activity).load(new File(path))
                .override(width,height)
                .placeholder(R.drawable.default_image).into(imageView);

//        GlideApp.
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
//        Glide.with(activity).load(new File(path))
//                .placeholder(R.mipmap.default_image).into(imageView);
        GlideApp.with(activity).load(new File(path))
                .override(width,height)
                .placeholder(R.drawable.default_image).into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
