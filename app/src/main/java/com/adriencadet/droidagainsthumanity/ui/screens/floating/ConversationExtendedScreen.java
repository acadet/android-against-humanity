package com.adriencadet.droidagainsthumanity.ui.screens.floating;

import com.adriencadet.droidagainsthumanity.beans.Conversation;
import com.adriencadet.droidagainsthumanity.ui.controllers.floating.ConversationExtendedController;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;

/**
 * ConversationExtendedScreen
 * <p>
 */
@Controller(ConversationExtendedController.class)
public class ConversationExtendedScreen extends Screen {
    public Conversation conversation;
    public String       slug;

    public ConversationExtendedScreen(Conversation conversation) {
        this.conversation = conversation;
    }

    public ConversationExtendedScreen(String slug) {
        this.slug = slug;
    }

    public boolean hasConversation() {
        return conversation != null;
    }
}
