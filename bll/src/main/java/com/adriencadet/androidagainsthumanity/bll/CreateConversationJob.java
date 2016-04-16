package com.adriencadet.androidagainsthumanity.bll;

import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.adriencadet.androidagainsthumanity.dao.IConversationDAO;
import com.adriencadet.androidagainsthumanity.services.sockets.ISocketService;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * CreateConversationJob
 * <p>
 */
class CreateConversationJob {
    private ISocketService   socketService;
    private IConversationDAO conversationDAO;

    Observable<Conversation> create() {
        return Observable
            .create(new Observable.OnSubscribe<Conversation>() {
                @Override
                public void call(Subscriber<? super Conversation> subscriber) {
                    socketService
                        .createConversation()
                        .observeOn(Schedulers.newThread())
                        .subscribe(new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(String s) {
                                subscriber.onNext(conversationDAO.save(s));
                            }
                        });
                }
            })
            .subscribeOn(Schedulers.newThread());
    }
}
