package com.adriencadet.androidagainsthumanity.ui.screens;

import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.lyft.scoop.Screen;

/**
 * JoinConversationScreen
 * <p>
 */
public class JoinConversationScreen extends Screen {
    public Conversation conversation;

    public JoinConversationScreen(Conversation conversation) {
        this.conversation = conversation;
    }
}