package com.adriencadet.droidagainsthumanity.beans;

import com.adriencadet.droidagainsthumanity.beans.utils.Optional;

import org.joda.time.DateTime;

/**
 * Conversation
 * <p>
 */
public class Conversation {
    private String             id;
    private String             slug;
    private Optional<DateTime> updatedAt;

    public String getSlug() {
        return slug;
    }

    public Conversation setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public String getId() {
        return id;
    }

    public Conversation setId(String id) {
        this.id = id;
        return this;
    }

    public Optional<DateTime> getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Optional<DateTime> updatedAt) {
        this.updatedAt = updatedAt;
    }
}
