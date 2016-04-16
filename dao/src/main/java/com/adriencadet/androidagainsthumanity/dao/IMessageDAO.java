package com.adriencadet.androidagainsthumanity.dao;

import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.adriencadet.androidagainsthumanity.beans.Message;

import java.util.List;

/**
 * IMessageDAO
 * <p>
 */
public interface IMessageDAO {
    List<Message> sortByDateAsc(Conversation conversation);

    Message saveOutgoing(Conversation conversation, String content);

    void save(Conversation conversation, Message message);
}
