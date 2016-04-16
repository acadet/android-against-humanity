package com.adriencadet.androidagainsthumanity.bll;

import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.adriencadet.androidagainsthumanity.beans.Message;
import com.adriencadet.androidagainsthumanity.dao.IMessageDAO;
import com.adriencadet.androidagainsthumanity.dao.IUserDAO;
import com.adriencadet.androidagainsthumanity.services.sockets.ISocketService;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * PostMessageJob
 * <p>
 */
class PostMessageJob {
    private ISocketService socketService;
    private IMessageDAO    messageDAO;
    private IUserDAO       userDAO;

    PostMessageJob(ISocketService socketService, IMessageDAO messageDAO, IUserDAO userDAO) {
        this.socketService = socketService;
        this.messageDAO = messageDAO;
        this.userDAO = userDAO;
    }

    Observable<Void> create(Conversation conversation, String content) {
        return Observable
            .create(new Observable.OnSubscribe<Void>() {
                @Override
                public void call(Subscriber<? super Void> subscriber) {
                    Message message = messageDAO.saveOutgoing(conversation, content);

                    message.setPosterNickname(userDAO.getNickname());

                    socketService
                        .pushMessage(conversation.getSlug(), message)
                        .observeOn(Schedulers.newThread())
                        .subscribe(new Subscriber<Void>() {
                            @Override
                            public void onCompleted() {
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Void aVoid) {

                            }
                        });
                }
            })
            .subscribeOn(Schedulers.newThread());
    }
}
