package com.adriencadet.androidagainsthumanity.dao;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmConfiguration;

/**
 * DAOFactory
 * <p>
 */
@Module
public class DAOFactory {
    @Provides
    @Singleton
    RealmConfiguration provideRealmConfiguration(Context context) {
        return new RealmConfiguration.Builder(context)
            .name("android-against-humanity.realm")
            .schemaVersion(1)
            .build();
    }

    @Provides
    @Singleton
    @Named("user")
    SharedPreferences provideUserSharedPreferences(Context context) {
        return context.getSharedPreferences("user", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    IMessageMapper provideMessageMapper() {
        return new MessageMapper();
    }

    @Provides
    @Singleton
    IConversationMapper provideConversationMapper() {
        return new ConversationMapper();
    }

    @Provides
    @Singleton
    public IMessageDAO provideMessageDAO(RealmConfiguration realmConfiguration, IMessageMapper messageMapper, IUserDAO userDAO) {
        return new MessageDAO(realmConfiguration, messageMapper, userDAO);
    }

    @Provides
    @Singleton
    public IConversationDAO provideConversationDAO(RealmConfiguration realmConfiguration, IConversationMapper conversationMapper) {
        return new ConversationDAO(realmConfiguration, conversationMapper);
    }

    @Provides
    @Singleton
    public IUserDAO provideUserDAO(@Named("user") SharedPreferences sharedPreferences) {
        return new UserDAO(sharedPreferences);
    }
}
