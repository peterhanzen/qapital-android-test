package com.pha.qapital;

import android.app.Application;

import com.facebook.stetho.Stetho;

import timber.log.Timber;

/**
 * Created by pha on 2017-12-02.
 */

public class QapitalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initTimber();
        initStetho();
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        Timber.v("Verbose");
        Timber.i("Info");
        Timber.d("Debug");
        Timber.w("Warn");
        Timber.e("Error");
        Timber.wtf("WTF");
    }

    private void initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }

}
