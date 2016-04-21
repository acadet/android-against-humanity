package com.adriencadet.androidagainsthumanity.ui.controllers.body;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.adriencadet.androidagainsthumanity.R;
import com.adriencadet.androidagainsthumanity.beans.Conversation;
import com.adriencadet.androidagainsthumanity.ui.adapters.ConversationListAdapter;
import com.adriencadet.androidagainsthumanity.ui.controllers.BaseController;
import com.adriencadet.androidagainsthumanity.ui.screens.modal.NicknameModalScreen;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import butterknife.Bind;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * ConversationListController
 * <p>
 */
public class ConversationListController extends BaseController {
    private Subscription hasNicknameSubscription;
    private Subscription sortByDateDescSubscription;

    @Bind(R.id.conversation_list_listview)
    ListView listView;

    @Bind(R.id.conversation_list_no_content_label)
    TextView noContentLabelView;

    private void showView(View view) {
        view.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeIn).delay(300).playOn(view);
    }

    @Override
    protected int layoutId() {
        return R.layout.conversation_list;
    }

    @Override
    public void onAttach() {
        super.onAttach();

        hasNicknameSubscription = userBLL
            .hasNickname()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new BaseSubscriber<Boolean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onNext(Boolean hasNickame) {
                    if (!hasNickame) {
                        modalRouter.goTo(new NicknameModalScreen());
                    }
                }
            });

        sortByDateDescSubscription = conversationBLL
            .sortByDateDesc()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new BaseSubscriber<List<Conversation>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);

                    listView.setVisibility(View.GONE);
                    showView(noContentLabelView);
                }

                @Override
                public void onNext(List<Conversation> conversations) {
                    if (conversations.isEmpty()) {
                        listView.setVisibility(View.GONE);
                        showView(noContentLabelView);
                    } else {
                        noContentLabelView.setVisibility(View.GONE);
                        listView.setAdapter(new ConversationListAdapter(context, conversations));
                        showView(listView);
                    }
                }
            });
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (hasNicknameSubscription != null) {
            hasNicknameSubscription.unsubscribe();
        }

        if (sortByDateDescSubscription != null) {
            sortByDateDescSubscription.unsubscribe();
        }
    }
}
