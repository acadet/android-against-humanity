package com.adriencadet.androidagainsthumanity.ui.screens.floating;

import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.adriencadet.androidagainsthumanity.ui.controllers.floating.ConversationFABController;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;

/**
 * ConversationFABScreen
 * <p>
 */
@Controller(ConversationFABController.class)
public class ConversationFABScreen extends Screen {
    public Conversation conversation;

    public ConversationFABScreen(Conversation conversation) {
        this.conversation = conversation;
    }
}
