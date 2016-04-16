package com.adriencadet.androidagainsthumanity.services.sockets;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * PushMessageJob
 * <p>
 */
class PushMessageJob {

    Observable<Void> create(String slug, String message) {
        return Observable
            .create(new Observable.OnSubscribe<Void>() {
                @Override
                public void call(Subscriber<? super Void> subscriber) {
                    SocketListenerService.pushMessage(new MessageEvent(slug, message));
                    subscriber.onCompleted();
                }
            })
            .subscribeOn(Schedulers.newThread());
    }
}
