package com.adriencadet.droidagainsthumanity.services.sockets;


import android.content.Context;
import android.content.Intent;

import com.adriencadet.droidagainsthumanity.beans.Message;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * JoinConversationJob
 * <p>
 */
@Singleton
class JoinConversationJob {
    private static final Object observableLock = new Object();

    private Map<String, Subject<Message, Message>> subjects;
    private Context                                context;
    private ISocketServerAPI                       api;
    private IMessageMapper                         messageMapper;

    JoinConversationJob(Context context, ISocketServerAPI api, IMessageMapper messageMapper) {
        this.context = context;
        this.api = api;
        this.messageMapper = messageMapper;
        this.subjects = new HashMap<>();

        SocketListenerService.subscribe(this);
    }

    Observable<Message> create(String slug) {
        if (!subjects.containsKey(slug)) {
            synchronized (observableLock) {
                if (!subjects.containsKey(slug)) {
                    Subject<Message, Message> subject = PublishSubject.create();

                    subjects.put(slug, subject);

                    Observable
                        .create(new Observable.OnSubscribe<Void>() {
                            @Override
                            public void call(Subscriber<? super Void> subscriber) {
                                Intent intent = new Intent(context, SocketListenerService.class);

                                SocketListenerService.hydrateIntent(intent, slug);

                                api.joinSession(slug);
                                context.startService(intent);
                            }
                        })
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(Schedulers.newThread())
                        .subscribe(new Subscriber<Void>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Void aVoid) {

                            }
                        });
                }
            }
        }

        return subjects.get(slug);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessage(MessageEvent event) {
        if (subjects.containsKey(event.slug)) {
            subjects.get(event.slug).onNext(messageMapper.map(event.data));
        }
    }
}
