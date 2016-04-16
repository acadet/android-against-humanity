package com.adriencadet.androidagainsthumanity.bll;

import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.adriencadet.androidagainsthumanity.beans.Message;

import java.util.List;

import rx.Observable;

/**
 * MessageBLL
 * <p>
 */
class MessageBLL implements IMessageBLL {
    private SortMessagesByDateAsc sortMessagesByDateAsc;
    private PostMessageJob postMessageJob;

    @Override
    public Observable<List<Message>> sortByDateAsc(Conversation conversation) {
        return sortMessagesByDateAsc.create(conversation);
    }

    @Override
    public Observable<Void> post(Conversation conversation, String content) {
        return postMessageJob.create(conversation, content);
    }
}
