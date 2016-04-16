package com.adriencadet.androidagainsthumanity.dao;

/**
 * IUserDAO
 * <p>
 */
public interface IUserDAO {
    String getNickname();

    void saveNickname(String value);
}
