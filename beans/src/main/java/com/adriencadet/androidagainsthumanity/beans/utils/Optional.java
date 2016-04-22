package com.adriencadet.androidagainsthumanity.beans.utils;

/**
 * Optional
 * <p>
 */
public class Optional<T> {
    private T value;

    public Optional() {
    }

    public Optional(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public boolean isPresent() {
        return value != null;
    }
}
