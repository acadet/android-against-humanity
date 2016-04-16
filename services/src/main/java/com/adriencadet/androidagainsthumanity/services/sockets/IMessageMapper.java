package com.adriencadet.androidagainsthumanity.services.sockets;

import com.adriencadet.androidagainsthumanity.beans.Message;

import org.json.JSONObject;

/**
 * IMessageMapper
 * <p>
 */
interface IMessageMapper {
    Message map(JSONObject source);

    JSONObject map(Message message);
}
