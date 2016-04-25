package com.adriencadet.droidagainsthumanity.bll;

import com.adriencadet.droidagainsthumanity.dao.IUserDAO;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * GetUserNicknameJob
 * <p>
 */
class GetUserNicknameJob {
    private IUserDAO userDAO;

    GetUserNicknameJob(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    Observable<String> create() {
        return Observable
            .create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    subscriber.onNext(userDAO.getNickname());
                    subscriber.onCompleted();
                }
            })
            .subscribeOn(Schedulers.newThread());
    }
}
