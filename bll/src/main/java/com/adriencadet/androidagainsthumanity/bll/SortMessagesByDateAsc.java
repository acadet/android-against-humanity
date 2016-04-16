package com.adriencadet.androidagainsthumanity.bll;

import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.adriencadet.androidagainsthumanity.beans.Message;
import com.adriencadet.androidagainsthumanity.dao.IMessageDAO;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * SortMessagesByDateAsc
 * <p>
 */
class SortMessagesByDateAsc {
    private IMessageDAO messageDAO;

    Observable<List<Message>> create(Conversation conversation) {
        return Observable
            .create(new Observable.OnSubscribe<List<Message>>() {
                @Override
                public void call(Subscriber<? super List<Message>> subscriber) {
                    subscriber.onNext(messageDAO.sortByDateAsc(conversation));
                    subscriber.onCompleted();
                }
            })
            .subscribeOn(Schedulers.newThread());
    }
}
