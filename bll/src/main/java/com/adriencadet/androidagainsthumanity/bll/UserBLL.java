package com.adriencadet.androidagainsthumanity.bll;

import rx.Observable;

/**
 * UserBLL
 * <p>
 */
class UserBLL implements IUserBLL {
    private GetUserNicknameJob  getUserNicknameJob;
    private SaveUserNicknameJob saveUserNicknameJob;

    @Override
    public Observable<String> getNickname() {
        return getUserNicknameJob.create();
    }

    @Override
    public Observable<String> generateNickname() {
        //TODO later
        return null;
    }

    @Override
    public Observable<Void> saveNickname(String nickname) {
        return saveUserNicknameJob.create(nickname);
    }
}
