package com.adriencadet.androidagainsthumanity.ui.containers;

import android.content.Context;
import android.util.AttributeSet;

import com.adriencadet.androidagainsthumanity.ui.routers.IRouter;
import com.lyft.scoop.UiContainer;

/**
 * BaseUiContainer
 * <p>
 */
abstract class BaseUiContainer extends UiContainer {
    public BaseUiContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected abstract IRouter getRouter();

    protected void whenRouterAttached() {
        getRouter().observe(this::goTo);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getRouter().stopObserving(this::goTo);
    }
}
