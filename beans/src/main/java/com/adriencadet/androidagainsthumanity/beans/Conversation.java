package com.adriencadet.androidagainsthumanity.beans;

/**
 * Conversation
 * <p>
 */
public class Conversation {
    private String id;
    private String slug;

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
}
