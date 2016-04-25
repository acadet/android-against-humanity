package com.adriencadet.droidagainsthumanity.bll;

import com.adriencadet.droidagainsthumanity.dao.IUserDAO;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * HasUserNicknameJob
 * <p>
 */
class HasUserNicknameJob {
    private IUserDAO userDAO;

    HasUserNicknameJob(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    Observable<Boolean> create() {
        return Observable
            .create(new Observable.OnSubscribe<Boolean>() {
                @Override
                public void call(Subscriber<? super Boolean> subscriber) {
                    subscriber.onNext(userDAO.hasNickname());
                    subscriber.onCompleted();
                }
            })
            .subscribeOn(Schedulers.newThread());
    }
}
