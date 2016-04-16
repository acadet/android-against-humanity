package com.adriencadet.androidagainsthumanity.services.sockets;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * CreateConversationJob
 * <p>
 */
class CreateConversationJob {
    private ISocketServerAPI api;

    CreateConversationJob(ISocketServerAPI api) {
        this.api = api;
    }

    Observable<String> create() {
        return Observable
            .create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    String slug = api.createSession();
                    subscriber.onNext(slug);
                    subscriber.onCompleted();
                }
            })
            .subscribeOn(Schedulers.newThread());
    }
}
