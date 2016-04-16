package com.adriencadet.androidagainsthumanity.services.sockets;

import com.adriencadet.androidagainsthumanity.beans.Message;

import rx.Observable;

/**
 * ISocketService
 * <p>
 */
public interface ISocketService {
    Observable<String> createConversation();

    Observable<Message> joinConversation(String slug);

    Observable<Void> pushMessage(String slug, Message message);
}
