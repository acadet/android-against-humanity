package com.adriencadet.droidagainsthumanity.services.sockets;

import com.adriencadet.droidagainsthumanity.beans.Message;

import org.json.JSONObject;

/**
 * IMessageMapper
 * <p>
 */
interface IMessageMapper {
    Message map(JSONObject source);

    JSONObject map(Message message);
}
