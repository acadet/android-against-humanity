package com.adriencadet.androidagainsthumanity.dao;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * MessageDTO
 * <p>
 */
public class MessageDTO extends RealmObject {
    @PrimaryKey
    private String id;

    private String conversationId;

    private String  posterNickname;
    private String  content;
    private Date    postedAt;
    private boolean isMine;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getPosterNickname() {
        return posterNickname;
    }

    public void setPosterNickname(String posterNickname) {
        this.posterNickname = posterNickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }
}
