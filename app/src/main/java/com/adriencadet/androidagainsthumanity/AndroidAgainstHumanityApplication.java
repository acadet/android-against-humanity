package com.adriencadet.androidagainsthumanity;

import android.app.Application;

import com.adriencadet.androidagainsthumanity.ui.utils.ButterKnifeViewBinder;
import com.lyft.scoop.Scoop;

import timber.log.Timber;

/**
 * AndroidAgainstHumanityApplication
 * <p>
 */
public class AndroidAgainstHumanityApplication extends Application {
    private static AndroidAgainstHumanityApplication instance;
    private        ApplicationComponent              applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        Scoop.setViewBinder(new ButterKnifeViewBinder());

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(new ApplicationModule(this))
            .build();

        Timber.plant(new Timber.DebugTree());
    }

    public static ApplicationComponent getApplicationComponent() {
        return instance.applicationComponent;
    }
}
