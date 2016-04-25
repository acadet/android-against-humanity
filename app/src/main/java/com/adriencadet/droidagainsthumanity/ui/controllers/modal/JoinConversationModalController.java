package com.adriencadet.droidagainsthumanity.ui.controllers.modal;

import android.widget.EditText;

import com.adriencadet.droidagainsthumanity.R;
import com.adriencadet.droidagainsthumanity.beans.Conversation;
import com.adriencadet.droidagainsthumanity.bll.BLLErrors;
import com.adriencadet.droidagainsthumanity.ui.controllers.BaseController;
import com.adriencadet.droidagainsthumanity.ui.screens.JoinConversationScreen;
import com.adriencadet.droidagainsthumanity.ui.screens.modal.InitModalScreen;
import com.adriencadet.droidagainsthumanity.ui.screens.modal.NicknameModalScreen;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * JoinConversationModalController
 * <p>
 */
public class JoinConversationModalController extends BaseController {
    private Subscription createConversationSubscription;

    @Bind(R.id.modal_join_conversation_edittext)
    EditText editText;

    @Override
    protected int layoutId() {
        return R.layout.modal_join_conversation;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (createConversationSubscription != null) {
            createConversationSubscription.unsubscribe();
        }
    }

    @OnClick(R.id.modal_join_conversation_join)
    void onJoin() {
        String slug = editText.getText().toString();

        modalRouter.resetTo(new InitModalScreen());
        mainRouter.goTo(new JoinConversationScreen(slug));
    }

    @OnClick(R.id.modal_join_conversation_create)
    void onCreate() {
        if (createConversationSubscription != null) {
            createConversationSubscription.unsubscribe();
        }

        createConversationSubscription = conversationBLL
            .create()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new BaseSubscriber<Conversation>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    if (e instanceof BLLErrors.NoNickname) {
                        modalRouter.goTo(new NicknameModalScreen());
                    } else {
                        super.onError(e);
                    }
                }

                @Override
                public void onNext(Conversation conversation) {
                    modalRouter.resetTo(new InitModalScreen());
                    mainRouter.goTo(new JoinConversationScreen(conversation));
                }
            });
    }
}
