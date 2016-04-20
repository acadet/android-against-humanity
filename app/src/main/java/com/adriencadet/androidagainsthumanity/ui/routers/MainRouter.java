package com.adriencadet.androidagainsthumanity.ui.routers;

import com.adriencadet.androidagainsthumanity.AndroidAgainstHumanityApplication;
import com.adriencadet.androidagainsthumanity.ui.screens.ConversationListScreen;
import com.adriencadet.androidagainsthumanity.ui.screens.JoinConversationScreen;
import com.adriencadet.androidagainsthumanity.ui.screens.floating.ConversationScreen;
import com.adriencadet.androidagainsthumanity.ui.screens.main.DetailsScreen;
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
        if (screen instanceof ConversationListScreen) {
            super.goTo(new InitScreen());
            bodyRouter.goTo(screen);
            floatingButtonRouter.goTo(new ConversationListScreen());
        } else if (screen instanceof JoinConversationScreen) {
            super.goTo(new DetailsScreen());
            bodyRouter.goTo(screen);
            floatingButtonRouter.goTo(new ConversationScreen());
        }
    }

    @Override
    public boolean goBack() {
        floatingButtonRouter.goBack();
        bodyRouter.goBack();
        return super.goBack();
    }
}
