package com.adriencadet.androidagainsthumanity.services.sockets;

import rx.Observable;

/**
 * SocketService
 * <p>
 */
class SocketService implements ISocketService {
    private CreateConversationJob createConversationJob;
    private JoinConversationJob   joinConversationJob;
    private PushMessageJob        pushMessageJob;

    SocketService(CreateConversationJob createConversationJob, JoinConversationJob joinConversationJob,
                  PushMessageJob pushMessageJob) {
        this.createConversationJob = createConversationJob;
        this.joinConversationJob = joinConversationJob;
        this.pushMessageJob = pushMessageJob;
    }

    @Override
    public Observable<String> createConversation() {
        return createConversationJob.create();
    }

    @Override
    public Observable<String> joinConversation(String slug) {
        return joinConversationJob.create(slug);
    }

    @Override
    public Observable<Void> pushMessage(String slug, String message) {
        return pushMessageJob.create(slug, message);
    }
}
