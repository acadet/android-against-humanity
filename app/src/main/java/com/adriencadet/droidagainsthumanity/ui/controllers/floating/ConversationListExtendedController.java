package com.adriencadet.droidagainsthumanity.ui.controllers.floating;

import com.adriencadet.droidagainsthumanity.R;
import com.adriencadet.droidagainsthumanity.ui.controllers.BaseController;
import com.adriencadet.droidagainsthumanity.ui.screens.modal.JoinConversationModalScreen;
import com.adriencadet.droidagainsthumanity.ui.screens.modal.NicknameModalScreen;

import butterknife.OnClick;

/**
 * ConversationListExtendedController
 * <p>
 */
public class ConversationListExtendedController extends BaseController {
    @Override
    protected int layoutId() {
        return R.layout.conversation_list_actions;
    }


    @OnClick(R.id.conversation_list_actions_create_join)
    public void onCreateOrJoin() {
        modalRouter.goTo(new JoinConversationModalScreen());
        mainRouter.goBack();
    }

    @OnClick(R.id.conversation_list_actions_nickname)
    public void onNickname() {
        modalRouter.goTo(new NicknameModalScreen());
        //TODO: replace by end or something similar
        mainRouter.goBack();
    }
}
