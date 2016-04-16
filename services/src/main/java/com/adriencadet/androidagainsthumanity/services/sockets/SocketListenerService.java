package com.adriencadet.androidagainsthumanity.services.sockets;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.adriencadet.androidagainsthumanity.services.BuildConfig;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusBuilder;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * SocketListenerService
 * <p>
 */
public class SocketListenerService extends Service {
    private static final String SOCKET_SERVER_ENDPOINT = "https://socket-against-humanity.herokuapp.com/sessions/";
    private static final String SLUG_KEY               = "slug";
    private static final String MESSAGE_EVENT          = "Message";

    private static SocketListenerService instance;
    private        Map<String, Socket>   sockets;
    private        EventBus              socketEventBus;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        sockets = new HashMap<>();
        EventBusBuilder builder = EventBus
            .builder()
            .logNoSubscriberMessages(false)
            .sendNoSubscriberEvent(false);

        if (BuildConfig.DEBUG) {
            builder.throwSubscriberException(true);
        }
        socketEventBus = builder.build();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String slug = intent.getStringExtra(SLUG_KEY);

        if (!sockets.containsKey(slug)) {
            try {
                Socket socket = IO.socket(SOCKET_SERVER_ENDPOINT + slug);

                sockets.put(slug, socket);

                socket.on(MESSAGE_EVENT, (args) -> socketEventBus.post(new MessageEvent(slug, (JSONObject) args[0])));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        return START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        for (Map.Entry<String, Socket> entry : sockets.entrySet()) {
            entry.getValue().disconnect().off();
        }
    }

    public static Intent hydrateIntent(Intent intent, String slug) {
        intent.putExtra(SLUG_KEY, slug);
        return intent;
    }

    public static void subscribe(Object subscriber) {
        instance.socketEventBus.register(subscriber);
    }

    public static void pushMessage(MessageEvent event) {
        instance.sockets.get(event.slug).emit(MESSAGE_EVENT, event.data);
    }
}
