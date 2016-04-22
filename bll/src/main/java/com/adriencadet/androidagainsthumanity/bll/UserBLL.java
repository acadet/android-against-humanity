package com.adriencadet.androidagainsthumanity.bll;

import rx.Observable;

/**
 * UserBLL
 * <p>
 */
class UserBLL implements IUserBLL {
    private GetUserNicknameJob  getUserNicknameJob;
    private GenerateUserNicknameJob generateUserNicknameJob;
    private SaveUserNicknameJob saveUserNicknameJob;
    private HasUserNicknameJob  hasUserNicknameJob;

    UserBLL(GetUserNicknameJob getUserNicknameJob, GenerateUserNicknameJob generateUserNicknameJob, SaveUserNicknameJob saveUserNicknameJob, HasUserNicknameJob hasUserNicknameJob) {
        this.getUserNicknameJob = getUserNicknameJob;
        this.generateUserNicknameJob = generateUserNicknameJob;
        this.saveUserNicknameJob = saveUserNicknameJob;
        this.hasUserNicknameJob = hasUserNicknameJob;
    }

    @Override
    public Observable<String> getNickname() {
        return getUserNicknameJob.create();
    }

    @Override
    public Observable<String> generateNickname() {
        return generateUserNicknameJob.create();
    }

    @Override
    public Observable<Void> saveNickname(String nickname) {
        return saveUserNicknameJob.create(nickname);
    }

    @Override
    public Observable<Boolean> hasNickname() {
        return hasUserNicknameJob.create();
    }
}
