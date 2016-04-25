package com.adriencadet.droidagainsthumanity.services.sockets;

import android.content.Context;

import com.adriencadet.droidagainsthumanity.services.R;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

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
    ISocketServerAPI provideSocketServiceAPI(Context context) {
        return new RestAdapter
            .Builder()
            .setEndpoint(context.getString(R.string.socket_api_endpoint))
            .setConverter(new GsonConverter(new GsonBuilder().create()))
            .build()
            .create(ISocketServerAPI.class);
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

    @Provides
    @Singleton
    public ISocketService provideSocketService(CreateConversationJob createConversationJob, JoinConversationJob joinConversationJob,
                                               PushMessageJob pushMessageJob) {
        return new SocketService(createConversationJob, joinConversationJob, pushMessageJob);
    }
}
