package com.adriencadet.androidagainsthumanity.bll;

import android.content.Context;

import com.adriencadet.androidagainsthumanity.dao.IConversationDAO;
import com.adriencadet.androidagainsthumanity.dao.IMessageDAO;
import com.adriencadet.androidagainsthumanity.dao.IUserDAO;
import com.adriencadet.androidagainsthumanity.services.sockets.ISocketService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * BLLFactory
 * <p>
 */
@Module
public class BLLFactory {
    @Provides
    @Singleton
    CreateConversationJob provideCreateConversationJob(ISocketService socketService, IConversationDAO conversationDAO, IUserDAO userDAO) {
        return new CreateConversationJob(socketService, conversationDAO, userDAO);
    }

    @Provides
    @Singleton
    GetUserNicknameJob provideGetUserNicknameJob(IUserDAO userDAO) {
        return new GetUserNicknameJob(userDAO);
    }

    @Provides
    @Singleton
    JoinConversationJob provideJoinConversationJob(IConversationDAO conversationDAO, IMessageDAO messageDAO, IUserDAO userDAO, ISocketService socketService) {
        return new JoinConversationJob(conversationDAO, messageDAO, userDAO, socketService);
    }

    @Provides
    @Singleton
    ListMessageSuggestionsJob provideListMessageSuggestionsJob(Context context) {
        return new ListMessageSuggestionsJob(context);
    }

    @Provides
    @Singleton
    PostMessageJob providePostMessageJob(ISocketService socketService, IConversationDAO conversationDAO, IMessageDAO messageDAO, IUserDAO userDAO) {
        return new PostMessageJob(socketService, conversationDAO, messageDAO, userDAO);
    }

    @Provides
    @Singleton
    SaveUserNicknameJob provideSaveUserNicknameJob(IUserDAO userDAO) {
        return new SaveUserNicknameJob(userDAO);
    }

    @Provides
    @Singleton
    SortConversationsByDateDescJob provideSortConversationsByDateDescJob(IConversationDAO conversationDAO) {
        return new SortConversationsByDateDescJob(conversationDAO);
    }

    @Provides
    @Singleton
    SortMessagesByDateAsc provideSortMessagesByDateAsc(IConversationDAO conversationDAO, IMessageDAO messageDAO) {
        return new SortMessagesByDateAsc(conversationDAO, messageDAO);
    }

    @Provides
    @Singleton
    HasUserNicknameJob provideHasUserNicknameJob(IUserDAO userDAO) {
        return new HasUserNicknameJob(userDAO);
    }

    @Provides
    @Singleton
    IMessageBLL provideMessageBLL(ListMessageSuggestionsJob listMessageSuggestionsJob, SortMessagesByDateAsc sortMessagesByDateAsc,
                                  PostMessageJob postMessageJob) {
        return new MessageBLL(listMessageSuggestionsJob, sortMessagesByDateAsc, postMessageJob);
    }

    @Provides
    @Singleton
    IUserBLL provideUserBLL(GetUserNicknameJob getUserNicknameJob, SaveUserNicknameJob saveUserNicknameJob, HasUserNicknameJob hasUserNicknameJob) {
        return new UserBLL(getUserNicknameJob, saveUserNicknameJob, hasUserNicknameJob);
    }

    @Provides
    @Singleton
    IConversationBLL provideConversationBLL(SortConversationsByDateDescJob sortConversationsByDateDescJob, CreateConversationJob createConversationJob,
                                            JoinConversationJob joinConversationJob) {
        return new ConversationBLL(sortConversationsByDateDescJob, createConversationJob, joinConversationJob);
    }
}
