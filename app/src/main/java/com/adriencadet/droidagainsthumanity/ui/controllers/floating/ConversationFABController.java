package com.adriencadet.droidagainsthumanity.ui.controllers.floating;

import com.adriencadet.droidagainsthumanity.R;
import com.adriencadet.droidagainsthumanity.ui.controllers.BaseController;
import com.adriencadet.droidagainsthumanity.ui.screens.floating.ConversationExtendedScreen;
import com.adriencadet.droidagainsthumanity.ui.screens.floating.ConversationFABScreen;
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
