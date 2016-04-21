package com.adriencadet.androidagainsthumanity.ui.controllers.floating;

import com.adriencadet.androidagainsthumanity.R;
import com.adriencadet.androidagainsthumanity.beans.Conversation;
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
    private Conversation conversation;

    @Override
    protected int layoutId() {
        return R.layout.floating_button;
    }

    @Override
    public void onAttach() {
        super.onAttach();

        ConversationFABScreen screen = Screen.fromController(this);
        conversation = screen.conversation;
    }

    @OnClick(R.id.floating_action_button)
    public void onButtonClicked() {
        mainRouter.goTo(new ConversationExtendedScreen(conversation));
    }
}
