package com.adriencadet.androidagainsthumanity.bll;

import android.content.Context;

import com.adriencadet.androidagainsthumanity.dao.IUserDAO;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * GenerateUserNicknameJob
 * <p>
 */
class GenerateUserNicknameJob {
    private Context  context;
    private IUserDAO userDAO;

    GenerateUserNicknameJob(Context context, IUserDAO userDAO) {
        this.context = context;
        this.userDAO = userDAO;
    }

    Observable<String> create() {
        return Observable
            .create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    String[] nicknames = context.getResources().getStringArray(R.array.nicknames);
                    int i = (int) Math.round(Math.random() * (nicknames.length - 1));
                    String nickname = nicknames[i];

                    userDAO.saveNickname(nickname);
                    subscriber.onNext(nickname);
                    subscriber.onCompleted();
                }
            })
            .subscribeOn(Schedulers.newThread());
    }
}
