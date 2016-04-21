package com.adriencadet.androidagainsthumanity.dao;

import com.adriencadet.androidagainsthumanity.beans.Conversation;

import java.util.List;

/**
 * IConversationDAO
 * <p>
 */
public interface IConversationDAO {
    List<Conversation> sortByDateDesc();

    Conversation findBySlug(String slug);

    Conversation save(String slug);
}
