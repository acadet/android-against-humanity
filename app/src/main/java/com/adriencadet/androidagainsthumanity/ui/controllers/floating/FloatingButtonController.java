package com.adriencadet.androidagainsthumanity.ui.controllers.floating;

import com.adriencadet.androidagainsthumanity.R;
import com.adriencadet.androidagainsthumanity.ui.controllers.BaseController;
import com.adriencadet.androidagainsthumanity.ui.screens.floating.ConversationExtendedScreen;
import com.adriencadet.androidagainsthumanity.ui.screens.floating.ConversationListExtendedScreen;
import com.adriencadet.androidagainsthumanity.ui.screens.floating.ConversationListScreen;
import com.lyft.scoop.Screen;

import butterknife.OnClick;

/**
 * FloatingButtonController
 * <p>
 */
public class FloatingButtonController extends BaseController {
    private boolean isOnList;

    @Override
    protected int layoutId() {
        return R.layout.floating_button;
    }

    @Override
    public void onAttach() {
        super.onAttach();

        Screen screen = Screen.fromController(this);
        isOnList = screen instanceof ConversationListScreen;
    }

    @OnClick(R.id.floating_action_button)
    public void onButtonClicked() {
        if (isOnList) {
            mainRouter.goTo(new ConversationListExtendedScreen());
        } else {
            mainRouter.goTo(new ConversationExtendedScreen());
        }
    }
}
