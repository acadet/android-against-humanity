package com.adriencadet.droidagainsthumanity.services.sockets;

import com.adriencadet.droidagainsthumanity.beans.Message;

import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * MessageMapper
 * <p>
 */
class MessageMapper implements IMessageMapper {
    private AndroidDevice androidDevice;

    MessageMapper(AndroidDevice androidDevice) {
        this.androidDevice = androidDevice;
    }

    @Override
    public Message map(JSONObject source) {
        Message outcome = new Message();

        try {
            outcome
                .setId(source.getString("id"))
                .setContent(source.getString("content"))
                .setPosterNickname(source.getString("poster_nickname"))
                .setPostedAt(new DateTime(source.getString("posted_at")))
                .setMine(androidDevice.getIdentifier().equals(source.getString("device_token")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return outcome;
    }

    @Override
    public JSONObject map(Message message) {
        JSONObject outcome = new JSONObject();

        try {
            outcome
                .put("id", message.getId())
                .put("content", message.getContent())
                .put("poster_nickname", message.getPosterNickname())
                .put("posted_at", message.getPostedAt().toString())
                .put("device_token", androidDevice.getIdentifier());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return outcome;
    }
}
