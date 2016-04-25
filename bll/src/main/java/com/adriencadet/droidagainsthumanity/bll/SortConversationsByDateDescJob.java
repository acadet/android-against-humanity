package com.adriencadet.droidagainsthumanity.bll;

import com.adriencadet.droidagainsthumanity.beans.Conversation;
import com.adriencadet.droidagainsthumanity.dao.IConversationDAO;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * SortConversationsByDateDescJob
 * <p>
 */
class SortConversationsByDateDescJob {
    private IConversationDAO conversationDAO;

    SortConversationsByDateDescJob(IConversationDAO conversationDAO) {
        this.conversationDAO = conversationDAO;
    }

    Observable<List<Conversation>> create() {
        return Observable
            .create(new Observable.OnSubscribe<List<Conversation>>() {
                @Override
                public void call(Subscriber<? super List<Conversation>> subscriber) {
                    subscriber.onNext(conversationDAO.sortByDateDesc());
                    subscriber.onCompleted();
                }
            })
            .subscribeOn(Schedulers.newThread());
    }
}
