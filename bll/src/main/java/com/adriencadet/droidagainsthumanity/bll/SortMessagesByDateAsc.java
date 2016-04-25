package com.adriencadet.droidagainsthumanity.bll;

import com.adriencadet.droidagainsthumanity.beans.Conversation;
import com.adriencadet.droidagainsthumanity.beans.Message;
import com.adriencadet.droidagainsthumanity.dao.IConversationDAO;
import com.adriencadet.droidagainsthumanity.dao.IMessageDAO;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * SortMessagesByDateAsc
 * <p>
 */
class SortMessagesByDateAsc {
    private IConversationDAO conversationDAO;
    private IMessageDAO      messageDAO;

    SortMessagesByDateAsc(IConversationDAO conversationDAO, IMessageDAO messageDAO) {
        this.conversationDAO = conversationDAO;
        this.messageDAO = messageDAO;
    }

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

    Observable<List<Message>> create(String slug) {
        return Observable
            .create(new Observable.OnSubscribe<List<Message>>() {
                @Override
                public void call(Subscriber<? super List<Message>> subscriber) {
                    Conversation conversation = conversationDAO.findBySlug(slug);

                    if (conversation == null) {
                        conversation = conversationDAO.save(slug);
                    }

                    create(conversation)
                        .observeOn(Schedulers.newThread())
                        .subscribe(subscriber);
                }
            })
            .subscribeOn(Schedulers.newThread());
    }
}
