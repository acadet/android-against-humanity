package com.adriencadet.androidagainsthumanity.services.sockets;

import org.json.JSONObject;

/**
 * MessageEvent
 * <p>
 */
class MessageEvent {
    String     slug;
    JSONObject data;

    MessageEvent(String slug, JSONObject data) {
        this.slug = slug;
        this.data = data;
    }
}
