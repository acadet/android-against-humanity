package com.adriencadet.androidagainsthumanity.ui.containers;

import android.content.Context;
import android.util.AttributeSet;

import com.adriencadet.androidagainsthumanity.AndroidAgainstHumanityApplication;
import com.adriencadet.androidagainsthumanity.ui.routers.IRouter;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * MainContainer
 * <p>
 */
public class MainContainer extends BaseUiContainer {

    @Inject
    @Named("main")
    IRouter router;

    public MainContainer(Context context, AttributeSet attrs) {
        super(context, attrs);

        AndroidAgainstHumanityApplication.getApplicationComponent().inject(this);
        whenRouterAttached();
    }

    @Override
    protected IRouter getRouter() {
        return router;
    }
}
