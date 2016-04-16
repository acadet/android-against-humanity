package com.adriencadet.androidagainsthumanity.bll;

import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.adriencadet.androidagainsthumanity.beans.Message;

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
