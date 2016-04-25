package com.adriencadet.droidagainsthumanity.bll;

import com.adriencadet.droidagainsthumanity.beans.Conversation;
import com.adriencadet.droidagainsthumanity.beans.Message;

import java.util.List;

import rx.Observable;

/**
 * ConversationBLL
 * <p>
 */
class ConversationBLL implements IConversationBLL {
    private SortConversationsByDateDescJob sortConversationsByDateDescJob;
    private CreateConversationJob          createConversationJob;
    private JoinConversationJob            joinConversationJob;

    ConversationBLL(SortConversationsByDateDescJob sortConversationsByDateDescJob, CreateConversationJob createConversationJob,
                    JoinConversationJob joinConversationJob) {
        this.sortConversationsByDateDescJob = sortConversationsByDateDescJob;
        this.createConversationJob = createConversationJob;
        this.joinConversationJob = joinConversationJob;
    }


    @Override
    public Observable<List<Conversation>> sortByDateDesc() {
        return sortConversationsByDateDescJob.create();
    }

    @Override
    public Observable<Conversation> create() {
        return createConversationJob.create();
    }

    @Override
    public Observable<Message> join(String slug) {
        return joinConversationJob.create(slug);
    }

    @Override
    public Observable<Message> join(Conversation conversation) {
        return joinConversationJob.create(conversation);
    }
}
