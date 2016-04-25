package com.adriencadet.droidagainsthumanity.ui.screens.floating;

import com.adriencadet.droidagainsthumanity.beans.Conversation;
import com.adriencadet.droidagainsthumanity.ui.controllers.floating.ConversationFABController;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;

/**
 * ConversationFABScreen
 * <p>
 */
@Controller(ConversationFABController.class)
public class ConversationFABScreen extends Screen {
    public Conversation conversation;
    public String       slug;

    public ConversationFABScreen(Conversation conversation) {
        this.conversation = conversation;
    }

    public ConversationFABScreen(String slug) {
        this.slug = slug;
    }

    public boolean hasConversation() {
        return conversation != null;
    }
}
