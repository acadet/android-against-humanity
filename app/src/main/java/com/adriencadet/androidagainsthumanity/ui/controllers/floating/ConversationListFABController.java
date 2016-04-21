package com.adriencadet.androidagainsthumanity.ui.controllers.floating;

import com.adriencadet.androidagainsthumanity.R;
import com.adriencadet.androidagainsthumanity.ui.controllers.BaseController;
import com.adriencadet.androidagainsthumanity.ui.screens.floating.ConversationListExtendedScreen;

import butterknife.OnClick;

/**
 * ConversationListFABController
 * <p>
 */
public class ConversationListFABController extends BaseController {
    @Override
    protected int layoutId() {
        return R.layout.floating_button;
    }

    @OnClick(R.id.floating_action_button)
    public void onButtonClicked() {
        mainRouter.goTo(new ConversationListExtendedScreen());
    }
}
