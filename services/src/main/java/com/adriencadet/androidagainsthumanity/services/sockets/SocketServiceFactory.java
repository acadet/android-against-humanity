package com.adriencadet.androidagainsthumanity.services.sockets;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * SocketServiceFactory
 * <p>
 */
@Module
public class SocketServiceFactory {
    @Provides
    @Singleton
    CreateConversationJob provideCreateConversationJob(ISocketServerAPI api) {
        return new CreateConversationJob(api);
    }

    @Provides
    @Singleton
    JoinConversationJob provideJoinConversationJob(Context context, ISocketServerAPI api) {
        return new JoinConversationJob(context, api);
    }

    @Provides
    @Singleton
    PushMessageJob providePushMessageJob() {
        return new PushMessageJob();
    }
}
