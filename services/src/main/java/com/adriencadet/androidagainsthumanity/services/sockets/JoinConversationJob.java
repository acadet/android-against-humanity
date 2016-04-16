package com.adriencadet.androidagainsthumanity.services.sockets;


import android.content.Context;
import android.content.Intent;

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

    private Map<String, Subject<String, String>> subject;
    private Context                              context;
    private ISocketServerAPI                     api;

    JoinConversationJob(Context context, ISocketServerAPI api) {
        this.context = context;
        this.api = api;
        this.subject = new HashMap<>();

        SocketListenerService.subscribe(this);
    }

    Observable<String> create(String slug) {
        if (!subject.containsKey(slug)) {
            synchronized (observableLock) {
                if (!subject.containsKey(slug)) {
                    Subject<String, String> s = PublishSubject.create();
                    subject.put(slug, s);

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

        return subject.get(slug);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessage(MessageEvent event) {
        if (subject.containsKey(event.slug)) {
            subject.get(event.slug).onNext(event.message);
        }
    }
}
