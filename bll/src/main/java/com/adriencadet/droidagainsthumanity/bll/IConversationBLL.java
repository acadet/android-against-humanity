package com.adriencadet.droidagainsthumanity.bll;

import com.adriencadet.droidagainsthumanity.beans.Conversation;
import com.adriencadet.droidagainsthumanity.beans.Message;

import java.util.List;

import rx.Observable;

/**
 * IConversationBLL
 * <p>
 */
public interface IConversationBLL {
    Observable<List<Conversation>> sortByDateDesc();

    Observable<Conversation> create();

    Observable<Message> join(String slug);

    Observable<Message> join(Conversation conversation);
}
