package com.adriencadet.androidagainsthumanity.ui.controllers.modal;

import android.widget.EditText;

import com.adriencadet.androidagainsthumanity.R;
import com.adriencadet.androidagainsthumanity.bll.BLLErrors;
import com.adriencadet.androidagainsthumanity.ui.controllers.BaseController;
import com.adriencadet.androidagainsthumanity.ui.screens.modal.ConfirmScreen;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * NicknameModalController
 * <p>
 */
public class NicknameModalController extends BaseController {
    private Subscription getNicknameSubscription;
    private Subscription setNicknameSubscription;
    private Subscription randomNicknameSubscription;

    @Bind(R.id.modal_nickname_edittext)
    EditText editText;

    private void confirmNickname(String value) {
        modalRouter.resetTo(new ConfirmScreen(context.getString(R.string.modal_nickname_confirmation, value)));
    }

    @Override
    protected int layoutId() {
        return R.layout.modal_nickname;
    }

    @Override
    public void onAttach() {
        super.onAttach();

        getNicknameSubscription = userBLL
            .getNickname()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new BaseSubscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onNext(String s) {
                    editText.setText(s);
                }
            });
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (getNicknameSubscription != null) {
            getNicknameSubscription.unsubscribe();
        }

        if (setNicknameSubscription != null) {
            setNicknameSubscription.unsubscribe();
        }

        if (randomNicknameSubscription != null) {
            randomNicknameSubscription.unsubscribe();
        }
    }

    @OnClick(R.id.modal_nickname_submit)
    void onSubmit() {
        String nickname = editText.getText().toString();

        if (setNicknameSubscription != null) {
            setNicknameSubscription.unsubscribe();
        }

        setNicknameSubscription = userBLL
            .saveNickname(nickname)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new BaseSubscriber<Void>() {
                @Override
                public void onCompleted() {
                    confirmNickname(nickname);
                }

                @Override
                public void onError(Throwable e) {
                    if (e instanceof BLLErrors.InvalidNickname) {
                        // TODO: alert user
                    } else {
                        super.onError(e);
                    }
                }

                @Override
                public void onNext(Void aVoid) {

                }
            });
    }

    @OnClick(R.id.modal_nickname_random)
    void onRandom() {
        if (randomNicknameSubscription != null) {
            randomNicknameSubscription.unsubscribe();
        }

        randomNicknameSubscription = userBLL
            .generateNickname()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new BaseSubscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onNext(String s) {
                    confirmNickname(s);
                }
            });
    }
}
