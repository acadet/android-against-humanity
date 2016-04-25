package com.adriencadet.droidagainsthumanity.bll;

import com.adriencadet.droidagainsthumanity.beans.Conversation;
import com.adriencadet.droidagainsthumanity.beans.Message;
import com.adriencadet.droidagainsthumanity.dao.IConversationDAO;
import com.adriencadet.droidagainsthumanity.dao.IMessageDAO;
import com.adriencadet.droidagainsthumanity.dao.IUserDAO;
import com.adriencadet.droidagainsthumanity.services.sockets.ISocketService;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * PostMessageJob
 * <p>
 */
class PostMessageJob {
    private ISocketService   socketService;
    private IConversationDAO conversationDAO;
    private IMessageDAO      messageDAO;
    private IUserDAO         userDAO;

    PostMessageJob(ISocketService socketService, IConversationDAO conversationDAO, IMessageDAO messageDAO, IUserDAO userDAO) {
        this.socketService = socketService;
        this.conversationDAO = conversationDAO;
        this.messageDAO = messageDAO;
        this.userDAO = userDAO;
    }

    Observable<Message> create(Conversation conversation, String content) {
        return Observable
            .create(new Observable.OnSubscribe<Message>() {
                @Override
                public void call(Subscriber<? super Message> subscriber) {
                    Message message = messageDAO.saveOutgoing(conversation, content);

                    message.setPosterNickname(userDAO.getNickname());

                    socketService
                        .pushMessage(conversation.getSlug(), message)
                        .observeOn(Schedulers.newThread())
                        .subscribe(new Subscriber<Void>() {
                            @Override
                            public void onCompleted() {
                                subscriber.onNext(message);
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

    Observable<Message> create(String slug, String content) {
        return Observable
            .create(new Observable.OnSubscribe<Message>() {
                @Override
                public void call(Subscriber<? super Message> subscriber) {
                    Conversation conversation = conversationDAO.findBySlug(slug);

                    if (conversation == null) {
                        conversation = conversationDAO.save(slug);
                    }

                    create(conversation, content)
                        .observeOn(Schedulers.newThread())
                        .subscribe(new Subscriber<Message>() {
                            @Override
                            public void onCompleted() {
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onError(Throwable e) {
                                subscriber.onError(e);
                            }

                            @Override
                            public void onNext(Message message) {
                                subscriber.onNext(message);
                            }
                        });
                }
            })
            .subscribeOn(Schedulers.newThread());
    }
}
