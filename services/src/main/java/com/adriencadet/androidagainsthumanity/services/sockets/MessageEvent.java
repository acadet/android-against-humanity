package com.adriencadet.androidagainsthumanity.services.sockets;

/**
 * MessageEvent
 * <p>
 */
class MessageEvent {
    String slug;
    String message;

    MessageEvent(String slug, String message) {
        this.slug = slug;
        this.message = message;
    }
}
