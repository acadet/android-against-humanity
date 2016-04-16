package com.adriencadet.androidagainsthumanity.dao;

import com.adriencadet.androidagainsthumanity.beans.Conversation;

import java.util.List;

/**
 * IConversationDAO
 * <p>
 */
public interface IConversationDAO {
    List<Conversation> sortByDateDesc();

    Conversation save(String slug);
}
