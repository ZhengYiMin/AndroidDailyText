package com.chs.androiddailytext;

import android.app.Application;
import android.content.Context;

import com.chs.androiddailytext.changeSkin.CustomSDCardLoader;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import androidx.appcompat.app.AppCompatDelegate;

import me.weishu.reflection.Reflection;
import okhttp3.OkHttpClient;
import skin.support.SkinCompatManager;

/**
 * 作者：chs on 2018-02-24 14:04
 * 邮箱：657083984@qq.com
 */

public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinCompatManager.withoutActivity(this)
                .addStrategy(new CustomSDCardLoader()).
        loadSkin();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Reflection.unseal(base);
    }
}
