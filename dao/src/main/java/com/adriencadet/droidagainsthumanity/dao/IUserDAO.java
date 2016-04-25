package com.adriencadet.droidagainsthumanity.dao;

/**
 * IUserDAO
 * <p>
 */
public interface IUserDAO {
    boolean hasNickname();

    String getNickname();

    void saveNickname(String value);
}
