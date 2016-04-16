package com.adriencadet.androidagainsthumanity.bll;

import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.adriencadet.androidagainsthumanity.beans.Message;

import org.javatuples.Pair;

import java.util.List;

import rx.Observable;

/**
 * MessageBLL
 * <p>
 */
class MessageBLL implements IMessageBLL {
    private ListMessageSuggestionsJob listMessageSuggestionsJob;
    private SortMessagesByDateAsc     sortMessagesByDateAsc;
    private PostMessageJob            postMessageJob;

    MessageBLL(ListMessageSuggestionsJob listMessageSuggestionsJob, SortMessagesByDateAsc sortMessagesByDateAsc,
               PostMessageJob postMessageJob) {
        this.listMessageSuggestionsJob = listMessageSuggestionsJob;
        this.sortMessagesByDateAsc = sortMessagesByDateAsc;
        this.postMessageJob = postMessageJob;
    }

    @Override
    public Observable<Pair<List<String>, List<String>>> listSuggestions() {
        return listMessageSuggestionsJob.create();
    }

    @Override
    public Observable<List<Message>> sortByDateAsc(Conversation conversation) {
        return sortMessagesByDateAsc.create(conversation);
    }

    @Override
    public Observable<Void> post(Conversation conversation, String content) {
        return postMessageJob.create(conversation, content);
    }
}
