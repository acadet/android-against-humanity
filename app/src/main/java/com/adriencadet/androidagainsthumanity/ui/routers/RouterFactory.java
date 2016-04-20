package com.adriencadet.androidagainsthumanity.ui.routers;

import com.lyft.scoop.ScreenScooper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * RouterFactory
 * <p>
 */
@Module
public class RouterFactory {
    @Provides
    @Named("main")
    @Singleton
    public IRouter provideMainRouter() {
        return new MainRouter(new ScreenScooper());
    }

    @Provides
    @Named("body")
    @Singleton
    public IRouter provideBodyRouter() {
        return new BodyRouter(new ScreenScooper());
    }

    @Provides
    @Named("floating")
    @Singleton
    public IRouter provideFloatingButtonRouter() {
        return new FloatingButtonRouter(new ScreenScooper());
    }
}
