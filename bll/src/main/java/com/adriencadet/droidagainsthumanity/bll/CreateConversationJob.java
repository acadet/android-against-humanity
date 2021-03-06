package com.adriencadet.droidagainsthumanity.bll;

import com.adriencadet.droidagainsthumanity.beans.Conversation;
import com.adriencadet.droidagainsthumanity.dao.IConversationDAO;
import com.adriencadet.droidagainsthumanity.dao.IUserDAO;
import com.adriencadet.droidagainsthumanity.services.sockets.ISocketService;

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
    private IUserDAO         userDAO;

    CreateConversationJob(ISocketService socketService, IConversationDAO conversationDAO, IUserDAO userDAO) {
        this.socketService = socketService;
        this.conversationDAO = conversationDAO;
        this.userDAO = userDAO;
    }

    Observable<Conversation> create() {
        return Observable
            .create(new Observable.OnSubscribe<Conversation>() {
                @Override
                public void call(Subscriber<? super Conversation> subscriber) {
                    if (!userDAO.hasNickname()) {
                        subscriber.onError(new BLLErrors.NoNickname());
                        return;
                    }

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
