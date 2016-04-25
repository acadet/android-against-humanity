package com.adriencadet.droidagainsthumanity.dao;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * ConversationDTO
 * <p>
 */
public class ConversationDTO extends RealmObject {
    @PrimaryKey
    private String id;

    private String slug;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
