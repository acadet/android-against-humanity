package com.adriencadet.droidagainsthumanity.dao;

import com.adriencadet.droidagainsthumanity.beans.Conversation;
import com.adriencadet.droidagainsthumanity.beans.Message;

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
