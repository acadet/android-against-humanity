package com.adriencadet.androidagainsthumanity.bll;

import com.adriencadet.androidagainsthumanity.dao.IUserDAO;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * SaveUserNicknameJob
 * <p>
 */
class SaveUserNicknameJob {
    private IUserDAO userDAO;

    SaveUserNicknameJob(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    Observable<Void> create(String nickname) {
        return Observable
            .create(new Observable.OnSubscribe<Void>() {
                @Override
                public void call(Subscriber<? super Void> subscriber) {
                    userDAO.saveNickname(nickname);
                    subscriber.onCompleted();
                }
            })
            .subscribeOn(Schedulers.newThread());
    }
}
