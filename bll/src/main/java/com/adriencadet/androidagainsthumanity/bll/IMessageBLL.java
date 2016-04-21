package com.adriencadet.androidagainsthumanity.bll;

import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.adriencadet.androidagainsthumanity.beans.Message;

import org.javatuples.Pair;

import java.util.List;

import rx.Observable;

/**
 * IMessageBLL
 * <p>
 */
public interface IMessageBLL {
    Observable<Pair<List<String>, List<String>>> listSuggestions();

    Observable<List<Message>> sortByDateAsc(Conversation conversation);

    Observable<List<Message>> sortByDateAsc(String slug);

    Observable<Void> post(Conversation conversation, String prefix, String suffix);

    Observable<Void> post(String slug, String prefix, String suffix);
}
