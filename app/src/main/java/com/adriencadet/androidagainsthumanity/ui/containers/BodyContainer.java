package com.adriencadet.androidagainsthumanity.ui.containers;

import android.content.Context;
import android.util.AttributeSet;

import com.adriencadet.androidagainsthumanity.AndroidAgainstHumanityApplication;
import com.adriencadet.androidagainsthumanity.ui.routers.IRouter;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * BodyContainer
 * <p>
 */
public class BodyContainer extends BaseUiContainer {
    @Inject
    @Named("body")
    IRouter router;

    public BodyContainer(Context context, AttributeSet attrs) {
        super(context, attrs);

        AndroidAgainstHumanityApplication.getApplicationComponent().inject(this);
        whenRouterAttached();
    }

    @Override
    protected IRouter getRouter() {
        return router;
    }
}
