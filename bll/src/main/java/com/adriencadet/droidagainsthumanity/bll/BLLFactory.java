package com.adriencadet.droidagainsthumanity.bll;

import android.content.Context;

import com.adriencadet.droidagainsthumanity.bll.utils.PushNotificationSystem;
import com.adriencadet.droidagainsthumanity.dao.IConversationDAO;
import com.adriencadet.droidagainsthumanity.dao.IMessageDAO;
import com.adriencadet.droidagainsthumanity.dao.IUserDAO;
import com.adriencadet.droidagainsthumanity.services.sockets.ISocketService;

import javax.inject.Named;
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
    PushNotificationSystem providePushNotificationSystem(
        Context context, @Named("smallNotificationResourceId") int smallNotificationResourceId,
        @Named("largeNotificationResourceId") int largeNotificationResourceId) {
        return new PushNotificationSystem(context, smallNotificationResourceId, largeNotificationResourceId, "group_key_android_against_humanity");
    }

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
    GenerateUserNicknameJob provideGenerateUserNicknameJob(Context context, IUserDAO userDAO) {
        return new GenerateUserNicknameJob(context, userDAO);
    }

    @Provides
    @Singleton
    JoinConversationJob provideJoinConversationJob(
        Context context, IConversationDAO conversationDAO, IMessageDAO messageDAO, IUserDAO userDAO,
        ISocketService socketService, PostMessageJob postMessageJob, PushNotificationSystem pushNotificationSystem) {
        return new JoinConversationJob(context, conversationDAO, messageDAO, userDAO, socketService, postMessageJob, pushNotificationSystem);
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
                                  JoinConversationJob joinConversationJob) {
        return new MessageBLL(listMessageSuggestionsJob, sortMessagesByDateAsc, joinConversationJob);
    }

    @Provides
    @Singleton
    IUserBLL provideUserBLL(GetUserNicknameJob getUserNicknameJob, GenerateUserNicknameJob generateUserNicknameJob, SaveUserNicknameJob saveUserNicknameJob, HasUserNicknameJob hasUserNicknameJob) {
        return new UserBLL(getUserNicknameJob, generateUserNicknameJob, saveUserNicknameJob, hasUserNicknameJob);
    }

    @Provides
    @Singleton
    IConversationBLL provideConversationBLL(SortConversationsByDateDescJob sortConversationsByDateDescJob, CreateConversationJob createConversationJob,
                                            JoinConversationJob joinConversationJob) {
        return new ConversationBLL(sortConversationsByDateDescJob, createConversationJob, joinConversationJob);
    }
}
