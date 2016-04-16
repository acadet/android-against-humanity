package com.adriencadet.androidagainsthumanity.services.sockets;

import rx.Observable;

/**
 * ISocketService
 * <p>
 */
public interface ISocketService {
    Observable<String> createConversation();

    Observable<String> joinConversation(String slug);

    Observable<Void> pushMessage(String slug, String message);
}
