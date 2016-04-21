package com.adriencadet.androidagainsthumanity.dao;

/**
 * IUserDAO
 * <p>
 */
public interface IUserDAO {
    boolean hasNickname();

    String getNickname();

    void saveNickname(String value);
}
