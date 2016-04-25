package com.adriencadet.droidagainsthumanity.ui.controllers.floating;

import com.adriencadet.droidagainsthumanity.R;
import com.adriencadet.droidagainsthumanity.ui.controllers.BaseController;
import com.adriencadet.droidagainsthumanity.ui.screens.floating.ConversationListExtendedScreen;

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
