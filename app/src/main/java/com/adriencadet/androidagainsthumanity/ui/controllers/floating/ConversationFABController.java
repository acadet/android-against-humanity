package com.adriencadet.androidagainsthumanity.ui.controllers.floating;

import com.adriencadet.androidagainsthumanity.R;
import com.adriencadet.androidagainsthumanity.ui.controllers.BaseController;
import com.adriencadet.androidagainsthumanity.ui.screens.floating.ConversationExtendedScreen;
import com.adriencadet.androidagainsthumanity.ui.screens.floating.ConversationFABScreen;
import com.lyft.scoop.Screen;

import butterknife.OnClick;

/**
 * ConversationFABController
 * <p>
 */
public class ConversationFABController extends BaseController {

    @Override
    protected int layoutId() {
        return R.layout.floating_button;
    }

    @OnClick(R.id.floating_action_button)
    public void onButtonClicked() {
        ConversationExtendedScreen s;
        ConversationFABScreen original = Screen.fromController(this);

        if (original.hasConversation()) {
            s = new ConversationExtendedScreen(original.conversation);
        } else {
            s = new ConversationExtendedScreen(original.slug);
        }

        mainRouter.goTo(s);
    }
}
