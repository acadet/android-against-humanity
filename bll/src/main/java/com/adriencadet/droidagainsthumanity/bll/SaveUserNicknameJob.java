package com.adriencadet.droidagainsthumanity.bll;

import com.adriencadet.droidagainsthumanity.dao.IUserDAO;

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
                    String value = nickname.trim();

                    if (value.isEmpty()) {
                        subscriber.onError(new BLLErrors.InvalidNickname());
                        return;
                    }

                    userDAO.saveNickname(value);
                    subscriber.onCompleted();
                }
            })
            .subscribeOn(Schedulers.newThread());
    }
}
