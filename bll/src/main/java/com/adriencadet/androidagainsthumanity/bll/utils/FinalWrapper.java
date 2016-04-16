package com.adriencadet.androidagainsthumanity.bll.utils;

/**
 * FinalWrapper
 * <p>
 */
public class FinalWrapper<T> {
    private T value;

    public FinalWrapper() {

    }

    public FinalWrapper(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}
