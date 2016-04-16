package com.adriencadet.androidagainsthumanity.beans;

import org.joda.time.DateTime;

/**
 * Message
 * <p>
 */
public class Message {
    private String   id;
    private String   posterNickname;
    private DateTime postedAt;
    private String   content;
    private boolean  isMine;

    public String getId() {
        return id;
    }

    public Message setId(String id) {
        this.id = id;
        return this;
    }

    public String getPosterNickname() {
        return posterNickname;
    }

    public Message setPosterNickname(String posterNickname) {
        this.posterNickname = posterNickname;
        return this;
    }

    public DateTime getPostedAt() {
        return postedAt;
    }

    public Message setPostedAt(DateTime postedAt) {
        this.postedAt = postedAt;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Message setContent(String content) {
        this.content = content;
        return this;
    }

    public boolean isMine() {
        return isMine;
    }

    public Message setMine(boolean mine) {
        isMine = mine;
        return this;
    }
}
