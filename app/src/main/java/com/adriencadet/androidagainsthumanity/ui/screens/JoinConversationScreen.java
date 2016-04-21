package com.adriencadet.androidagainsthumanity.ui.screens;

import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.adriencadet.androidagainsthumanity.ui.controllers.body.ConversationController;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;

/**
 * JoinConversationScreen
 * <p>
 */
@Controller(ConversationController.class)
public class JoinConversationScreen extends Screen {
    public Conversation conversation;
    public String       slug;

    public JoinConversationScreen(Conversation conversation) {
        this.conversation = conversation;
    }

    public JoinConversationScreen(String slug) {
        this.slug = slug;
    }

    public boolean hasConversation() {
        return conversation != null;
    }
}
