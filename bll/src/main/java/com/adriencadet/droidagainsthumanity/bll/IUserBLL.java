package com.adriencadet.droidagainsthumanity.bll;

import rx.Observable;

/**
 * IUserBLL
 * <p>
 */
public interface IUserBLL {
    Observable<String> getNickname();

    Observable<String> generateNickname();

    Observable<Void> saveNickname(String nickname);

    Observable<Boolean> hasNickname();
}
