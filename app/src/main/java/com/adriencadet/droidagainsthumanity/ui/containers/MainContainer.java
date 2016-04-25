package com.adriencadet.droidagainsthumanity.ui.containers;

import android.content.Context;
import android.util.AttributeSet;

import com.adriencadet.droidagainsthumanity.AndroidAgainstHumanityApplication;
import com.adriencadet.droidagainsthumanity.ui.routers.IRouter;

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
