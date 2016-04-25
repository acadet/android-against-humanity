package com.adriencadet.androidagainsthumanity;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * ApplicationModule
 * <p>
 */
@Module
public class ApplicationModule {
    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

    @Provides
    @Named("smallNotificationResourceId")
    public int provideSmallNotificationResourceId() {
        return R.drawable.ic_small_notification;
    }

    @Provides
    @Named("largeNotificationResourceId")
    public int provideNotificationResourceId() {
        return R.drawable.ic_large_notification;
    }
}
