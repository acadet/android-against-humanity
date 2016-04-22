package com.adriencadet.androidagainsthumanity.ui.routers;

import com.adriencadet.androidagainsthumanity.AndroidAgainstHumanityApplication;
import com.adriencadet.androidagainsthumanity.ui.screens.ConversationListScreen;
import com.adriencadet.androidagainsthumanity.ui.screens.JoinConversationScreen;
import com.adriencadet.androidagainsthumanity.ui.screens.floating.ConversationExtendedScreen;
import com.adriencadet.androidagainsthumanity.ui.screens.floating.ConversationFABScreen;
import com.adriencadet.androidagainsthumanity.ui.screens.floating.ConversationListExtendedScreen;
import com.adriencadet.androidagainsthumanity.ui.screens.floating.ConversationListFABScreen;
import com.adriencadet.androidagainsthumanity.ui.screens.main.InitMainScreen;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ScreenScooper;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * MainRouter
 * <p>
 */
public class MainRouter extends BaseRouter {

    private boolean isFABActive;

    @Inject
    @Named("body")
    IRouter bodyRouter;

    @Inject
    @Named("floating")
    IRouter floatingButtonRouter;

    MainRouter(ScreenScooper screenScooper) {
        super(screenScooper);

        AndroidAgainstHumanityApplication.getApplicationComponent().inject(this);
        isFABActive = false;
    }

    @Override
    public void goTo(Screen screen) {
        if (screen instanceof ConversationListScreen) {
            super.goTo(new InitMainScreen());
            bodyRouter.goTo(screen);
            floatingButtonRouter.goTo(new ConversationListFABScreen());
        } else if (screen instanceof JoinConversationScreen) {
            JoinConversationScreen s1 = (JoinConversationScreen) screen;
            ConversationFABScreen s2;

            if (s1.hasConversation()) {
                s2 = new ConversationFABScreen(s1.conversation);
            } else {
                s2 = new ConversationFABScreen(s1.slug);
            }

            bodyRouter.goTo(screen);
            floatingButtonRouter.goTo(s2);

        } else if (screen instanceof ConversationListExtendedScreen || screen instanceof ConversationExtendedScreen) {
            isFABActive = true;
            floatingButtonRouter.goTo(screen);
        }
    }

    @Override
    public boolean goBack() {
        if (isFABActive) {
            isFABActive = false;
            floatingButtonRouter.goBack();
            return hasActiveScreen();
        }

        floatingButtonRouter.goBack();
        if (!bodyRouter.goBack()) {
            return super.goBack();
        }

        return true;
    }
}
