package com.amm.sample.helper;

import android.content.Context;
import android.graphics.*;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by amartinez on 18/03/2015.
 */
public class BitmapHelper {

    public static void init(final Context context) {


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(5)
                .threadPriority(Thread.MAX_PRIORITY)
                .denyCacheImageMultipleSizesInMemory()
                .imageDownloader(new BaseImageDownloader(context))
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();

        ImageLoader.getInstance().init(config);
    }


    public static void loadImagen(Context context, ImageView imageView, String url, ImageLoadingListener listener) {
        if (!ImageLoader.getInstance().isInited()) {
            init(context);
        }

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisc()
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .displayer(new FadeInBitmapDisplayer(500))
                .build();

        ImageLoader.getInstance().displayImage(url, imageView, options, listener);

    }


}
