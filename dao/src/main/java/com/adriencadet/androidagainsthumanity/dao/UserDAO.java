package com.adriencadet.androidagainsthumanity.dao;

import android.content.SharedPreferences;

/**
 * UserDAO
 * <p>
 */
class UserDAO implements IUserDAO {
    private static final String NICKNAME_KEY = "nickname";

    private SharedPreferences sharedPreferences;

    UserDAO(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public boolean hasNickname() {
        return getNickname() != null && !getNickname().trim().isEmpty();
    }

    @Override
    public String getNickname() {
        return sharedPreferences.getString(NICKNAME_KEY, null);
    }

    @Override
    public void saveNickname(String value) {
        sharedPreferences.edit().putString(NICKNAME_KEY, value).commit();
    }
}
