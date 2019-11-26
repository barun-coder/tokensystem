
package com.displayfort.dftoken.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;


public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    public static void setImageUrl(ImageView imageView, String url, int mipmap) {
        if (url == null || url.length() <= 0 || url.equalsIgnoreCase("null")) {
            imageView.setImageResource(mipmap);
        } else {
            Context context = imageView.getContext();
            Glide.with(context)
                    .load(url)
                    .into(imageView);
        }
    }

    public static void setSVGImageUrl(ImageView imageView, String url, int mipmap) {
        Context context = imageView.getContext();

    }

    @BindingAdapter("id")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

//
//    @BindingAdapter("id")
//    public static void setImageFile(ImageView imageView, File url) {
//        Context context = imageView.getContext();
//        Glide.with(context).load(url).into(imageView);
//    }
//
//
//    @BindingAdapter("id")
//    public static void setImageUrlR(ImageView imageView, String url) {
//        if (url != null && url.length() > 0) {
//            Context context = imageView.getContext();
//            setImageUrl(imageView, url);
//        }
//    }
//
//
//    @BindingAdapter("id")
//    public static void setImageFileR(ImageView imageView, File url) {
//        Context context = imageView.getContext();
//        Glide.with(context).load(url).into(imageView);
//    }
}
