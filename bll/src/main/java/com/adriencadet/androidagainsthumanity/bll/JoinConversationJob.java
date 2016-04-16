package com.adriencadet.androidagainsthumanity.bll;

import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.adriencadet.androidagainsthumanity.beans.Message;
import com.adriencadet.androidagainsthumanity.dao.IConversationDAO;
import com.adriencadet.androidagainsthumanity.dao.IMessageDAO;
import com.adriencadet.androidagainsthumanity.services.sockets.ISocketService;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * JoinConversationJob
 * <p>
 */
class JoinConversationJob {

    private IConversationDAO conversationDAO;
    private IMessageDAO      messageDAO;
    private ISocketService   socketService;

    JoinConversationJob(IConversationDAO conversationDAO, IMessageDAO messageDAO, ISocketService socketService) {
        this.conversationDAO = conversationDAO;
        this.messageDAO = messageDAO;
        this.socketService = socketService;
    }

    Observable<Message> create(String slug) {
        return
            Observable
                .create(new Observable.OnSubscribe<Message>() {
                    @Override
                    public void call(Subscriber<? super Message> subscriber) {
                        Conversation conversation = conversationDAO.save(slug);

                        socketService
                            .joinConversation(slug)
                            .observeOn(Schedulers.newThread())
                            .subscribe(new Subscriber<Message>() {
                                @Override
                                public void onCompleted() {
                                    subscriber.onCompleted();
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(Message message) {
                                    subscriber.onNext(message);
                                    messageDAO.save(conversation, message);
                                }
                            });
                    }
                })
                .subscribeOn(Schedulers.newThread());
    }

    Observable<Message> create(Conversation conversation) {
        return create(conversation.getSlug());
    }
}
