package com.adriencadet.androidagainsthumanity.ui.routers;

import com.adriencadet.androidagainsthumanity.AndroidAgainstHumanityApplication;
import com.adriencadet.androidagainsthumanity.ui.screens.main.InitScreen;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ScreenScooper;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * MainRouter
 * <p>
 */
public class MainRouter extends BaseRouter {

    @Inject
    @Named("body")
    IRouter bodyRouter;

    @Inject
    @Named("floating")
    IRouter floatingButtonRouter;

    MainRouter(ScreenScooper screenScooper) {
        super(screenScooper);

        AndroidAgainstHumanityApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void goTo(Screen screen) {
        super.goTo(new InitScreen());
    }

    @Override
    public boolean goBack() {
        floatingButtonRouter.goBack();
        return bodyRouter.goBack() || super.goBack();
    }
}
