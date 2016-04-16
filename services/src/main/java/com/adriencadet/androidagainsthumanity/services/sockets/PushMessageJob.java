package com.adriencadet.androidagainsthumanity.services.sockets;

import com.adriencadet.androidagainsthumanity.beans.Message;

import org.json.JSONObject;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * PushMessageJob
 * <p>
 */
class PushMessageJob {
    private IMessageMapper messageMapper;

    PushMessageJob(IMessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    Observable<Void> create(String slug, Message message) {
        return Observable
            .create(new Observable.OnSubscribe<Void>() {
                @Override
                public void call(Subscriber<? super Void> subscriber) {
                    JSONObject data = messageMapper.map(message);

                    SocketListenerService.pushMessage(new MessageEvent(slug, data));
                    subscriber.onCompleted();
                }
            })
            .subscribeOn(Schedulers.newThread());
    }
}
