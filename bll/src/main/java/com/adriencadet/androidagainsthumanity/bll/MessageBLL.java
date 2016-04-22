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
    private JoinConversationJob       joinConversationJob;

    MessageBLL(ListMessageSuggestionsJob listMessageSuggestionsJob, SortMessagesByDateAsc sortMessagesByDateAsc, JoinConversationJob joinConversationJob) {
        this.listMessageSuggestionsJob = listMessageSuggestionsJob;
        this.sortMessagesByDateAsc = sortMessagesByDateAsc;
        this.joinConversationJob = joinConversationJob;
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
    public Observable<List<Message>> sortByDateAsc(String slug) {
        return sortMessagesByDateAsc.create(slug);
    }

    @Override
    public void post(Conversation conversation, String prefix, String suffix) {
        joinConversationJob.postMessage(conversation, prefix + " " + suffix);
    }

    @Override
    public void post(String slug, String prefix, String suffix) {
        joinConversationJob.postMessage(slug, prefix + " " + suffix);
    }
}
