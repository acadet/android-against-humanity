package com.adriencadet.androidagainsthumanity.dao;

import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.adriencadet.androidagainsthumanity.beans.Message;

/**
 * IMessageDAO
 * <p>
 */
public interface IMessageDAO {
    void save(Conversation conversation, Message message);
}
