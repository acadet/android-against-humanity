package com.adriencadet.droidagainsthumanity.dao;

import com.adriencadet.droidagainsthumanity.beans.Conversation;

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
