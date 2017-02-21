package com.versatilemobitech.fmc.activities;


import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by Manikanta on 11/28/2016.
 */

public class FMCAppilication extends MultiDexApplication {


    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static FMCAppilication mInstance;

    /*private static final String TWITTER_KEY = "R7gbTtqUyCMuBVAFQjbS4ULm1";
    private static final String TWITTER_SECRET = "KZr46WSaPBEtbRPWxU0Hr69QqtNAZZZVojL6OudxshGcZGZDwI";
*/
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        /*TwitterAuthConfig authConfig = new TwitterAuthConfig(Utility.getResourcesString(this, R.string.twitter_consumer_key),
                Utility.getResourcesString(this, R.string.twitter_consumer_secret));*/

        /*FACEBOOK*/
        initImageLoader(getApplicationContext());
    }
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }
    public static synchronized FMCAppilication getInstance() {
        return mInstance;
    }
}

