package com.adriencadet.droidagainsthumanity.ui.controllers.body;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.adriencadet.droidagainsthumanity.R;
import com.adriencadet.droidagainsthumanity.beans.Message;
import com.adriencadet.droidagainsthumanity.bll.BLLErrors;
import com.adriencadet.droidagainsthumanity.ui.adapters.MessageListAdapter;
import com.adriencadet.droidagainsthumanity.ui.controllers.BaseController;
import com.adriencadet.droidagainsthumanity.ui.screens.JoinConversationScreen;
import com.adriencadet.droidagainsthumanity.ui.screens.modal.NicknameModalScreen;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.lyft.scoop.Screen;
import com.nineoldandroids.animation.Animator;

import java.util.List;

import butterknife.Bind;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * ConversationController
 * <p>
 */
public class ConversationController extends BaseController {
    private Subscription sortByDateAscSubscription;
    private Subscription joinConversationSubscription;

    private MessageListAdapter messageListAdapter;

    @Bind(R.id.conversation_no_message)
    TextView noContentLabelView;

    @Bind(R.id.conversation_listview)
    ListView listView;

    private void show(View view) {
        YoYo
            .with(Techniques.FadeIn)
            .withListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    view.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            })
            .duration(300)
            .playOn(view);
    }

    private void hide(View view) {
        view.setVisibility(View.GONE);
    }

    @Override
    protected int layoutId() {
        return R.layout.conversation;
    }

    @Override
    public void onAttach() {
        super.onAttach();

        JoinConversationScreen screen = Screen.fromController(this);
        Observable<List<Message>> sortByDateAscObservable;
        Observable<Message> joinConversationObservable;

        if (screen.hasConversation()) {
            sortByDateAscObservable = messageBLL.sortByDateAsc(screen.conversation);
            joinConversationObservable = conversationBLL.join(screen.conversation);
        } else {
            sortByDateAscObservable = messageBLL.sortByDateAsc(screen.slug);
            joinConversationObservable = conversationBLL.join(screen.slug);
        }

        messageListAdapter = new MessageListAdapter(context);
        listView.setAdapter(messageListAdapter);

        sortByDateAscSubscription = sortByDateAscObservable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new BaseSubscriber<List<Message>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    hide(listView);
                    show(noContentLabelView);
                }

                @Override
                public void onNext(List<Message> messages) {
                    if (messages.isEmpty()) {
                        hide(listView);
                        show(noContentLabelView);
                    } else {
                        hide(noContentLabelView);
                        messageListAdapter.add(messages);
                        show(listView);
                    }
                }
            });

        joinConversationSubscription = joinConversationObservable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new BaseSubscriber<Message>() {
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
                public void onNext(Message message) {
                    messageListAdapter.add(message);
                    if (listView.getVisibility() == View.GONE) {
                        show(listView);
                        hide(noContentLabelView);
                    }
                }
            });
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (sortByDateAscSubscription != null) {
            sortByDateAscSubscription.unsubscribe();
        }

        if (joinConversationSubscription != null) {
            joinConversationSubscription.unsubscribe();
        }
    }
}
