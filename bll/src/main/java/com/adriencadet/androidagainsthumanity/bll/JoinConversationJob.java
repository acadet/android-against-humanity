package com.adriencadet.androidagainsthumanity.bll;

import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.adriencadet.androidagainsthumanity.beans.Message;
import com.adriencadet.androidagainsthumanity.dao.IConversationDAO;
import com.adriencadet.androidagainsthumanity.dao.IMessageDAO;
import com.adriencadet.androidagainsthumanity.dao.IUserDAO;
import com.adriencadet.androidagainsthumanity.services.sockets.ISocketService;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * JoinConversationJob
 * <p>
 */
@Singleton
class JoinConversationJob {

    private class CustomSubscriber extends Subscriber<Message> {
        private Conversation                conversation;
        private Subscriber<? super Message> client;

        CustomSubscriber(Conversation conversation, Subscriber<? super Message> client) {
            this.conversation = conversation;
            this.client = client;
        }

        @Override
        public void onCompleted() {
            client.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            client.onError(e);
        }

        @Override
        public void onNext(Message message) {
            client.onNext(message);
            messageDAO.save(conversation, message);
        }

        void onMessagePosted(Message message) {
            client.onNext(message);
        }
    }

    private Map<String, CustomSubscriber> customSubscriberMap;

    private IConversationDAO conversationDAO;
    private IMessageDAO      messageDAO;
    private IUserDAO         userDAO;
    private ISocketService   socketService;
    private PostMessageJob   postMessageJob;

    JoinConversationJob(IConversationDAO conversationDAO, IMessageDAO messageDAO, IUserDAO userDAO, ISocketService socketService, PostMessageJob postMessageJob) {
        this.conversationDAO = conversationDAO;
        this.messageDAO = messageDAO;
        this.userDAO = userDAO;
        this.socketService = socketService;
        this.postMessageJob = postMessageJob;

        this.customSubscriberMap = new HashMap<>();
    }

    private void postMessage(String slug, Observable<Message> postMessageObservable) {
        CustomSubscriber customSubscriber;

        if (!customSubscriberMap.containsKey(slug)) {
            Timber.e("Missing subscriber");
            return;
        }

        customSubscriber = customSubscriberMap.get(slug);

        postMessageObservable
            .observeOn(Schedulers.newThread())
            .subscribe(new Subscriber<Message>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                    customSubscriber.onError(e);
                }

                @Override
                public void onNext(Message message) {
                    customSubscriber.onMessagePosted(message);
                }
            });
    }

    Observable<Message> create(String slug) {
        return Observable
            .create(new Observable.OnSubscribe<Message>() {
                @Override
                public void call(Subscriber<? super Message> subscriber) {
                    Conversation conversation = conversationDAO.findBySlug(slug);

                    if (conversation == null) {
                        conversationDAO.save(slug);
                    }

                    create(conversation)
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

    Observable<Message> create(Conversation conversation) {
        return
            Observable
                .create(new Observable.OnSubscribe<Message>() {
                    @Override
                    public void call(Subscriber<? super Message> subscriber) {
                        CustomSubscriber customSubscriber;

                        if (!userDAO.hasNickname()) {
                            subscriber.onError(new BLLErrors.NoNickname());
                            return;
                        }

                        customSubscriber = new CustomSubscriber(conversation, subscriber);
                        customSubscriberMap.put(conversation.getSlug(), customSubscriber);

                        socketService
                            .joinConversation(conversation.getSlug())
                            .observeOn(Schedulers.newThread())
                            .subscribe(customSubscriber);
                    }
                })
                .subscribeOn(Schedulers.newThread());
    }

    void postMessage(Conversation conversation, String content) {
        postMessage(conversation.getSlug(), postMessageJob.create(conversation, content));
    }

    void postMessage(String slug, String content) {
        postMessage(slug, postMessageJob.create(slug, content));
    }
}
