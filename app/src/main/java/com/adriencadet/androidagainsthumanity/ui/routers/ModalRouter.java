package com.adriencadet.androidagainsthumanity.ui.routers;

import com.adriencadet.androidagainsthumanity.ui.screens.modal.ConfirmScreen;
import com.adriencadet.androidagainsthumanity.ui.screens.modal.InitModalScreen;
import com.adriencadet.androidagainsthumanity.ui.screens.modal.NicknameModalScreen;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ScreenScooper;

/**
 * ModalRouter
 * <p>
 */
public class ModalRouter extends BaseRouter {
    private Screen current;

    ModalRouter(ScreenScooper screenScooper) {
        super(screenScooper);
    }

    @Override
    public void goTo(Screen screen) {
        super.goTo(screen);

        current = screen;
    }

    @Override
    public boolean hasActiveScreen() {
        if (current instanceof InitModalScreen || current instanceof ConfirmScreen
            || current instanceof NicknameModalScreen) {
            return false;
        }

        return true;
    }

    @Override
    public void resetTo(Screen screen) {
        super.resetTo(screen);
        current = screen;
    }
}
