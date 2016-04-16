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
    AndroidDevice provideAndroidDevice() {
        return new AndroidDevice();
    }

    @Provides
    @Singleton
    IMessageMapper provideMessageMapper(AndroidDevice androidDevice) {
        return new MessageMapper(androidDevice);
    }

    @Provides
    @Singleton
    CreateConversationJob provideCreateConversationJob(ISocketServerAPI api) {
        return new CreateConversationJob(api);
    }

    @Provides
    @Singleton
    JoinConversationJob provideJoinConversationJob(Context context, ISocketServerAPI api, IMessageMapper messageMapper) {
        return new JoinConversationJob(context, api, messageMapper);
    }

    @Provides
    @Singleton
    PushMessageJob providePushMessageJob(IMessageMapper messageMapper) {
        return new PushMessageJob(messageMapper);
    }
}
